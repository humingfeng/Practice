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
    <h2>角色管理</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="角色名称">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/system/role_handle"
                   data-name="角色新增"><i class="iconfont icon-add"></i>角色新增</a>
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
            <th>角色名称</th>
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
                <td>{{# if (item.status) { }}
                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                       data-url="/auth/system/role/update" data-index="{{index}}" ></a>
                    {{# } else { }}
                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                       data-url="/auth/system/role/update" data-index="{{index}}"></a>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action"
                           data-type="handle" data-url="/auth/jump/system/role_handle?id={{item.id}}" data-name="编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-warm do-action"
                           data-type="handle" data-url="/auth/jump/system/role_control?id={{item.id}}" data-name="权限菜单控制">
                            <i class="iconfont icon-permission"></i>
                            权限菜单控制
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="delete"
                           data-url="/auth/system/role/delete" data-name="{{item.name}},删除会影响拥有该角色的用户" data-index="{{index}}">
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
    layui.config({base:"/static/js/"}).use(['pagelist','app','form'],function(){
        var $ = layui.$,form = layui.form,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/system/role/list'});
        page.render();
    })
</script>
</body>
</html>
