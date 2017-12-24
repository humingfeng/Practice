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
    <h2>版本记录</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="time" id="time" class="layui-input" placeholder="时间">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/system/version_handle"
                   data-name="版本新增"><i class="iconfont icon-add"></i>版本新增</a>
            </div>
        </div>
    </form>
</div>
<!--table-->
<div class="layui-form">
    <table class="layui-table" >
        <colgroup>
            <col width="10%">
            <col width="15%">
            <col width="15%">
            <col >
        </colgroup>
        <thead>
        <tr>
            <th>ID</th>
            <th>版本号</th>
            <th>版本概述</th>
            <th>负责人</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
            <tr>
                <td>{{ item.id }}</td>
                <td>{{ item.versionNum }}</td>
                <td>{{ item.versionGeneral }}</td>
                <td>{{ item.leader }}</td>
                <td>{{ item.createTime }}</td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small do-action"
                           data-type="handle" data-url="/auth/jump/system/version_handle?id={{item.id}}" data-name="编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small layui-btn-danger do-action-page" data-type="deleteUrl"
                           data-url="/auth/system/version/delete/{{item.id}}" data-name="{{item.versionNum}}" data-index="{{index}}">
                            <i class="iconfont icon-shanchu"></i>
                            删除
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
    layui.config({base:"/static/js/"}).use(['pagelist','app','form','laydate'],function(){
        var $ = layui.$,form = layui.form,laydate = layui.laydate
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/system/version/list'});
        page.render();

        laydate.render({
            elem: '#time'
            ,range: true
        });
    })
</script>
</body>
</html>
