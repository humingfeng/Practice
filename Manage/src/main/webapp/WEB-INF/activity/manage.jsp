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
    <h2>活动管理</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">活动类型</label>
                <div class="layui-input-inline">
                    <select name="typeId" id="typeId" lay-filter="typeId" >
                        <option value=''>请选择</option>
                        <c:forEach items="${types}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="classifyId" id="classifyId" lay-filter="classifyId" >

                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="themeId" id="themeId" lay-filter="themeId" >

                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/activity/manage/base/handle"
                   data-name="活动新增"><i class="iconfont icon-add"></i>活动新增</a>
            </div>
        </div>
    </form>
</div>
<!--table-->
<div class="layui-form">
    <table class="layui-table" >
        <thead>
        <tr>
            <th>ID</th>
            <th>活动名称</th>
            <th>活动类型</th>
            <th>活动分类</th>
            <th>活动主题</th>
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
                <td>{{ item.type }}</td>
                <td>{{ item.classify }}</td>
                <td>{{ item.theme }}</td>
                <td>
                    {{# if (item.status==9) { }}
                    <span class="layui-badge layui-bg-orange">创建中</span>
                    {{# } }}
                    {{# if (item.status==3) { }}
                    <span class="layui-badge layui-bg-gray">审核中</span>
                    {{# } }}
                    {{# if (item.status==6) { }}
                    <span class="layui-badge layui-bg-green">已发布</span>
                    {{# } }}
                    {{# if (item.status==2) { }}
                    <span class="layui-badge">报名中</span>
                    {{# } }}
                    {{# if (item.status==5) { }}
                    <span class="layui-badge layui-bg-gray">活动已结束</span>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action"
                           data-type="handle" data-url="/auth/activity/manage/base/handle?id={{item.id}}" data-name="基本信息">
                            <i class="iconfont icon-bianji"></i>
                            基本
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action"
                           data-type="handle" data-url="/auth/activity/manage/base/handle?id={{item.id}}" data-name="基本信息">
                            <i class="iconfont icon-bianji"></i>
                            设置
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="deleteUrl"
                           data-url="/auth/activity/type/delete/{{item.id}}" data-name="{{item.name}},删除会影响拥有该类型的活动" data-index="{{index}}">
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
        var $ = layui.$,form = layui.form,app = layui.app,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/activity/manage/list'});
        page.render();
        var load;
        form.on('select(typeId)', function(data){
            var typeId = data.value;
            load = app.showLoading();
            app.get('/auth/activity/classify/usable/'+typeId).then(d=>{
                $("#classifyId").empty();
                $("#classifyId").append("<option value=''>请选择</option>");
                layui.each(d.data,function(index,item){
                    $("#classifyId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                })
                form.render('select');
            },e=>{}).finally(_=>{app.closeLoading(load)});
        });

        form.on('select(classifyId)', function(data){
            var classifyId = data.value;
            load = app.showLoading();
            app.get('/auth/activity/theme/usable/'+classifyId).then(d=>{
                $("#themeId").empty();
                $("#themeId").append("<option value=''>请选择</option>");
                layui.each(d.data,function(index,item){
                    $("#themeId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                })
                form.render('select');
            },e=>{}).finally(_=>{app.closeLoading(load)});
        });
    })
</script>
</body>
</html>
