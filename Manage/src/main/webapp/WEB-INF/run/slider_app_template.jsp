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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">APP 轮播模版页面</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="sliderId" value="${id}" id="sliderId">
                <input type="hidden" name="id" value="0" id="id">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">页面内容</label>
                    <div class="layui-input-block">
                        <div id="edit"style="height:600px;max-height:600px;">

                        </div>
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
<script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','upload'],function(){
        var $ = layui.$,
            form = layui.form,
            app = layui.app,load;

        var editor = new window.wangEditor('#edit');

        editor.customConfig.uploadFileName = 'file'
        editor.customConfig.uploadImgServer = '/upload/img/slider_introduce';
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


        load = app.showLoading();

        var url = '/auth/run/slider/app/content/save';

        app.get('/auth/run/slider/app/content/${id}').then(d=>{
            if(d.data.id){
                $("#id").val(d.data.id);
                editor.txt.html(d.data.content);
                url = '/auth/run/slider/app/content/update';
            }
            $('.w-e-text-container').css('height','500px');
        },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)})





        form.on("submit(submit)",data=>{
            if(!editor.txt.html()){
                $('.w-e-text-container').css("border-color","#FF5722");
                app.layerMessageE("请输入活动介绍");
                return false;
            }else{
                $('.w-e-text-container').css("border-color","#ccc");
            }
            data.field.content = editor.txt.html();

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
