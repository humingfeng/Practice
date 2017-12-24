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
<%@include file="../head.jsp"%>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>管理人员</h2>
</blockquote>
<div class="row">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="account" autocomplete="off" class="layui-input" placeholder="用户名称">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" autocomplete="off" class="layui-input" placeholder="用户手机">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/system/user_handle"
                   data-name="新增用户"><i class="iconfont icon-add"></i>新增用户</a>
            </div>
        </div>
    </form>
</div>
<!--table-->
<div class="layui-form">
    <table class="layui-table" >
        <colgroup>
            <col width="10%">
            <col width="8%">
            <col width="10%">
            <col width="12%">
            <col width="15%">
            <col width="5%">
            <col >
        </colgroup>
        <thead>
        <tr>
            <th>ID</th>
            <th>头像</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>昵称</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
            <tr>
                <td>{{ item.id }}</td>
                <td>
                    <img src="{{item.headImg}}" width="50">
                </td>
                <td>{{ item.account }}</td>
                <td>{{ item.phone }}</td>
                <td>{{ item.nickName }}</td>
                <td>{{# if (item.status==1) { }}
                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                       data-url="/auth/system/user/update" data-index="{{index}}" ></a>
                    {{# } else { }}
                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                       data-url="/auth/system/user/update" data-index="{{index}}"></a>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small do-action" data-type="handle" data-url="/auth/jump/system/user_handle?id={{item.id}}" data-name="用户编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small layui-btn-warm do-action"
                           data-type="handle" data-url="/auth/jump/system/user_control?id={{item.id}}" data-name="分配角色">
                            <i class="iconfont icon-permission"></i>
                            分配角色
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small layui-btn-danger do-action-page" data-type="delete"
                           data-url="/auth/system/user/delete" data-name="{{item.account}}" data-index="{{index}}">
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
    layui.config({base:"/static/js/"}).use(['pagelist','app'],function(){
        var $ = layui.$,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/system/user/list'});
        page.render();
    })
</script>
</body>
</html>
