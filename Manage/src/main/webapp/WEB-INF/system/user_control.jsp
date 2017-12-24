<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/12/23
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../head.jsp"%>
<body class="childrenBody">
<fieldset class="layui-elem-field"  style="width: 700px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        <p> 用户角色可以分配多个，系统会自动取交集</p>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form layui-form-pane" id="content">
                    <script id="tpl" type="text/html">
                        <fieldset class="layui-elem-field">
                            <legend>角色</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item" >
                                    {{# layui.each(d,function(index,item){ }}
                                    <input type="checkbox" name="role[{{index}}]" title="{{item.name}}" value="{{item.id}}"
                                           {{# if(item.checked){ }}
                                           checked
                                           {{# } }}
                                    >
                                    {{# });}}
                                </div>
                            </div>
                        </fieldset>
                    </script>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                        <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>

<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function(){
        var $ = layui.$,
            form = layui.form,
            app = layui.app,
            laytpl = layui.laytpl;

        var id = $("#id").val();
        var tpl= laytpl($("#tpl").html());

        if(id){
            var load = app.showLoading();
            app.get('/auth/system/role/usable/list/'+id).then(d=>{
                tpl.render(d.data, function(html){
                    $("#content").append(html);
                    form.render()
                });
            },e=>{}).finally(_=>{app.closeLoading(load)});
        }

        form.on("submit(submit)",data=>{
            var roleArry =[];
            for(var key in data.field){
                roleArry.push(data.field[key]);
            }
            var load = app.showLoading();
            app.post('/auth/system/user/role/save/'+id,{roles:roleArry.join(',')}).then(d=>{
                app.layerMessageS(d.message);
                app.time(function(){app.back()});
            },e=>{
                app.layerMessageE(e);
            }).finally(_=>{
                app.closeLoading(load);
            })
            return false;
        })

    })
</script>
</body>
</html>
