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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        任务题目设置
        <p><small>重复添加系统会自动排查</small></p>
    </blockquote>
    <div class="row" style="overflow: inherit">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">题目类型</label>
                    <div class="layui-input-inline">
                        <select name="type" id="type" lay-filter="type" >
                            <option value=''>请选择</option>
                            <c:forEach items="${options}" var="item">
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
                    <a class="layui-btn layui-btn-danger" id="add">添加</a>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-form">
        <table class="layui-table" >
            <colgroup>
                <col >
                <col width="15%">
                <col width="15%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr>
                <th>题目</th>
                <th>题型</th>
                <th>类型</th>
                <th>选择</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.question }}</td>
                    <td>{{ item.classify }}</td>
                    <td>{{ item.type }}</td>
                    <td>
                        <div class="layui-form-item" style="margin: 0;">
                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" style="margin: 0;" data-id="{{item.id}}">
                                <i class="layui-icon"></i>
                            </div>
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
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['pagelist','app','form'],function(){
        var $ = layui.$,
            app = layui.app,
            page = layui.pagelist({
                isPage:1,
                pageIndex:1,
                pageSize:10,
                url:'/auth/activity/question/usable/list'});
        page.render();

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        $('body').on("click", ".layui-form-checkbox", function (e) {

            if($(this).hasClass('layui-form-checked')){
                $(this).removeClass('layui-form-checked');
            }else{
                $(this).addClass('layui-form-checked');
            }

        })

        $('#add').click(function(){
            var checks = [];
            $('.layui-form-checked').each(function(index,item){
                checks.push($(item).data('id'));
            })
            if(!checks.length){
                app.layerMessageE('请选择题目！');
                return;
            }
            var load = app.showLoading();
            app.post('/auth/activity/task/question/add/${activityId}/${taskId}/save',{checkeds:checks.join(',')}).then(d=>{
                app.layerMessageS(d.message);

                app.time(function(){
                    parent.layer.close(index);
                })


            },e=>{app.layerMessageE(e)}).finally(_d=>{app.closeLoading(load)});
        })

    })
</script>
</body>
</html>
