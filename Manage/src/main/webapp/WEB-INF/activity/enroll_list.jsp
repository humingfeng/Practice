<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/10/10
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<style>
    .layui-form-label{
        width: auto;
    }
    .layui-table-page{
        text-align: right;
    }
    .layui-badge{
        margin-top: 5px;
    }
</style>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>报名管理</h2>
</blockquote>


<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <input type="hidden" name="activityId" value="${id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学校</label>
                <div class="layui-input-inline">
                    <select name="schoolId" id="schoolId" lay-filter="schoolId" lay-search>
                        <option value="">请选择学校</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学生名称</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="name" maxlength="20">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="status">
                        <option value="">请选择状态</option>
                        <option value="8">已确认</option>
                        <option value="9">待确认</option>
                        <option value="4">用户取消</option>
                        <option value="5">支付超时取消</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn search_btn layui-btn-warm" id="export" lay-filter="export">导出EXCEL</a>
            </div>
        </div>
    </form>

</div>
<!--table-->
<div class="layui-row" style="overflow: inherit">
    <table class="layui-hide" id="table"></table>
</div>
<script type="text/html" id="statusTpl">
    {{#  if(d.status === 9){ }}
    <span class="layui-badge layui-bg-orange">待确定</span>
    {{#  } }}
    {{#  if(d.status === 8){ }}
    <span class="layui-badge layui-bg-green">已确认</span>
    {{#  } }}
    {{#  if(d.status === 5){ }}
    <span class="layui-badge layui-bg-danger">订单超时取消</span>
    {{#  } }}
    {{#  if(d.status === 4){ }}
    <span class="layui-badge layui-bg-danger">用户取消</span>
    {{#  } }}
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/html" id="exportFileds">
    <fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">基本信息已包含</blockquote>
    <div class="layui-form layui-form-pane" id="form">
        <div class="layui-form-item ">
            {{# if(d.phone){ }}
            <div class="layui-inline">
                <label class="layui-form-label ">电话信息</label>
                <div class="layui-input-block ">
                    <input type="checkbox" name="phone" title="选择" >
                </div>
            </div>
            {{# } }}
            {{# if(d.parentName){ }}
            <div class="layui-inline">
                <label class="layui-form-label">家长姓名</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="parentName" title="选择" >
                </div>
            </div>
            {{# } }}
            {{# if(d.idNum){ }}
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="idNum" title="选择" >
                </div>
            </div>
            {{# } }}
        </div>
        <div class="layui-form-item">
            {{# if(d.passport){ }}
            <div class="layui-inline">
                <label class="layui-form-label">护照</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="passport" title="选择">
                </div>
            </div>
            {{# } }}
            {{# if(d.weight){ }}
            <div class="layui-inline">
                <label class="layui-form-label">体重</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="weight" title="选择" >
                </div>
            </div>
            {{# } }}
            {{# if(d.height){ }}
            <div class="layui-inline">
                <label class="layui-form-label">身高</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="height" title="选择" >
                </div>
            </div>
            {{# } }}
            {{# if(d.sex){ }}
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="sex" title="选择" >
                </div>
            </div>
            {{# } }}
        </div>
        <div class="layui-form-item">
            {{# if(d.nation){ }}
            <div class="layui-inline">
                <label class="layui-form-label">民族</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="nation" title="选择">
                </div>
            </div>
            {{# } }}
            {{# if(d.birthday){ }}
            <div class="layui-inline">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="birthday" title="选择">
                </div>
            </div>
            {{# } }}
        </div>
        <div class="layui-inline">
            <a class="layui-btn search_btn" lay-submit="return false" lay-filter="export">确认导出</a>
        </div>
    </div>
    </fieldset>
</script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','table','laypage','laytpl'],function(){
        var $ = layui.$,
            form = layui.form,
            app=layui.app,
            table = layui.table,
            laytpl = layui.laytpl,
            laypage = layui.laypage;

        var pageParam = {
            pageIndex:1,
            pageSize:10,
            pageStatus:1,
            searchFileds:{}
        }

        var load;

        var getTpl = $("#exportFileds").html();


        var Fileds = {

            phone:0,
            parentName:0,
            idNum:0,
            birthday:0,
            nation:0,
            passport:0,
            weight:0,
            height:0,
            sex:0

        }

        var index;
        $("#export").click(function(){
            //获取该活动选择的信息

            app.get('/auth/activity/enroll/info/${id}').then(d=>{

                laytpl(getTpl).render(d.data, function(html){
                    index = layer.open({
                        type: 1,area: ['800px', '400px'],title:'选择导出字段',
                        content: html
                    });
                    form.render();
                });
            });
        })

        form.on("submit(export)",function(data){

            var form = $("<form>");
            form.attr('style', 'display:none');
            form.attr('target', '');
            form.attr('method', 'post');
            form.attr('action', '/download/excel/enroll/${id}');

            layui.each(data.field,function(key,value){

                Fileds[key] = 1;


            })

            $('body').append(form);

            layui.each(Fileds,function(key,value){
                var input = $('<input>');
                input.attr('type', 'hidden');
                input.attr('name', key);
                input.attr('value', value);
                form.append(input);
            })

            form.submit();
            form.remove();

            layer.close(index);

            return false;
        })


        app.get("/auth/baseinfo/school/usable/list").then(d=>{
            layui.each(d.data,function(index,item){
                $("#schoolId").append("<option value='"+item.id+"' >"+item.value+"</option>");
            })
            form.render('select');
        });


        var initData = function(){
            load = app.showLoading()
            app.post('/auth/activity/enroll/record/list/${id}',pageParam).then(d=>{
                renderTable(d.data);
            },e=>{app.layerMessageE(e)}).finally(_d=>{app.closeLoading(load)});
        }
        initData();

        var renderTable = function(data){
            table.render({
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'schoolName', title: '学校'},
                    {field:'periodName', title: '年级'},
                    {field:'className', title: '班级'},
                    {field:'name', title: '名称'},
                    {field:'status', title: '状态',templet: '#statusTpl',width:100},
                    {field:'timeStr', title: '报名时间',width:180}
                ]],
                data:data.list,
                done: function(res, curr, count){

                    $('.layui-table-box').append($('<div class="layui-table-page" id="page"></div>'));

                    laypage.render({
                        elem: 'page',
                        count: data.total,
                        layout: ['count', 'prev', 'page', 'next'],
                        prev: '<i class="layui-icon"></i>',
                        next: '<i class="layui-icon"></i>',
                        curr:data.pageNum,
                        jump: function(obj_page, first) {
                            pageParam.pageIndex = obj_page.curr;
                            if(!first) {
                              initData();
                            }
                        }
                    });
                }
            });
        }

        form.on("submit(search)", function (formdata) {
            if(isFormNull(formdata.field)){
                pageParam.searchFileds={};
                initData();
            }else{
                pageParam.searchFileds = formdata.field;
                initData();

            }
        });

        var isFormNull = function(field){
            var r = 1;
            $.each(field,function (key, value) {
                if(value){r =0 ;return false;}
            });
            return r;
        }


    })
</script>
</body>
</html>
