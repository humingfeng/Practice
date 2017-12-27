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
    <h2>教育局管理</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学校名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="学校名称">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/baseinfo/school_handle"
                   data-name="学校新增"><i class="iconfont icon-add"></i>学校新增</a>
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
            <th>学校名称</th>
            <th>所属教育局</th>
            <th>省市区</th>
            <th>联系人</th>
            <th>联系方式</th>
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
                <td>{{ item.eduName }}</td>
                <td>{{ item.province }}/{{ item.city }}/{{ item.area }}</td>
                <td>{{ item.linkMan }}</td>
                <td>{{ item.linkPhone }}</td>
                <td>{{# if (item.status) { }}
                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                       data-url="/auth/baseinfo/school/update" data-index="{{index}}" ></a>
                    {{# } else { }}
                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                       data-url="/auth/baseinfo/school/update" data-index="{{index}}"></a>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small do-action"
                           data-type="handle" data-url="/auth/jump/baseinfo/school_handle?id={{item.id}}" data-name="编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-small layui-btn-danger do-action-page" data-type="deleteUrl"
                           data-url="/auth/baseinfo/school/delete/{{item.id}}" data-name="{{item.name}},删除会影响该教育局下的学校" data-index="{{index}}">
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
                url:'/auth/baseinfo/school/list'});
        page.render();
    })
</script>
</body>
</html>
