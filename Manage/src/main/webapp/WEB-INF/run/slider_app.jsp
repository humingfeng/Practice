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
    <h2>APP 轮播广告</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="名称">
                </div>
                <div class="layui-input-inline">
                    <select name="type" lay-verify="empty" lay-verType="tips" placeholder="请选择广告类型" lay-filter="type">
                        <option value=""> 请选择广告类型</option>
                        <option value="1"> 内容模版</option>
                        <option value="2"> 第三方页面</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal do-action-page"
                   data-type="handle" data-url="/auth/jump/run/slider_app_handle"
                   data-name="轮播新增"><i class="iconfont icon-add"></i>新增轮播</a>
            </div>
        </div>
    </form>
</div>
<!--table-->

<div class="row">
    <table class="layui-hide" id="table"></table>
</div>

<script type="text/html" id="status">
    {{# if(d.status){ }}
    <div class="layui-unselect layui-form-switch layui-form-onswitch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/run/slider/app/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# }else{ }}
    <div class="layui-unselect layui-form-switch do-action-page"
         lay-skin="_switch"
         data-type="status" data-url="/auth/run/slider/app/update" data-id="{{d.id}}" data-status="{{d.status}}"
    ><em>可用</em><i></i></div>
    {{# } }}
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs do-action-page" data-type="handle" data-url="/auth/jump/run/slider_app_handle?id={{d.id}}">
        <i class="iconfont icon-bianji"></i>
        编辑
    </a>
    {{# if(d.type==1){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs do-action-page" data-type="handle" data-url="/auth/jump/run/slider_app_template?id={{d.id}}">
        <i class="iconfont icon-bianji"></i>
        模版页
    </a>
    {{# } }}
    {{# if(d.statu==0){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs do-action-page"
       data-type="delete" data-name="{{d.name}}" data-url="/auth/run/slider/app/del/{{d.id}}">
        <i class="iconfont icon-shanchu"></i>
        删除
    </a>
    {{# } }}
</script>
<script type="text/html" id="img">
    <img src="{{d.imgCover}}" alt="">
</script>
<script type="text/html" id="type">
    {{# if(d.type == 1){ }}
    <span class="layui-badge layui-bg-green mt5">内容模版</span>
    {{# } }}
    {{# if(d.type == 2){ }}
    <span class="layui-badge layui-bg-blue mt5">第三方页面</span>
    {{# } }}
</script>
<script type="text/html" id="tag">
    {{# if(d.tag == 1){ }}
    <span class="layui-badge layui-bg-orange mt5">活动页</span>
    {{# } }}
    {{# if(d.tag == 2){ }}
    <span class="layui-badge layui-bg-orange mt5">统计页</span>
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
                url: '/auth/run/slider/app/list'
            },
            tableConfig:{
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'name', title: '广告名称',width:200},
                    {field:'imgCover', title: '广告封面',templet:'#img',width:120,unresize: true},
                    {field:'type', title: '广告类型',templet:'#type',width:120,unresize: true},
                    {field:'tag', title: '投放位置',templet:'#tag',width:120,unresize: true},
                    {field:'url', title: '广告URL'},
                    {title: '状态',templet: '#status',width:110,unresize: true},
                    {title: '操作',width:250, toolbar: '#bar',unresize: true}
                ]],
            }
        })

        Page.render();

    })
</script>
</body>
</html>
