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
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动题目管理</blockquote>

    <div class="row" style="overflow: inherit">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">题目类型</label>
                    <div class="layui-input-inline">
                        <select name="type" id="typeId" lay-filter="typeId" >
                            <option value=''>请选择</option>
                            <c:forEach items="${types}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">题目内容</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                    <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                       data-type="handle" data-url="/auth/jump/activity/question_handle"
                       data-name="题目新增"
                    ><i class="iconfont icon-add"></i>题目新增</a>

                </div>
            </div>
        </form>
    </div>

    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="10%">
                <col width="10%">
                <col width="30%">
                <col width="12%">
                <col width="12%">
                <col >
            </colgroup>
            <thead>
            <tr>
                <th>ID</th>
                <th>题目类型</th>
                <th>题目内容</th>
                <th>任务分值</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>
                        {{ item.id }}
                    </td>
                    <td>
                        {{ item.typeName }}
                    </td>
                    <td>{{ item.question}}</td>
                    <td align="center">
                        {{# if(item.classify == 1){ }}
                        <span class="layui-badge layui-bg-blue">客观题</span>
                        {{# } else { }}
                        <span class="layui-badge ">主观题</span>
                        {{# } }}
                    </td>
                    <td>{{# if (item.status) { }}
                        <a class="iconfont icon-keyong do-action-page" data-type="status"
                           data-url="/auth/activity/question/status/{{item.id}}/{{item.status?0:1}}" data-index="{{index}}" ></a>
                        {{# } else { }}
                        <a class="iconfont icon-bukeyong do-action-page" data-type="status"
                           data-url="/auth/activity/question/status/{{item.id}}/{{item.status?0:1}}" data-index="{{index}}"></a>
                        {{# } }}
                    </td>
                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs do-action"
                               data-type="handle" data-url="/auth/jump/activity/question_handle?id={{item.id}}" data-name="编辑">
                                <i class="iconfont icon-bianji"></i>
                                编辑
                            </a>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="deleteUrl"
                               data-url="/auth/activity/question/del/{{item.id}}" data-name=",删除会影响拥有该题目的活动" data-index="{{index}}">
                                <i class="iconfont icon-shanchu"></i>
                                删除
                            </a>
                        </div>
                    </td>
                </tr>
                {{# }); }}
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
                url:'/auth/activity/question/list'});
        page.render();
    })
</script>
</body>
</html>
