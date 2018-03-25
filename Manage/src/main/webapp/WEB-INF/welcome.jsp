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
<jsp:include page="/head"></jsp:include>
<style>
    h3{font-size: 18px;font-weight: bold}
</style>
<body class="childrenBody">
<div class="panel_box row">
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon">
                <i class="iconfont icon-huodong-" data-icon="icon-shangjia1"></i>
            </div>
            <div class="panel_word newMessage">
                <span data-to="${count.activityCount}">--</span>
                <cite>活动总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#FF5722;">
                <i class="iconfont icon-fenlei" data-icon="icon-fenlei"></i>
            </div>
            <div class="panel_word userAll">
                <span data-to="${count.classifyCount}">--</span>
                <cite>分类总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#009688;">
                <i class="iconfont icon-zhuti" data-icon="icon-cheyuan-fu"></i>
            </div>
            <div class="panel_word userAll">
                <span data-to="${count.themeCount}">--</span>
                <cite>主题总数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#5FB878;">
                <i class="iconfont icon-zhuceyonghu" data-icon="icon-yly_qiugou"></i>
            </div>
            <div class="panel_word imgAll">
                <span data-to="${count.parentCount}">--</span>
                <cite>注册用户</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#F7B824;">
                <i class="iconfont icon-jiaoyujidi" data-icon="icon-zhihuan"></i>
            </div>
            <div class="panel_word waitNews">
                <span data-to="${count.basesCount}">--</span>
                <cite>基地数量</cite>
            </div>
        </a>
    </div>
    <div class="panel col max_panel">
        <a href="javascript:;" data-url="">
            <div class="panel_icon" style="background-color:#2F4056;">
                <i class="iconfont icon-shenhe" data-icon="icon-anquan"></i>
            </div>
            <div class="panel_word allNews">
                <span data-to="${count.waitVerifyCount}">--</span>
                <em>待审核</em>
            </div>
        </a>
    </div>
</div>
<%--<blockquote class="layui-elem-quote explain" style="margin-left: 10px;">--%>
    <%--<p>1</p>--%>
    <%--<p>2</p>--%>
    <%--<p>3</p>--%>
<%--</blockquote>--%>
<div class="row">

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
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">更新日志</blockquote>
        <div class="layui-elem-quote layui-quote-nm ">
            <ul class="layui-timeline">
                <c:forEach items="${versionLog}" var="item">
                <li class="layui-timeline-item">
                    <i class="iconfont icon-riqi layui-timeline-axis"></i>
                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">${item.time}  # ${item.version}（${item.title}） - ${item.author}</h3>
                        <blockquote class="layui-elem-quote " style="border-left: 5px solid #ff5823">
                        <c:forEach items="${item.items}" var="child">
                            <p>* ${child.title}</p>
                        </c:forEach>
                        </blockquote>
                    </div>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(["dynamic.count"],function(){
        var $ = layui.$;
        $(".panel_word span").each(function () {
            $(this).countTo();
        });
//        $(".panel a").bind("click",function(){
//            if($(this).data('url')){
//                parent.window.tabOpen($(this).data('url'))
//            }
//        })
    })
</script>
</body>
</html>
