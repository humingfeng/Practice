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
<body class="childrenBody">

<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动注意事项</blockquote>
    <div class="layui-row">
        <form class="layui-form layui-form-pane">
            <input type="hidden" value="${activityId}" name="activityId" id="activityId">
            <input type="hidden" value="${attention.id}" name="id" id="attentionId">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">注意事项</label>
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
    </div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function() {
        var $ = layui.$, form = layui.form,  laytpl = layui.laytpl,app = layui.app;

        var load;

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


        editor.txt.html('${attention.doc}');

        var id = $("#attentionId").val();


        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);
        });

        form.on('submit(submit)', function(data){

            if(!editor.txt.html()){
                $('.w-e-text-container').css("border-color","#FF5722");
                app.layerMessageE("请输入活动介绍");
                return false;
            }else{
                $('.w-e-text-container').css("border-color","#ccc");
            }
            data.field.doc = editor.txt.html();

            load = app.showLoading();

            if(!Number(id)){
                app.post("/auth/activity/attention/add",data.field).then(d=>{
                    app.layerMessageS(d.message);

                    $("#attentionId").val(d.data);
                    id = d.data;

                    $("#attentionId").val(d.data);

                },e=>{
                    app.layerMessageE(e);
                }).finally(()=>{
                    app.closeLoading(load)
                });

            }else{
                app.post("/auth/activity/attention/update",data.field).then(d=>{
                    app.layerMessageS(d.message);
                },e=>{
                    app.layerMessageE(e);
                }).finally(()=>{
                    app.closeLoading(load)
                });
            }
            return false;
        })



    });
</script>
</body>
</html>
