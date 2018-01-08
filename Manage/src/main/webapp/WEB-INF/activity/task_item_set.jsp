<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/1/2
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../head.jsp"%>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">任务题目设置</blockquote>
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
                <th>类型名称</th>
                <th>分类名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.id }}</td>
                    <td>{{ item.typeName }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{# if (item.status) { }}
                        <a class="iconfont icon-keyong do-action-page" data-type="update"
                           data-url="/auth/activity/classify/update" data-index="{{index}}" ></a>
                        {{# } else { }}
                        <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                           data-url="/auth/activity/classify/update" data-index="{{index}}"></a>
                        {{# } }}
                    </td>
                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs do-action"
                               data-type="handle" data-url="/auth/jump/activity/classify_handle?id={{item.id}}" data-name="编辑">
                                <i class="iconfont icon-bianji"></i>
                                编辑
                            </a>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="deleteUrl"
                               data-url="/auth/activity/classify/delete/{{item.id}}" data-name="{{item.name}},删除会影响拥有该分类的活动" data-index="{{index}}">
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
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['pagelist','app','form'],function(){
        var $ = layui.$,form = layui.form,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/activity/question/list'});
        page.render();
    })
</script>
</body>
</html>
