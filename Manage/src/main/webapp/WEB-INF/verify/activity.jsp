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
<%@include file="../head.jsp"%>
<style>
    .layui-form-label{
        width: auto;
    }
</style>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>活动审核</h2>
</blockquote>

<div class="row" style="overflow: inherit">
</div>
<!--table-->
<div class="layui-form">
    <table class="layui-table" >
        <thead>
        <tr>
            <th>ID</th>
            <th>活动名称</th>
            <th>活动类型</th>
            <th>活动分类</th>
            <th>活动主题</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
            <tr>
                <td>{{ item.id }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.type }}</td>
                <td>{{ item.classify }}</td>
                <td>{{ item.theme }}</td>
                <td>
                    {{# if (item.status==3) { }}
                    <span class="layui-badge layui-bg-danger">待审核</span>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action layui-btn-normal"
                           data-type="handle" data-url="/auth/jump/verify/activity_view?id={{item.id}}" data-name="基本信息">
                            <i class="iconfont icon-bianji"></i>
                            审核
                        </a>
                    </div>

                </td>
            </tr>
            {{#  }); }}
        </script>
        </tbody>
    </table>
</div>
<!--分页-->
<div id="page" style="text-align: right"></div>

<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['pagelist','app','form'],function(){
        var $ = layui.$,form = layui.form,app = layui.app,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/verify/activity/list'});
        page.render();

    })
</script>
</body>
</html>
