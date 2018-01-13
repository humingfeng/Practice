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
    <h2>教师人员</h2>
</blockquote>

<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学校选择</label>
                <div class="layui-input-inline">
                    <select name="schoolId" id="schoolId">
                        <option value="">请选择学校</option>
                        <c:forEach items="${schoolList}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年级选择</label>
                <div class="layui-input-inline">
                    <select name="periodId" id="periodId">
                        <option value="">年级选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">班级选择</label>
                <div class="layui-input-inline">
                    <select name="classId" id="classId">
                        <option value="">班级选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学生名称</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="name" maxlength="20">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学籍号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="enuNum" maxlength="30">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
                <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
                   data-type="handle" data-url="/auth/jump/personnel/student_handle"
                   data-name="新增学生"><i class="iconfont icon-add"></i>新增学生</a>
                <a class="layui-btn layui-btn-normal layui-btn-warm do-action"
                   data-type="handle" data-url="/auth/jump/personnel/student_export"
                   data-name="批量导入"><i class="iconfont icon-add"></i>批量导入</a>
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
            <th>年级</th>
            <th>班级</th>
            <th>学生名称</th>
            <th>学籍号</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
            <tr>
                <td>{{ item.id }}</td>
                <td>{{ item.schoolName }}</td>
                <td>{{ item.periodName }}</td>
                <td>{{ item.className }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.enuNum }}</td>
                <td>{{# if (item.status) { }}
                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                       data-url="/auth/personnel/student/update" data-index="{{index}}" ></a>
                    {{# } else { }}
                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                       data-url="/auth/personnel/student/update" data-index="{{index}}"></a>
                    {{# } }}
                </td>
                <td>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action"
                           data-type="handle" data-url="/auth/jump/personnel/student_handle?id={{item.id}}" data-name="编辑">
                            <i class="iconfont icon-bianji"></i>
                            编辑
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="deleteUrl"
                           data-url="/auth/personnel/student/del/{{item.id}}" data-name="{{item.name}},删除会影响部分功能，慎重！" data-index="{{index}}">
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
                url:'/auth/personnel/student/list'});
        page.render();



        app.get('/auth/system/dictionary/type/PERIOD').then(d=>{
            layui.each(d.data, function (index, item) {
                if (item.param == "小学" || item.param == "初中" || item.param == "高中") {
                    $("#periodId").append("<option value='" + item.id + "' >" + item.name + "</option>");
                }

            })
            app.get('/auth/system/dictionary/type/CLASS_TYPE').then(d => {
                layui.each(d.data, function (index, item) {
                    $("#classId").append("<option value='" + item.id + "' >" + item.name + "</option>");
                })
                form.render();
            },e=>{});
        },e=>{})
    });
</script>
</body>
</html>
