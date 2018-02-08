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
    .layui-table-page{
        text-align: right;
    }
    .layui-badge{
        margin-top: 5px;
    }
</style>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <h2>报名管理</h2>
</blockquote>


<div class="row" style="overflow: inherit">
    <form class="layui-form layui-form-pane" action="">
        <input type="hidden" name="activityId" value="${id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学校</label>
                <div class="layui-input-inline">
                    <select name="schoolId" id="schoolId" lay-filter="schoolId" lay-search>
                        <option value="">请选择学校</option>
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
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="status">
                        <option value="">请选择状态</option>
                        <option value="8">已确认</option>
                        <option value="9">待确认</option>
                        <option value="4">用户取消</option>
                        <option value="5">支付超时取消</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
            </div>
        </div>
    </form>

</div>
<!--table-->
<div class="layui-row" style="overflow: inherit">
    <table class="layui-hide" id="table"></table>
</div>
<script type="text/html" id="statusTpl">
    {{#  if(d.status === 9){ }}
    <span class="layui-badge layui-bg-orange">待确定</span>
    {{#  } }}
    {{#  if(d.status === 8){ }}
    <span class="layui-badge layui-bg-green">已确认</span>
    {{#  } }}
    {{#  if(d.status === 5){ }}
    <span class="layui-badge layui-bg-danger">订单超时取消</span>
    {{#  } }}
    {{#  if(d.status === 4){ }}
    <span class="layui-badge layui-bg-danger">用户取消</span>
    {{#  } }}
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','table','laypage'],function(){
        var $ = layui.$,form = layui.form,app=layui.app,table = layui.table,laypage = layui.laypage;

        var pageParam = {
            pageIndex:1,
            pageSize:10,
            pageStatus:1,
            searchFileds:{}
        }

        var load;

        app.get("/auth/baseinfo/school/usable/list").then(d=>{
            layui.each(d.data,function(index,item){
                $("#schoolId").append("<option value='"+item.id+"' >"+item.value+"</option>");
            })
            form.render('select');
        });


        var initData = function(){
            load = app.showLoading()
            app.post('/auth/activity/enroll/record/list/${id}',pageParam).then(d=>{
                renderTable(d.data);
            },e=>{app.layerMessageE(e)}).finally(_d=>{app.closeLoading(load)});
        }
        initData();

        var renderTable = function(data){
            table.render({
                elem: '#table',
                cols: [[
                    {field:'id', title: 'ID',width:80},
                    {field:'schoolName', title: '学校'},
                    {field:'periodName', title: '年级'},
                    {field:'className', title: '班级'},
                    {field:'name', title: '名称'},
                    {field:'status', title: '状态',templet: '#statusTpl',width:100},
                    {field:'timeStr', title: '报名时间',width:180}
                ]],
                data:data.list,
                done: function(res, curr, count){

                    $('.layui-table-box').append($('<div class="layui-table-page" id="page"></div>'));

                    laypage.render({
                        elem: 'page',
                        count: data.total,
                        layout: ['count', 'prev', 'page', 'next'],
                        prev: '<i class="layui-icon"></i>',
                        next: '<i class="layui-icon"></i>',
                        curr:data.pageNum,
                        jump: function(obj_page, first) {
                            pageParam.pageIndex = obj_page.curr;
                            if(!first) {
                              initData();
                            }
                        }
                    });
                }
            });
        }

        form.on("submit(search)", function (formdata) {
            if(isFormNull(formdata.field)){
                pageParam.searchFileds={};
                initData();
            }else{
                pageParam.searchFileds = formdata.field;
                initData();

            }
        });

        var isFormNull = function(field){
            var r = 1;
            $.each(field,function (key, value) {
                if(value){r =0 ;return false;}
            });
            return r;
        }


    })
</script>
</body>
</html>
