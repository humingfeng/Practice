<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/9/30
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Pactice-Login</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/static/css/login.css" media="all" />
</head>
<body>
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
    <source src="https://t.alipayobjects.com/images/T1T78eXapfXXXXXXXX.mp4" type="video/mp4">
</video>
<div class="video_mask"></div>
<div class="login">
    <h1>Practice-CMS</h1>
    <form class="layui-form">
        <div class="layui-form-item">
            <input class="layui-input" name="account" placeholder="手机号" lay-verify="required|phone|number" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="pass" placeholder="密码" lay-verify="required|pass" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" maxlength="4" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="/verifyCode" width="116" height="36" onclick="this.src='/verifyCode?'+Math.random()"></div>
        </div>
        <button class="layui-btn login_btn" lay-submit="return false" lay-filter="login">登录</button>
    </form>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>
