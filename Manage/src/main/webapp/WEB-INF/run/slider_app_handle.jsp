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

<fieldset class="layui-elem-field"  style="width: 500px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">APP 轮播广告新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">广告名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="empty" lay-verType="tips" maxlength="100" placeholder="请输入广告名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">投放位置</label>
                    <div class="layui-input-block">
                        <select name="tag" lay-verify="empty" lay-verType="tips" placeholder="请选择投放位置">
                            <option value=""> 请选择投放位置</option>
                            <option value="1"> 活动页</option>
                            <option value="2"> 统计页</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">广告类型</label>
                    <div class="layui-input-block">
                        <select name="type" lay-verify="empty" lay-verType="tips" placeholder="请选择广告类型" lay-filter="type">
                            <option value=""> 请选择广告类型</option>
                            <option value="1"> 内容模版</option>
                            <option value="2"> 第三方页面</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">广告URL</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" id="url" class="layui-input" lay-verify="empty|url" lay-verType="tips" maxlength="200" placeholder="请输入广告URL">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">广告封面</label>
                    <input type="hidden" name="imgCover" id="imgCover">
                    <div class="layui-input-block" >
                        <div class="layui-input-block" id="upload">
                            <img class="layui-upload-img" src="/static/img/app_slider.png" id="cover" style="width: 100%;">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">描述信息</label>
                    <div class="layui-input-block" >
                        <textarea  name="description" lay-verify="empty" lay-verType="tips" maxlength="500" class="layui-textarea" placeholder="请输入描述信息" style="resize: none;"></textarea>
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
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','upload'],function(){
        var $ = layui.$,
            form = layui.form,
            upload = layui.upload,
            app = layui.app,load;

        form.verify({
            empty: function(value, item) {
                if(!value){
                    return $(item).attr('placeholder');
                }
            }
        });

        var id = $("#id").val();

        var url = "/auth/run/slider/app/add";

        if(id){
            load = app.showLoading();
            url = "/auth/run/slider/app/update";
            app.get('/auth/run/slider/app/'+id).then(d=>{
                $("#form").initForm(d.data);

                $("#cover").attr("src",d.data.imgCover);

                form.render('select');
            },e=>{}).finally(_=>{app.closeLoading(load);});
        }

        upload.render({
            elem: '#upload',url: '/upload/img/slider_cover',
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

        form.on('select(type)', function(data){
            if(data.value == 1){
                $("#url").attr("disabled","disabled");
                $("#url").removeAttr("lay-verify");
            }else{
                $("#url").removeAttr("disabled","disabled");
                $("#url").attr("lay-verify","empty|url");
            }

        });

        form.on("submit(submit)",data=>{

            if(!data.field.imgCover){
                layer.tips('请上传封面', '#cover', {
                    tips: [1, '#FF5722']
                });
                return false;
            }

            app.post(url,data.field).then(d=>{
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
