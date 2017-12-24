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

<fieldset class="layui-elem-field"  style="width: 800px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">版本记录新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">版本号</label>
                    <div class="layui-input-block">
                        <input type="text" name="versionNum" class="layui-input" lay-verify="required" maxlength="20" placeholder="版本号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">版本概述</label>
                    <div class="layui-input-block">
                        <input type="text" name="versionGeneral" class="layui-input" lay-verify="required" maxlength="100" placeholder="版本概述">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">负责人</label>
                    <div class="layui-input-block">
                        <input type="text" name="leader" class="layui-input" lay-verify="required" maxlength="100" placeholder="负责人">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">版本记录</label>
                        <div class="layui-input-inline" style="width: 495px;">
                            <input type="text" id="title" name="title" lay-verify="required" maxlength="100"  class="layui-input" placeholder="版本记录" style="width: 495px;">
                        </div>
                    </div>
                    <div class="layui-inline" >
                        <div class="layui-input-inline"style="width: 100px;" >
                            <input type="text" id="author" name="author" lay-verify="required" maxlength="20" class="layui-input" placeholder="作者">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-right: 0px;float: right">
                        <button class="layui-btn" style="font-size: 20px;" id="plus">+</button>
                    </div>
                    <input type="hidden" name="items">
                </div>
                <div id="content">
                    <script id="tpl" type="text/html">
                        {{#  layui.each(d, function(index, item){ }}
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">版本记录</label>
                                <div class="layui-input-inline" style="width: 495px;">
                                    <input type="text" name="{{item.name}}" lay-verify="required" maxlength="100" value="{{item.value1}}" class="layui-input" placeholder="版本记录" style="width: 495px;">
                                </div>
                            </div>
                            <div class="layui-inline" >
                                <div class="layui-input-inline"style="width: 100px;" >
                                    <input type="text" name="{{item.author}}" lay-verify="required" maxlength="20" value="{{item.value2}}" class="layui-input" placeholder="作者">
                                </div>
                            </div>
                            <div class="layui-inline" style="margin-right: 0px;float: right">
                                <button class="layui-btn layui-btn-danger min" style="font-size: 35px;">-</button>
                            </div>
                        </div>
                        {{# });}}
                    </script>
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
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','laytpl'],function(){
        var $ = layui.$,form = layui.form,app = layui.app,laytpl = layui.laytpl;


        var id = $("#id").val();

        var url = "/auth/system/version/add";

        var tpl = laytpl($("#tpl").html());

        if(id){
            var load = app.showLoading();
            url = "/auth/system/version/update";
            app.get('/auth/system/version/'+id).then(d=>{
                $("#form").initForm(d.data);
            },e=>{}).finally(_=>{});

            app.get('/auth/system/version/item/list/'+id).then(d=>{

                var data = d.data;

                var items = [];

                layui.each(data,function(index,item){
                    if(index==0){
                        $("#title").val(item.item);
                        $("#author").val(item.author);
                    }else{
                        items.push({name:'title',author:'author',value1:item.item,value2:item.author});
                    }
                });
                tpl.render(items, function(html){
                    $("#content").append(html);
                    form.render();
                });
                app.closeLoading(load);

            },e=>{}).finally(_=>{});
        }




        $("body").on("click","#plus",function(){

            var item = [{name:'title',author:'author',value1:'',value2:''}];

            tpl.render(item, function(html){
                $("#content").append(html);
                form.render();
            });
            return false;
        })
        $("body").on("click",".min",function(){
            $(this).parent().parent().remove();
            return false;
        });

        form.on("submit(submit)",data=>{

            var items = [];

            var titleArry = $("input[name='title']");

            var authorArry = $("input[name='author']");

            layui.each(titleArry,function(index,item){
                var obj = {title:item.value,author:authorArry[index].value};
                items.push(obj);
            })
            data.field.items = JSON.stringify(items);

            app.post(url,data.field).then(d=>{
                if(!id){
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
