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
<%@include file="../head.jsp" %>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">题目列表</blockquote>
    <a class="layui-btn layui-btn-normal add"><i class="iconfont icon-add"></i>新增题目</a>
    <div class="layui-form">
        <table class="layui-table" >
            <colgroup>
                <col width="10%">
                <col >
                <col width="15%">
                <col width="15%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr>
                <th>题目ID</th>
                <th>题目</th>
                <th>题型</th>
                <th>类型</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.questionId }}</td>
                    <td>{{ item.question }}</td>
                    <td>{{ item.classify }}</td>
                    <td>{{ item.type }}</td>
                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger del"
                               data-url="/auth/activity/task/question/del/{{item.activityId}}/{{item.taskId}}/{{item.id}}"
                               data-name="这个题目么？" data-index="{{index}}">
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
    var $;
    layui.config({base:"/static/js/"}).use(['pagelist','app','laytpl'],function(){
        $ = layui.$,app = layui.app,laytpl = layui.laytpl;

        var  tpl = laytpl($("#tpl").html());

        var initData = function(){

            var load = app.showLoading();

            app.get('/auth/activity/task/question/${activityId}/${taskId}/list').then(d=>{
                tpl.render(d.data, function(html){
                    $("#tbody").html(html);
                });
            },e=>{}).finally(_d=>{app.closeLoading(load)})
        }

        initData();

        $(".add").click(function(){

            var index = layui.layer.open({
                title: '新增题目',
                type: 2, area: ['400px', '500px'],offset: 't',
                content: '/auth/activity/task/question/add/${activityId}/${taskId}',
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                },
                end:function(){
                    initData();
                }
            })
            layui.layer.full(index);
        })

        $('body').on('click','.del',function(){
            var url = $(this).data('url'),name = $(this).data('name')||'该条信息',index= $(this).data('index');
            if (url) {
                app.layerDel('确定删除 '+name +'?',_=>{
                    var ls = app.showLoading();
                    app.get(url).then(d=>{
                        app.layerMessageS(d.message);
                        initData()
                    },e=>{app.layerMessageE(e)}).finally(d=>{app.closeLoading(ls)});
                })
            }
        })


    });
</script>
</body>
</html>
