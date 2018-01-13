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
<%@include file="../head.jsp"%>
<body class="childrenBody tips_verify">
<fieldset class="layui-elem-field"  style="width: 710px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">学生新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">学校</label>
                    <div class="layui-input-block">
                        <select name="schoolId" id="schoolId" lay-filter="schoolId" lay-search lay-verify="empty" lay-verType="tips" placeholder="请选择学校" >
                            <option value="">请选择学校</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">年级</label>
                    <div class="layui-input-block">
                        <select name="periodId" id="periodId" lay-filter="periodId" lay-search lay-verify="empty" lay-verType="tips" placeholder="请选择年级" >
                            <option value="">请选择年级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-block">
                        <select name="classId" id="classId" lay-filter="classId" lay-search lay-verify="empty" lay-verType="tips" placeholder="请选择班级" >
                            <option value="">请选择班级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学生名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="empty" lay-verType="tips" maxlength="20" placeholder="请输入学生名称" >
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="1" title="男" checked >
                        <input type="radio" name="sex" value="2" title="女" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学籍号</label>
                    <div class="layui-input-block">
                        <input type="text" name="enuNum" class="layui-input" lay-verify="empty" lay-verType="tips" maxlength="30"  placeholder="请输入学籍号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-block">
                        <input type="text" name="idNum" class="layui-input"  maxlength="20"  placeholder="请输入身份证号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">出生日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="birthday" name="birthday" class="layui-input"  lay-verify="empty" lay-verType="tips" maxlength="20"  placeholder="请输入出生日期">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">民族</label>
                    <div class="layui-input-block">
                        <select name="nation" id="nation" lay-filter="nation" lay-search lay-verify="empty" lay-verType="tips" placeholder="请选择民族" >
                            <option value="">请选择民族</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','laydate'],function(){
        var $ = layui.$,form = layui.form,app = layui.app,laydate = layui.laydate;

        var id = $("#id").val();

        var url = "/auth/personnel/student/add";

        var load = app.showLoading();


        laydate.render({elem: '#birthday'});

        form.verify({
            empty: function(value, item) {
                if(!value){
                    return $(item).attr('placeholder');
                }
            }
        });
        app.get('/auth/system/dictionary/type/PERIOD').then(d=>{
            layui.each(d.data,function(index,item){
                if(item.param=="小学"||item.param=="初中"||item.param=="高中"){
                    $("#periodId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                }

            })
            app.get('/auth/system/dictionary/type/CLASS_TYPE').then(d=>{
                layui.each(d.data,function(index,item){
                    $("#classId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                })

                app.get('/auth/system/dictionary/type/NATION_TYPE').then(d=>{
                    layui.each(d.data,function(index,item){
                        $("#nation").append("<option value='"+item.id+"' >"+item.name+"</option>");
                    })

                    app.get("/auth/baseinfo/school/usable/list").then(d=>{
                        layui.each(d.data,function(index,item){
                            $("#schoolId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                        })

                        if(id){
                            url = "/auth/personnel/student/update";
                            app.get('/auth/personnel/student/'+id).then(d=>{

                                $("#form").initForm(d.data);

                                form.render();

                            },e=>{}).finally(_=>{app.closeLoading(load)});
                        }else{
                            form.render('select');
                            app.closeLoading(load);
                        }

                    },e=>{});
                })


            },e=>{});
        })




        form.on("submit(submit)",data=>{

            app.post(url,data.field).then(d=>{
                if(id){
                    app.layerMessageS(d.message);
                    app.time(function(){
                        app.back();
                    })
                }else{
                    app.layerSuccessConfirm(d.message+'，是否继续添加',function(){
                        app.layerMessage('好的');
                        $("button[type='reset']").click();
                    },function(){
                        app.back();
                    });
                }
            },e=>{
                app.layerMessageE(e);
            })
            return false;
        })

    })
</script>
</body>
</html>
