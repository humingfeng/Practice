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
<div class="layui-tab layui-tab-card" lay-filter="tab">
    <ul class="layui-tab-title ">
        <li class="layui-this"> 活动页</li>
        <li> 统计页</li>
    </ul>
    <div class="layui-tab-content clildFrame">
        <div class="layui-tab-item layui-show " style="height: 600px;">
            <iframe src="/auth/run/slider/app/order/drag/1" ></iframe>
        </div>
        <div class="layui-tab-item clildFrame" style="height: 600px;">
            <iframe src="/auth/run/slider/app/order/drag/2"></iframe>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['element'],function(){

    })
</script>
</body>
</html>
