<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/12/23
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<body class="childrenBody tips_verify">
<fieldset class="layui-elem-field"  style="width: 710px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        批量导入
        <div class="layui-inline" style="margin-left: 20px;">
            <a class="layui-btn layui-btn-primary" href="/download/excel/teacher/template">模版下载</a>
            <a class="layui-btn layui-btn-primary" id="upload">上传Excel</a>
        </div>
    </blockquote>
    <blockquote class="layui-elem-quote layui-quote-nm">
        <p>1.手机号为系统内唯一，如果重复系统会自动跳过</p>
        <p>2.性别未设置，系统默认为男</p>
        <p>3.导入的数据，教师身份默认为任课教师</p>
    </blockquote>
    <div class="layui-row layui-hide"  id="body">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <div class="layui-form-item">
                    <label class="layui-form-label">学校</label>
                    <div class="layui-input-inline">
                        <select name="schoolId" id="schoolId" lay-filter="schoolId" lay-search lay-verify="empty" lay-verType="tips" placeholder="请选择学校" >
                            <option value="">请选择学校</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即导入</button>
                    </div>
                </div>

                <div class="layui-form-item">
                    <table class="layui-hide" id="table" lay-filter="table"></table>
                </div>


            </form>
        </div>
    </div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','upload','table'],function(){
        var $ = layui.$,
            form = layui.form,
            app = layui.app,
            upload = layui.upload,
            table = layui.table,
            data=[],
            load;

        form.verify({
            empty: function(value, item) {
                if(!value){
                    return $(item).attr('placeholder');
                }
            }
        });

        upload.render({
            elem: '#upload',url: '/upload/file/teacher/excel',
            accept:"file",exts:'xlsx|xls',size:1024*1024*3,
            before: function(){
                load = app.showLoading();
            },
            done: function(result){
                if(result.code==200){

                    data = result.data;
                    initTable(data);
                    $("#body").removeClass('layui-hide');
                }else{
                    app.layerMessageE(result.message);
                }
                app.closeLoading(load);
            }
        });
        var tableModel;
        var initTable = function(data) {
            tableModel = table.render({
                elem: '#table',
                page:{ layout: ['count', 'prev', 'page', 'next']},
                cols: [[
                    {field: 'name', title: '名称', edit: 'text',minWidth:150},
                    {field: 'sex', title: '性别', edit: 'text'},
                    {field: 'phone', title: '手机号', edit: 'text',minWidth:150},
                    {field: 'idNum', title: '身份证', edit: 'text',minWidth:180}
                ]],
                data: data
            });
        }
        app.get('/auth/baseinfo/school/usable/list').then(d=>{
            layui.each(d.data,function(index,item){
                $("#schoolId").append("<option value='"+item.id+"' >"+item.value+"</option>");
            })
            form.render('select');
            app.closeLoading(load);
        },e=>{});

        form.on("submit(submit)",data=>{

            var list = tableModel.config.data;

            data.field.rows = JSON.stringify(list);

            app.post('/auth/personnel/teacher/export',data.field).then(d=>{
                app.layerMessageS(d.message);
                app.time(function(){
                    app.back();
                })
            },e=>{
                app.layerMessageE(e);
            })
            return false;
        })

    })
</script>
</body>
</html>
