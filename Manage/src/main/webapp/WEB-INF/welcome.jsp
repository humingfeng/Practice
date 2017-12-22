<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/10/9
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="head.jsp"%>
<style>
    h3{font-size: 18px;font-weight: bold}
</style>
<body class="childrenBody">
<div class="panel_box row">
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon">
                <i class="iconfont icon-shangjia1" data-icon="icon-shangjia1"></i>
            </div>
            <div class="panel_word newMessage">
                <span>23</span>
                <cite>商户总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#FF5722;">
                <i class="iconfont icon-geren" data-icon="icon-geren"></i>
            </div>
            <div class="panel_word userAll">
                <span>100</span>
                <cite>用户总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#009688;">
                <i class="iconfont icon-cheyuan-fu" data-icon="icon-cheyuan-fu"></i>
            </div>
            <div class="panel_word userAll">
                <span>1000</span>
                <cite>车源总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#5FB878;">
                <i class="iconfont icon-yly_qiugou" data-icon="icon-yly_qiugou"></i>
            </div>
            <div class="panel_word imgAll">
                <span>500</span>
                <cite>求购总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#F7B824;">
                <i class="iconfont icon-zhihuan" data-icon="icon-zhihuan"></i>
            </div>
            <div class="panel_word waitNews">
                <span>100</span>
                <cite>置换总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col max_panel">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#2F4056;">
                <i class="iconfont icon-anquan" data-icon="icon-anquan"></i>
            </div>
            <div class="panel_word allNews">
                <span>50</span>
                <em>待审核</em>
            </div>
        </a>
    </div>
</div>
<blockquote class="layui-elem-quote explain">
    <p>1</p>
    <p>2</p>
    <p>3</p>
</blockquote>
<div class="row">
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">更新日志</blockquote>
        <div class="layui-elem-quote layui-quote-nm">
            <c:forEach items="${versionLog}" var="item">
                <h3># ${item.version}（${item.title}） - ${item.author}  - ${item.time}</h3>
                <c:forEach items="${item.items}" var="child">
                    <p>* ${child.title}</p>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">系统基本参数</blockquote>
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col>
            </colgroup>
            <tbody>
            <tr>
                <td>Java的运行环境</td>
                <td class="version">${system.javaVersion}</td>
            </tr>
            <tr>
                <td>Java环境供应商</td>
                <td class="version">${system.javaVendor}</td>
            </tr>
            <tr>
                <td>操作系统名称</td>
                <td class="author">${system.osName}</td>
            </tr>
            <tr>
                <td>操作系统架构</td>
                <td class="author">${system.osArch}</td>
            </tr>
            <tr>
                <td>操作系统版本</td>
                <td class="version">${system.osVersion}</td>
            </tr>
            <tr>
                <td>数据库版本</td>
                <td class="dataBase">${system.mysqlVersion}</td>
            </tr>
            <tr>
                <td>当前系统版本</td>
                <td class="version">${system.curVersion}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
</body>
</html>
