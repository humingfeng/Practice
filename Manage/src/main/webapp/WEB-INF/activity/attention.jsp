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

<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动注意事项</blockquote>
    <div class="layui-row">
        <form class="layui-form layui-form-pane">
            <input type="hidden" value="${activityId}" name="activityId" id="activityId">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="type" id="user" lay-verify="required" >
                            <option value=''>请选择</option>
                            <c:forEach items="${types}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline" style="width:600px;">
                    <label class="layui-form-label">注意事项</label>
                    <div class="layui-input-block" >
                        <input name="doc" lay-verify="required" maxlength="200" class="layui-input" placeholder="请输入注意事项" />
                    </div>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-normal" lay-submit="return false" lay-filter="add">添加</a>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-row">
        <table class="layui-table">
            <colgroup>
                <col >
                <col width="10%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr>
                <th>注意事项</th>
                <th>类型</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.doc }}</td>
                    <td>{{ item.typeStr }}</td>

                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" data-type="del" data-index="{{index}}">
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
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function() {
        var $ = layui.$, form = layui.form,  laytpl = layui.laytpl,app = layui.app;

        var load,data=[];


        var tpl = laytpl($("#tpl").html());

        var initList = function(){
            load = app.showLoading();
            app.get('/auth/activity/attention/list/${activityId}').then(d=>{
                data = d.data;
                tpl.render(data,function(html){
                    $("#tbody").html(html);
                });
            },e=>{}).finally(_=>{app.closeLoading(load);})
        }

        initList();

        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);
        });

        form.on('submit(add)', function(data){

            load = app.showLoading();

            app.post("/auth/activity/attention/add",data.field).then(d=>{
                initList();
            },e=>{
                app.layerMessageE(e);
            }).finally(_=>{app.closeLoading(load)})


            return false;
        })


        $('body').on("click", ".layui-btn-xs", function (e) {
            var index = $(this).data("index");

            var item = data[index];

            app.layerDel('确定移除该条注意事项么?',_=>{
                load = app.showLoading();
                app.post('/auth/activity/attention/del/'+item.id).then(d=>{
                    app.layerMessageS(d.message);
                    initList()
                },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)});
            })

            layui.stope(e);//阻止冒泡事件
        });
    });
</script>
</body>
</html>
