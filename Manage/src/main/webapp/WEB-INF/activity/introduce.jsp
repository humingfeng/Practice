<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/1/2
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<style>
    .layui-upload-img{
        height: 100px;
        width: 100px;
    }
</style>
<body class="childrenBody">
    <fieldset class="layui-elem-field">
        <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动介绍</blockquote>
        <form class="layui-form layui-form-pane" id="form">
            <input type="hidden" name="id" value="${introduce.id}" id="id">
            <input type="hidden" name="activityId" value="${introduce.activityId}" id="activityId">
            <div class="layui-form-item">
                <label class="layui-form-label">省市区</label>
                <div class="layui-input-inline">
                    <select name="pid" id="proviceId" lay-filter="proviceId" lay-search lay-verify="required">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="cid" id="cityId" lay-filter="cityId" lay-search lay-verify="required">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="aid" id="areaId" >
                        <option value="">请选择县/区</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item " >
                <div class="layui-input-inline layui-form-text" style="width: 300px;">
                    <label class="layui-form-label">活动地点</label>
                    <div class="layui-input-block" >
                        <textarea name="address" lay-verify="required" maxlength="200" style="height: 102px;resize: none" class="layui-textarea" placeholder="请输入活动地点" >${introduce.address}</textarea>
                    </div>
                </div>
                <div class="layui-input-inline layui-form-text" style="width: 102px;">
                    <input type="hidden" name="imgCover" id="imgCover" value="${introduce.imgCover}">
                    <label class="layui-form-label">封面</label>
                    <div class="layui-input-block" id="upload">
                        <img class="layui-upload-img" src="/static/img/noimg.svg" id="cover" style="border:1px solid #e6e6e6">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">活动介绍</label>
                <div class="layui-input-block">
                    <div id="edit" >

                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                </div>
            </div>

        </form>

    </fieldset>
    <script type="text/javascript" src="/static/layui/layui.js"></script>
    <script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
    <script type="text/javascript">
        layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','upload'],function() {
            var $ = layui.$, form = layui.form, app = layui.app,upload = layui.upload;

            var load;

            load = app.showLoading();



            var editor = new window.wangEditor('#edit');
            editor.customConfig.uploadFileName = 'file'
            editor.customConfig.uploadImgServer = '/upload/img/activity_introduce';
            editor.customConfig.uploadImgHooks = {
                before: function (xhr, editor, files) {
                    load = app.showLoading();
                },
                customInsert: function (insertImg, result, editor) {
                    if(result.code==200){
                        insertImg(result.data);
                    }else{
                        app.layerMessageE(result.message);
                    }
                    app.closeLoading(load);
                }
            }
            editor.create();

            app.get('/auth/baseinfo/province/list').then(d=>{
                layui.each(d.data,function(index,item) {
                    $("#proviceId").append("<option value='" + item.pid + "' >" + item.name + "</option>");
                });


                if(Number($("#id").val())){

                    $("#cover").attr("src",$("#imgCover").val());

                    $("#proviceId").val(${introduce.pid});

                    provinceChange(${introduce.pid}).then(function(){

                        $("#cityId").val(${introduce.cid});

                        cityChange(${introduce.cid}).then(function(){

                            $("#areaId").val(${introduce.aid});

                            editor.txt.html('${introduce.detail}');


                            form.render("select");
                            app.closeLoading(load);

                        })
                    });


                }else{
                    form.render("select");
                    app.closeLoading(load);
                }


            },e=>{});

            $(document).ready(function () {

                parent.resetHeight(document.body.scrollHeight);

            });


            //普通图片上传
            var uploadInst = upload.render({
                elem: '#upload',url: '/upload/img/activity_cover',
                accept:"images",size:1024*1024*3,
                before: function(){
                    load = app.showLoading();
                },
                done: function(result){
                    if(result.code==200){
                        $("#cover").attr("src",result.data);
                        $("#imgCover").val(result.data);
                    }else{
                        app.layerMessageE(result.message);
                    }
                    app.closeLoading(load);
                }
            });

            form.on('select(proviceId)', function(data){
                if(data.value){
                    provinceChange(data.value);
                }else{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>请选择城市/区</option>");
                    form.render('select');
                }


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
                if(data.value){
                    cityChange(data.value);
                }else{
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>请选择区/县</option>");
                    form.render('select');
                }


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
                if(!data.field.aid)data.field.areaId=0;
                if(!data.field.imgCover){

                    $("#cover").css("border-color","#FF5722");
                    app.layerMessageE("请上传封面");

                    return false;
                }else{
                    $("#cover").css("border-color","#e6e6e6");
                }
                if(!editor.txt.text()){
                    $('.w-e-text-container').css("border-color","#FF5722");
                    app.layerMessageE("请输入活动介绍");
                    return false;
                }else{
                    $('.w-e-text-container').css("border-color","#ccc");
                }
                data.field.detail = editor.txt.html();
                app.post('/auth/activity/introduce/update',data.field).then(d=>{
                    app.layerMessageS(d.message);
                },e=>{
                    app.layerMessageE(e);
                })
                return false;
            })

        });
    </script>
</body>
</html>
