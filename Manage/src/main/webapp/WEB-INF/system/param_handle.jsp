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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">系统参数新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">参数名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="empty" lay-verType="tips" maxlength="20" placeholder="请输入参数名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">参数信息</label>
                    <div class="layui-input-block">
                        <input type="text" name="param" class="layui-input" lay-verify="empty" lay-verType="tips" maxlength="500" placeholder="请输入参数信息">
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
        var $ = layui.$,
            form = layui.form,
            app = layui.app,load;

        form.verify({
            empty: function(value, item) {
                if(!value){
                    return $(item).attr('placeholder');
                }
            }
        });

        var id = $("#id").val();

        var url = "/auth/system/param/add";

        if(id){
            load = app.showLoading();
            url = "/auth/system/param/update";
            app.get('/auth/system/param/'+id).then(d=>{
                $("#form").initForm(d.data);
                form.render('select');
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
