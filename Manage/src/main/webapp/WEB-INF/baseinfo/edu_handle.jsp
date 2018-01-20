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
<fieldset class="layui-elem-field"  style="width: 710px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">教育局新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">省市区</label>
                    <div class="layui-input-inline">
                        <select name="proviceId" id="proviceId" lay-filter="proviceId" lay-search lay-verify="required">
                            <option value="">请选择省</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="cityId" id="cityId" lay-filter="cityId" lay-search lay-verify="required">
                            <option value="">请选择市</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="areaId" id="areaId" >
                            <option value="">请选择县/区</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教育局名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="required" maxlength="40" style="width: 300px;" placeholder="请输入教育局名称">
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

        var url = "/auth/baseinfo/edu/add";

        var load = app.showLoading();
        app.get('/auth/baseinfo/province/list').then(d=>{
            layui.each(d.data,function(index,item){
                $("#proviceId").append("<option value='"+item.pid+"' >"+item.name+"</option>");
            })
            form.render('select');
            if(id){
                url = "/auth/baseinfo/edu/update";
                app.get('/auth/baseinfo/edu/'+id).then(d=>{
                    provinceChange(d.data.proviceId).then(function(){
                        cityChange(d.data.cityId).then(function(){
                            $("#form").initForm(d.data);
                            form.render('select');
                            app.closeLoading(load);
                        })
                    });
                },e=>{}).finally(_=>{});
            }else{

                app.closeLoading(load);
            }
        },e=>{});

        form.on('select(proviceId)', function(data){
            provinceChange(data.value);

        });

        var provinceChange = function(pid){
            return new Promise((resolve) => {
                app.get('/auth/baseinfo/city/list/'+pid).then(d=>{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>请选择城市/区</option>");
                    layui.each(d.data,function(index,item){
                        $("#cityId").append("<option value='"+item.cid+"' >"+item.name+"</option>");
                    })
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>请选择区/县</option>");
                    form.render('select');
                },e=>{}).finally(_=>{resolve()});
            });
        }

        form.on('select(cityId)', function(data){
            cityChange(data.value);
        });

        var cityChange = function(cid){
            return new Promise((resolve) => {
                app.get('/auth/baseinfo/area/list/'+cid).then(d=>{
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>请选择区/县</option>");
                    if(d.data.length){
                        $("#areaId").removeAttr("disabled");
                        layui.each(d.data,function(index,item){
                            $("#areaId").append("<option value='"+item.aid+"' >"+item.name+"</option>");
                        })
                    }else{
                        $("#areaId").attr("disabled","disabled");
                        $("#areaId").val(0);
                    }

                    form.render('select');
                },e=>{}).finally(_=>{resolve();});
            });

        }

        form.on("submit(submit)",data=>{
            if(!data.field.areaId)data.field.areaId=0;
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
