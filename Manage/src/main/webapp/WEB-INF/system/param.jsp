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
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>系统参数</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">参数名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="参数名称">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action-page"
                   data-type="handle" data-url="/auth/jump/system/param_handle"
                   data-name="参数新增"><i class="iconfont icon-add"></i>新增参数</a>
                <a class="layui-btn layui-btn-danger newsAdd_btn do-action-page"
                   data-type="confrim" data-url="/auth/system/param/sync/cache"
                   data-name="刷新缓存"><i class="iconfont icon-image"></i>刷新缓存</a>
            </div>
        </div>
    </form>
</div>
<!--table-->

<div class="row">
    <table class="layui-hide" id="table"></table>
</div>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs do-action-page" data-type="handle" data-url="/auth/jump/system/param_handle?id={{d.id}}">
        <i class="iconfont icon-bianji"></i>
        编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs do-action-page"
       data-type="delete" data-name="{{d.name}}" data-url="/auth/system/param/del/{{d.id}}"
    > <i class="iconfont icon-shanchu"></i>删除</a>
</script>
<script type="text/html" id="status">
    {{# if(d.status){ }}
    <div class="layui-unselect layui-form-switch layui-form-onswitch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/system/param/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# }else{ }}
    <div class="layui-unselect layui-form-switch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/system/param/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# } }}
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['tableSearchPage'],function(){
        var $ = layui.$;

        var Page = layui.tableSearchPage({
            pageConfig:{
                isPage:1,
                pageSize: 10,
                pageIndex: 1,
                url: '/auth/system/param/list'
            },
            tableConfig:{
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'name', title: '参数名称',width:200},
                    {field:'param', title: '参数信息'},
                    {title: '状态',templet: '#status',width:110,unresize: true},
                    {title: '操作',width:160,align:'center', toolbar: '#bar',unresize: true}
                ]],
            }
        })

        Page.render();

    })
</script>
</body>
</html>
