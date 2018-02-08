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
    <h2>订单管理</h2>
</blockquote>
<%--search--%>
<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">订单编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="orderNum" autocomplete="off" class="layui-input" placeholder="订单编号" maxlength="20">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="userId" autocomplete="off" class="layui-input" placeholder="用户ID" maxlength="11">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">下单时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="timeDiff" id="timeDiff" class="layui-input"  placeholder="请选择活动时间段">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">订单状态</label>
                <div class="layui-input-inline">
                    <select name="status">
                        <option value="">请选择状态</option>
                        <option value="0">不可用</option>
                        <option value="1">等待支付</option>
                        <option value="2">已支付</option>
                        <option value="3">用户取消</option>
                        <option value="4">支付超时取消</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
            </div>
        </div>
    </form>
</div>
<%--table--%>
<div class="row">
    <table class="layui-hide" id="table"></table>
</div>
<script type="text/html" id="status">
    {{#  if(d.status === 0){ }}
    <span class="layui-badge layui-bg-orange">不可用</span>
    {{#  } }}
    {{#  if(d.status === 1){ }}
    <span class="layui-badge layui-bg-orange">等待支付</span>
    {{#  } }}
    {{#  if(d.status === 2){ }}
    <span class="layui-badge layui-bg-green">已支付</span>
    {{#  } }}
    {{#  if(d.status === 4){ }}
    <span class="layui-badge layui-bg-danger">订单超时取消</span>
    {{#  } }}
    {{#  if(d.status === 3){ }}
    <span class="layui-badge layui-bg-danger">用户取消</span>
    {{#  } }}
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs do-action-page layui-btn-normal"
       data-type="handle" data-url="/auth/jump/finance/order_detail?id={{d.id}}" data-name="订单详情">
        <i class="iconfont icon-bianji"></i>
        订单详情
    </a>
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['tableSearchPage','laydate'],function(){
        var $ = layui.$,laydate = layui.laydate;

        laydate.render({elem: '#timeDiff',type:'datetime',range: true});

        var Page = layui.tableSearchPage({
            pageConfig:{
                isPage:1,
                pageSize: 10,
                pageIndex: 1,
                url: '/auth/finance/order/list'
            },
            tableConfig:{
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'orderNum', title: '订单编号',width:180},
                    {field:'orderName', title: '订单名称',width:200},
                    {field:'createTime',title: '下单时间'},
                    {field:'priceStr',title: '订单金额'},
                    {field:'userName',title: '下单用户'},
                    {field:'payTime',title: '支付时间'},
                    {field:'methodName',title: '支付方式'},
                    {title: '状态',templet: '#status',width:120,unresize: true},
                    {title: '操作',align:'center', toolbar: '#bar',unresize: true}
                ]],
            }
        })

        Page.render();

    })
</script>
</body>
</html>
