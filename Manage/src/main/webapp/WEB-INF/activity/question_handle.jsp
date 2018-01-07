<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/1/7
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../head.jsp"%>
<body class="childrenBody">
<fieldset class="layui-elem-field"  style="width: 500px">
        <div class="layui-form layui-form-pane" id="form">
            <input type="hidden" value="${id}" name="id" id="id">
            <div class="layui-form-item">
                <label class="layui-form-label">题目类型</label>
                <div class="layui-input-block">
                    <select name="typeId" id="typeId" lay-filter="typeId" lay-verify="required" >

                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">题目内容</label>
                <div class="layui-input-block">
                        <textarea type="text" class="layui-textarea" name="question" maxlength="200"
                                  placeholder="请输入题目内容" lay-verify="required" style="resize: none"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
            </div>
        </div>
    </fieldset>
    <script type="text/javascript" src="/static/layui/layui.js"></script>
    <script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm'],function(){
        var $ = layui.$,form = layui.form,app = layui.app;


        var id = $("#id").val();

        var url = "/auth/activity/question/add";

        var load = app.showLoading();

        app.get('/auth/system/dictionary/type/QUESTION_TYPE').then(d=>{
            $("#typeId").empty();
            $("#typeId").append("<option value=''>请选择</option>");
            layui.each(d.data,function(index,item){
                $("#typeId").append("<option value='"+item.id+"' >"+item.name+"</option>");
            })
            form.render('select');

            if(id){
                url = "/auth/activity/question/update";
                app.get('/auth/activity/question/obj/'+id).then(d=>{
                    $("#form").initForm(d.data);
                    form.render('select');
                },e=>{}).finally(_=>{app.closeLoading(load);});
            }else{

                app.closeLoading(load);
            }
        },e=>{}).finally(_=>{app.closeLoading(load)})



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
