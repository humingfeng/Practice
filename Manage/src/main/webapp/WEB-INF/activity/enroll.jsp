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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动报名设置</blockquote>

    <div class="layui-form layui-form-pane" id="form">
        <input type="hidden" id="activityId" value="${activityId}">
        <input type="hidden" name="id" value="${enroll.id}" id="id">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label ">电话信息</label>
                <div class="layui-input-block ">
                    <input type="checkbox" name="phone" title="采集" lay-filter="phone"
                           <c:if test="${enroll.phone==1}">checked</c:if>  >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家长姓名</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="parentName" title="采集"
                           <c:if test="${enroll.parentName==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学生姓名</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="name" title="采集"
                           <c:if test="${enroll.name==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="idNum" title="采集"
                           <c:if test="${enroll.idNum==1}">checked</c:if> >
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">护照</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="passport" title="采集"
                           <c:if test="${enroll.passport==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">体重</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="weight" title="采集"
                           <c:if test="${enroll.weight==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身高</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="height" title="采集"
                           <c:if test="${enroll.height==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="sex" title="采集"
                           <c:if test="${enroll.sex==1}">checked</c:if> >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">民族</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="nation" title="采集"
                           <c:if test="${enroll.nation==1}">checked</c:if> >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="birthday" title="采集"
                           <c:if test="${enroll.birthday==1}">checked</c:if> >
                </div>
            </div>
        </div>
    </div>

</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base: "/static/js/"}).use(['app', 'form', 'jsonToForm'], function () {
        var $ = layui.$, form = layui.form, app = layui.app;

        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);

        });
        var load, id = $("#id").val();

        form.on("checkbox", function (data) {
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            var key = data.elem.name;
            var param = {};
            param['id'] = id;
            param[key] = status;
            load = app.showLoading();
            app.post('/auth/activity/enroll/update', param).then().finally(_ => {
                app.closeLoading(load)
            })
        })


    });
</script>
</body>
</html>
