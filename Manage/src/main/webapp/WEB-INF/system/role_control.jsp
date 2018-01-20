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
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">
<blockquote class="layui-elem-quote layui-quote-nm qute-blue">角色权限菜单控制</blockquote>
<input id="id" type="hidden" value="${id}">
<form class="layui-form" action="">
<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
        <li class="layui-this">菜单分配</li>
        <li>权限分配</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane" id="form_nav" lay-filter="form_nav">
            <script id="tpl_nav" type="text/html">
                {{# layui.each(d,function(index,item){ }}
                <fieldset class="layui-elem-field">
                    <legend>{{item.name}}</legend>
                    <div class="layui-field-box">
                        <div class="layui-form-item">
                            {{# layui.each(item.children,function(indexC,itemC){ }}
                            <input type="checkbox" name="nav[{{itemC.id}}]" title="{{itemC.name}}" value="{{itemC.id}}_{{itemC.parentId}}"
                                   {{# if(itemC.checked){ }}
                                   checked
                                   {{# } }}
                            >
                            {{# });}}
                        </div>
                    </div>
                </fieldset>
                {{# });}}
            </script>
            </div>
        </div>
        <div class="layui-tab-item">
            <div class="layui-form layui-form-pane" id="form_per" lay-filter="form_per">
                <script id="tpl_per" type="text/html">
                    <fieldset class="layui-elem-field">
                        <legend>权限</legend>
                        <div class="layui-field-box">
                            <div class="layui-form-item" >
                                {{# layui.each(d,function(index,item){ }}
                                <input type="checkbox" name="per[{{index}}]" title="{{item.name}}" value="{{item.id}}"
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
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
            <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
        </div>
    </div>
</div>
</form>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','element','laytpl'],function(){
        var $ = layui.$,
            form = layui.form,
            app = layui.app,
            element = layui.element,
            laytpl = layui.laytpl;

        var id = $("#id").val();

        var tplNav = laytpl($("#tpl_nav").html());
        var tplPer = laytpl($("#tpl_per").html());

        if(id){
            var load = app.showLoading();
            app.get('/auth/system/menu/usable/list/'+id).then(d=>{
                tplNav.render(d.data, function(html){
                    $("#form_nav").append(html);
                    form.render('checkbox', 'form_nav')
                });
            },e=>{}).finally(_=>{app.closeLoading(load)});
            app.get('/auth/system/permission/usable/list/'+id).then(d=>{
                tplPer.render(d.data, function(html){
                    $("#form_per").append(html);
                    form.render('checkbox', 'form_per')
                });
            },e=>{}).finally(_=>{});
        }

        form.on("submit(submit)",data=>{
            var navArry =[],perArry=[] ;
            for(var key in data.field){
                if(key.indexOf('nav')>=0){
                    navArry.push(data.field[key])
                }else{
                    perArry.push(data.field[key]);
                }
            }
            var load = app.showLoading();
            app.post('/auth/system/role/navpermission/save/'+id,{navs:navArry.join(','),pers:perArry.join(',')}).then(d=>{
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
