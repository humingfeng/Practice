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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">分类新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-block">
                        <select name="typeId" lay-verify="required" id="typeId">

                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">分类名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="required" maxlength="20" placeholder="请输入分类名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <input type="hidden" name="icon" id="imgCover">
                    <label class="layui-form-label" style=" height: 132px;  line-height: 112px;">ICON</label>
                    <div class="layui-input-block " id="upload">
                        <img class="layui-upload-img" src="/static/img/noimg.svg" id="cover" width="130" height="130" style="border:1px solid #e6e6e6">
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
        var $ = layui.$,form = layui.form,app = layui.app,upload = layui.upload;


        var id = Number($("#id").val());

        var url = "/auth/activity/classify/add";

        var load = app.showLoading();
        app.get('/auth/activity/type/usable/').then(d=>{

            $("#typeId").empty();
            $("#typeId").append("<option value=''>请选择</option>");
            layui.each(d.data,function(index,item){
                $("#typeId").append("<option value='"+item.id+"' >"+item.value+"</option>");
            })
            form.render('select');
            if(id){
                url = "/auth/activity/classify/update";
                app.get('/auth/activity/classify/'+id).then(d=>{
                    $("#form").initForm(d.data);
                    if(d.data.icon){
                        $("#cover").attr('src',d.data.icon)
                    }
                    form.render('select');
                },e=>{}).finally(_=>{app.closeLoading(load);});
            }else{

                app.closeLoading(load);
            }
        },e=>{});

        upload.render({
            elem: '#upload',url: '/upload/img/classify',
            accept:"images",exts: 'svg|jpg|png|jpeg',size:1024*1024,
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
