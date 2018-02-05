<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/2/5
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>家长关联的学生</h2>
</blockquote>

<!--table-->
<div class="layui-row" style="overflow: inherit" id="content">
    <script id="tpl" type="text/html">

        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">家长姓名</label>
                    <div class="layui-input-inline">
                        <input type="text"  class="layui-input" value="{{d.parentName}}" disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">注册手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="{{d.phone}}" disabled>
                    </div>
                </div>
            </div>
        </form>
        <table class="layui-table" >
            <thead>
            <tr>
                <th>学生名称</th>
                <th>学校</th>
                <th>年级</th>
                <th>班级</th>
                <th>关系</th>
                <th>绑定时间</th>
            </tr>
            </thead>
            <tbody >
                {{#  layui.each(d.list, function(index, item){ }}
                <tr>
                    <td>{{ item.studentName }}</td>
                    <td>{{ item.schoolName }}</td>
                    <td>{{ item.periodName }}</td>
                    <td>{{ item.className }}</td>
                    <td>{{ item.relation }}</td>
                    <td>{{ item.bindTime }}</td>
                </tr>
                {{#  }); }}
            </tbody>
        </table>
    </script>
</div>


<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function() {
        var $ = layui.$, form = layui.form, laytpl = layui.laytpl, app = layui.app;


        var tpl = laytpl($('#tpl').html());


        var load = app.showLoading();
        app.get('/auth/personnel/parent/student/list/${id}').then(d=>{

            tpl.render(d.data,function(html){
                $("#content").html(html);
            })

        },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)})

    });


</script>
</body>
</html>
