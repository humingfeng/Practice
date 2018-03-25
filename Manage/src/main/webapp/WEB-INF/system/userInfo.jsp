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
<body class="childrenBody">

<fieldset class="layui-elem-field"  style="width: 500px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
       <p> 帐号暂放，建议输入昵称的拼音</p>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${userInfo.id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">帐号</label>
                    <div class="layui-input-block">
                        <input type="text" name="account" class="layui-input" value="${userInfo.account}" lay-verify="required" maxlength="50" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-block">
                        <input type="text" name="phone"  class="layui-input" value="${userInfo.phone}" lay-verify="required|phone|number" maxlength="11" placeholder="请输入手机号" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-block">
                        <input type="text" name="nickName" class="layui-input" value="${userInfo.nickName}" lay-verify="required" maxlength="100" placeholder="请输入昵称">
                    </div>
                </div>


                <div class="layui-form-item" >
                    <label class="layui-form-label ">用户类型</label>
                    <div class="layui-input-block">
                        <select name="type" lay-verify="required" id="type">

                        </select>
                    </div>
                </div>
                <div class="layui-form-item" pane>
                    <label class="layui-form-label ">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="1" title="男" checked>
                        <input type="radio" name="sex" value="2" title="女">
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

        var load = app.showLoading();
        app.get('/auth/system/dictionary/type/ORGANIZE_TYPE').then(d=>{
            $("#organizeId").empty();
            $("#organizeId").append("<option value=''>请选择</option>");
            layui.each(d.data,function(index,item){
                $("#organizeId").append("<option value='"+item.id+"' >"+item.name+"</option>");
            })
            form.render('select');

        },e=>{}).finally(_=>{app.closeLoading(load)})

        app.get('/auth/system/dictionary/type/USER_TYPE').then(d=>{
            $("#type").empty();
            $("#type").append("<option value=''>请选择</option>");
            layui.each(d.data,function(index,item){
                $("#type").append("<option value='"+item.id+"' >"+item.name+"</option>");
            })
            form.render('select');

        },e=>{}).finally(_=>{if(!id){app.closeLoading(load)}})


        var url = "/auth/system/user/add";
        var pass = "";

        app.time(function(){
            if(id){
                url = '/auth/system/user/update';
                app.get('/auth/system/user/'+id).then(d=>{
                    $("#form").initForm(d.data);
                    pass = d.data.password;
                    form.render();
                },e=>{}).finally(_=>{app.closeLoading(load)});
            }
        },100)

        form.on("submit(submit)",data=>{

            app.post(url,data.field).then(d=>{
                app.layerMessageS(d.message);
                app.time(function(){
                    parent.closeWinCur();
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
