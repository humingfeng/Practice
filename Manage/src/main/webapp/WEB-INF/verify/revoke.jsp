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
    <h2>已上线活动</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">活动ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" autocomplete="off" class="layui-input" placeholder="活动ID">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">活动名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="活动名称">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
            </div>
        </div>
    </form>
</div>
<!--table-->

<div class="row">
    <table class="layui-hide" id="table"></table>
</div>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs do-action-page layui-btn-normal"
       data-type="handle" data-url="/auth/jump/verify/activity_view?id={{d.id}}" data-name="预览">
        <i class="iconfont icon-bianji"></i>
        预览</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs do-action-page"
       data-type="confrim" data-name="确定要下线 {{d.name}} 吗？" data-url="/auth/verify/activity/revoke/{{d.id}}"
    > <i class="iconfont icon-shanchu"></i>下线</a>
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
                url: '/auth/verify/activity/revoke/list'
            },
            tableConfig:{
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'name', title: '活动名称',width:300},
                    {field:'type', title: '活动类型',width:300},
                    {field:'classify', title: '活动分类',width:300},
                    {field:'theme', title: '活动主题',width:300},
                    {title: '操作',width:160,align:'center', toolbar: '#bar',unresize: true}
                ]],
            }
        })

        Page.render();

    })
</script>
</body>
</html>
