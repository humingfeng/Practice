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
    .layui-form-label{
        width: auto;
    }
</style>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>数据字典</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">字典类型</label>
                <div class="layui-input-inline">
                    <select name="parentId" lay-filter="dicType" >
                        <option value=""></option>
                        <c:forEach items="${parent}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">字典名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="字典名称">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/system/dictionary_handle"
                   data-name="字典新增"><i class="iconfont icon-add"></i>字典新增</a>
                <a class="layui-btn layui-btn-danger newsAdd_btn do-action-page"
                   data-type="clearCache" data-url="/auth/system/dictionary/reset/cache"
                   data-name="刷新缓存"><i class="iconfont icon-image"></i>刷新缓存</a>
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
            <col width="20%">
            <col width="10%">
            <col >
        </colgroup>
        <thead>
        <tr>
            <th>ID</th>
            <th>字典名称</th>
            <th>字典类型</th>
            <th>附加参数</th>
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
                <td>{{ item.keyword }}</td>
                <td>{{ item.param }}</td>
                <td>{{# if (item.status) { }}
                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                       data-url="/auth/system/dictionary/update" data-index="{{index}}" ></a>
                    {{# } else { }}
                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                       data-url="/auth/system/dictionary/update" data-index="{{index}}"></a>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action"
                           data-type="handle" data-url="/auth/jump/system/dictionary_handle?id={{item.id}}" data-name="编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="delete"
                           data-url="/auth/system/dictionary/delete" data-name="{{item.name}}" data-index="{{index}}">
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
                url:'/auth/system/dictionary/list'});
        page.render();
    })
</script>
</body>
</html>
