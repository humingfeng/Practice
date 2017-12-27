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
<body class="childrenBody">

<fieldset class="layui-elem-field"  style="width: 500px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">类型新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">类型名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="required" maxlength="20" placeholder="请输入类型名称">
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
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm'],function(){
        var $ = layui.$,form = layui.form,app = layui.app;


        var id = $("#id").val();

        var url = "/auth/activity/type/add";

        if(id){

            var load = app.showLoading();
            url = "/auth/activity/type/update";
            app.get('/auth/activity/type/'+id).then(d=>{
                $("#form").initForm(d.data);
            },e=>{}).finally(_=>{app.closeLoading(load);});
        }

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