<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/10/10
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<style>
    .title {
        background: #f9f9f9;
        width: 100px;
    }
</style>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>订单详情</h2>
</blockquote>
<%--search--%>
<div class="row" style="overflow: inherit">
    <div class="layui-row" id="content">
        <script id="tpl" type="text/html">
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="4">订单信息</th>
                </tr>
                <thead>
                <tbody>
                    <tr>
                        <td class="title">订单编号</td>
                        <td>{{d.orderNum}}</td>
                        <td class="title">订单名称</td>
                        <td>{{d.orderName}}</td>
                    </tr>
                    <tr>
                        <td class="title">订单金额</td>
                        <td>{{d.price}}</td>
                        <td class="title">订单状态</td>
                        <td>{{d.status}} {{d.methodName}}</td>
                    </tr>
                    <tr>
                        <td class="title">创建时间</td>
                        <td>{{d.createTime}}</td>
                        <td class="title">支付时间</td>
                        <td>{{d.payTime}}</td>
                    </tr>
                </tbody>
            </table>
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="4">报名信息</th>
                </tr>
                <thead>
                <tbody>
                    <tr>
                        <td class="title">学生信息</td>
                        <td>{{d.studentName}} {{d.schoolName}} {{d.periodName}} {{d.className}} </td>
                    </tr>
                    <tr>
                        <td class="title">家长信息</td>
                        <td>{{d.parentName}} {{d.phone}} </td>
                    </tr>
                </tbody>
            </table>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th colspan="4">活动信息</th>
                    </tr>
                <thead>
                <tbody>
                    <tr>
                        <td class="title">活动名称</td>
                        <td>{{d.activityName}} </td>
                    </tr>
                    <tr>
                        <td class="title">活动属性</td>
                        <td>{{d.type}} {{d.classify}} {{d.theme}}</td>
                    </tr>
                    <tr>
                        <td class="title">活动适用年级</td>
                        <td>
                            {{# layui.each(d.applys,function(j,i){ }}
                                {{i}}
                            {{# }) }}

                        </td>
                    </tr>
                    <tr>
                        <td class="title">活动时间段</td>
                        <td>{{d.beginEndTime}} </td>
                    </tr>
                </tbody>
            </table>
        </script>
    </div>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','laytpl'],function() {
        var $ = layui.$, laytpl = layui.laytpl, app = layui.app;

        var load = app.showLoading();

        var tpl = laytpl($("#tpl").html());

        app.get('/auth/finance/order/${id}').then(d=>{
            tpl.render(d.data, function (html) {
                $("#content").html(html);
            });
        },e=>{app.layerMessageS(e)}).finally(()=>{app.closeLoading(load)})

    })
</script>
</body>
</html>
