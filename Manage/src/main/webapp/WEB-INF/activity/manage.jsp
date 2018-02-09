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
<jsp:include page="/head"></jsp:include>
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
                   data-name="活动创建"><i class="iconfont icon-add"></i>活动创建</a>
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
                    {{# if (item.status==8) { }}
                    <span class="layui-badge">待提交</span>
                    {{# } }}
                    {{# if (item.status==7) { }}
                    <span class="layui-badge layui-bg-danger">审核不通过</span>
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
                    {{# if (item.status==1) { }}
                    <span class="layui-badge layui-bg-green">进行中</span>
                    {{# } }}
                    {{# if (item.status==5) { }}
                    <span class="layui-badge layui-bg-gray">活动已结束</span>
                    {{# } }}
                </td>
                <td>
                    {{# if(item.status==8 || item.status==9 || item.status==7){ }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs do-action layui-btn-normal"
                           data-type="handle" data-url="/auth/activity/manage/base/handle?id={{item.id}}" data-name="基本信息">
                            基本
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs  do-action layui-btn-normal"
                           data-type="handle" data-url="/auth/activity/manage/set/{{item.id}}" data-name="活动设置">
                            设置
                        </a>
                    </div>
                    {{# } }}
                    {{# if(item.status==8){ }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs sub" data-id="{{item.id}}">
                            提交审核
                        </a>
                    </div>
                    {{# } }}
                    {{# if(item.status==9 || item.status==7){ }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-warm check" data-id="{{item.id}}">
                            检查
                        </a>
                    </div>
                    {{# } }}
                    {{# if(item.status==9 || item.status == 8 || item.status==7){ }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="deleteUrl"
                           data-url="/auth/activity/type/delete/{{item.id}}" data-name="{{item.name}},删除会影响拥有该类型的活动" data-index="{{index}}">
                            删除
                        </a>
                    </div>
                    {{# } }}
                    {{# if (item.status==7) { }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs reason" data-reason="{{item.reason}}">
                            原因
                        </a>
                    </div>
                    {{# } }}
                    {{# if (item.status==6||item.status==2) { }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs  do-action layui-btn-normal"
                           data-type="handle" data-url="/auth/jump/activity/activity_view?id={{item.id}}" data-name="活动预览">
                            预览
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs  do-action"
                           data-type="handle" data-url="/auth/jump/activity/enroll_list?id={{item.id}}" data-name="报名列表">
                            报名列表
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger offline" data-id="{{item.id}}">
                            下线
                        </a>
                    </div>
                    {{# } }}
                    {{# if(item.status==1){ }}
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs  do-action layui-btn-normal"
                           data-type="handle" data-url="/auth/jump/activity/activity_view?id={{item.id}}" data-name="活动预览">
                            预览
                        </a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs  do-action"
                           data-type="handle" data-url="/auth/jump/activity/enroll_list?id={{item.id}}" data-name="报名列表">
                            人员列表
                        </a>
                    </div>
                    {{# } }}
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


        $("body").on("click",'.check',function(){
            var id = $(this).data("id");
            load = app.showLoading();
            app.get('/auth/activity/over/check/'+id).then(d=>{
                app.layerMessageS(d.message);
                page.getList();
            },e=>{
                app.layerAlertE(e);
            }).finally(_=>{app.closeLoading(load)})
        });

        $("body").on('click','.sub',function(){
           var id = $(this).data('id');

            app.layerConfirm('确定提交审核吗?',_=>{
                app.get('/auth/activity/to/verify/'+id).then(r=>{

                    app.layerMessageS(r.message);
                    page.getList();
                },e=>{
                    app.layerMessageE(e);
                })
            })
        });

        $('body').on('click','.reason',function(){
            var reason = $(this).data('reason');

            layer.tips(reason, this, {
                tips: [1, '#FF5722']
            });
        })
        $('body').on('click','.offline',function(){
             var id = $(this).data('id');

             app.layerConfirm('确定非要下线么，下线后可以修改活动信息，但需要再次审核！',function(){
                 load = app.showLoading();
                 app.get('/auth/activity/offline/'+id).then(d=>{
                     app.layerMessageS(d.message);
                     page.getList();
                 },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)});
             })
        })

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
