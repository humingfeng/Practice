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
    <h2>家长用户</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家长姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="家长姓名">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">注册手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" autocomplete="off" class="layui-input" placeholder="注册手机号" maxlength="11">
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
       data-type="handle" data-url="/auth/jump/personnel/parent_student?id={{d.id}}" data-name="家长学生管理">
        <i class="iconfont icon-bianji"></i>
        查看关联学生
    </a>
</script>

<script type="text/html" id="status">
    {{# if(d.status){ }}
    <div class="layui-unselect layui-form-switch layui-form-onswitch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/personnel/parent/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# }else{ }}
    <div class="layui-unselect layui-form-switch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/personnel/parent/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# } }}
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['tableSearchPage','util'],function(){
        var $ = layui.$,util = layui.util;

        var Page = layui.tableSearchPage({
            pageConfig:{
                isPage:1,
                pageSize: 10,
                pageIndex: 1,
                url: '/auth/personnel/parent/list'
            },
            tableConfig:{
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'name', title: '家长姓名'},
                    {field:'phone', title: '注册手机号'},
                    {field:'createTime',title: '注册时间'},
                    {field:'updateTime',title: '最近登录'},
                    {title: '状态',templet: '#status',width:110,unresize: true},
                    {title: '操作',align:'center', toolbar: '#bar',unresize: true}
                ]],
            }
        })

        Page.render();

    })
</script>
</body>
</html>
