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

<fieldset class="layui-elem-field" style="width: 700px;">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动基本信息</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id" >
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动类型</label>
                        <div class="layui-input-block">
                            <select name="typeId" id="typeId" lay-filter="typeId" lay-verify="required">
                                <option value=''>请选择活动类型</option>
                                <c:forEach items="${types}" var="item">
                                    <option value="${item.id}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">活动分类</label>
                        <div class="layui-input-block">
                            <select name="classifyId" id="classifyId" lay-filter="classifyId" lay-verify="required" >

                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动主题</label>
                        <div class="layui-input-block">
                            <select name="themeId" id="themeId" lay-verify="required">

                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">活动名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" class="layui-input" lay-verify="required" maxlength="20" style="width: 212px" placeholder="请输入活动名称">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" >
                        <label class="layui-form-label">活动基地</label>
                        <div class="layui-input-inline"  style="width: 212px">
                            <select name="baseId" id="baseId" lay-verify="required" >
                                <option value=''>请选择活动基地</option>
                                <c:forEach items="${bases}" var="item">
                                    <option value="${item.id}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="height: 43px;">
                        <input type="checkbox" id="nobases" lay-skin="primary" title="无基地" lay-filter="nobases">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">时长</label>
                        <div class="layui-input-block" >
                            <input type="text" name="duration" class="layui-input" lay-verify="required" maxlength="20" style="width: 212px" placeholder="请输入活动时长，单位小时">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <input type="radio" name="durationType" value="2" title="当天">
                        <input type="radio" name="durationType" value="1" title="超过1天" checked="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">活动时间</label>
                    <div class="layui-input-block" style="width: 545px">
                        <input type="text" name="timeStr" id="timeStr" class="layui-input" lay-verify="required" placeholder="请输入活动时间段">
                    </div>
                </div>
                <div class="layui-form-item" >
                    <label class="layui-form-label">自助</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="self" value="1" title="是">
                        <input type="radio" name="self" value="2" title="否" checked="">
                    </div>
                    <div class="layui-form-mid layui-word-aux">自助。。。。。</div>
                </div>
                <div class="layui-form-item" >
                    <label class="layui-form-label">签到</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sign" value="1" title="开启">
                        <input type="radio" name="sign" value="2" title="关闭" checked="">
                    </div>
                    <div class="layui-form-mid layui-word-aux">开启签到，可使用二维码扫描签到签退</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动人数</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="number" id="number" class="layui-input" lay-verify="required|number"  maxlength="5" placeholder="请输入活动人数">
                        </div>
                    </div>
                    <div class="layui-inline" style="height: 43px;">
                        <input type="checkbox" id="nonumbers" lay-skin="primary" lay-filter="nonumbers" title="不限人数">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动费用</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="money" id="money" class="layui-input" lay-verify="required|number"  maxlength="5" placeholder="请输入活动费用">
                        </div>
                    </div>
                    <div class="layui-inline" style="height: 43px;">
                        <input type="checkbox" id="free" lay-filter="free" lay-skin="primary" title="免费">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">截止方式</label>
                        <div class="layui-input-block" style="width: 212px">
                            <select name="closeType" id="closeType" lay-verify="required" lay-filter="closeType">
                                <option value="">请选择报名截止方式</option>
                                <option value="1">指定截止时间</option>
                                <option value="2">人数满自动截止</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">截止时间</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="closeTimeStr" id="closeTime" class="layui-input" lay-verify="required" placeholder="请输入报名截止时间">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','laydate'],function(){
        var $ = layui.$,form = layui.form,app = layui.app, laydate = layui.laydate;

        var load,id = $("#id").val();

        var url = "/auth/activity/manage/base/add";

        laydate.render({elem: '#closeTime',min: 3,btns: ['clear', 'confirm']});

        laydate.render({elem: '#timeStr',type: 'datetime',range: true,min: 3});

        if(id){

            $(document).ready(function(){
                load = app.showLoading();

                url = "/auth/activity/manage/update";

                app.get("/auth/activity/manage/obj/"+id).then(d=>{
                    var data = d.data;
                    app.get('/auth/activity/classify/usable/'+data.typeId).then(d=>{
                        $("#classifyId").empty();
                        $("#classifyId").append("<option value=''>请选择活动分类</option>");
                        layui.each(d.data,function(index,item){
                            $("#classifyId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                        })
                        app.get('/auth/activity/theme/usable/'+data.classifyId).then(d=>{
                            $("#themeId").empty();
                            $("#themeId").append("<option value=''>请选择活动主题</option>");
                            layui.each(d.data,function(index,item){
                                $("#themeId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                            })

                            $("#form").initForm(data);

                            if(!data.baseId){
                                $("#baseId").attr("disabled","disabled");
                                $("#baseId").val(0);
                                $("#nobases").attr("checked","checked");
                            }
                            if(!data.number){
                                $("#number").attr("disabled","disabled").removeAttr("lay-verify").val("");
                                $("#closeType option").each(function(){
                                    if($(this).val()==2){
                                        $(this).attr("disabled","disabled");
                                        return false;
                                    }
                                })
                                $("#nonumbers").attr("checked","checked");

                            }

                            if(!data.money){
                                $("#money").attr("disabled","disabled").removeAttr("lay-verify").val("");
                                $("#free").attr("checked","checked");
                            }
                            if(data.closeType==2){
                                $("#closeTime").val('').attr("disabled","disabled").removeAttr("lay-verify");
                            }

                            form.render();

                        },e=>{}).finally(_=>{app.closeLoading(load)});


                    },e=>{}).finally(_=>{});

                },e=>{})
            })

        }




        //监听活动类型
        form.on('select(typeId)', function(data){
            load = app.showLoading();
            app.get('/auth/activity/classify/usable/'+data.value).then(d=>{
                $("#classifyId").empty();
                $("#classifyId").append("<option value=''>请选择活动分类</option>");
                layui.each(d.data,function(index,item){
                    $("#classifyId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                })
                form.render('select');
            },e=>{}).finally(_=>{app.closeLoading(load)});
        });
        //监听活动分类
        form.on('select(classifyId)', function(data){
            load = app.showLoading();
            app.get('/auth/activity/theme/usable/'+data.value).then(d=>{
                $("#themeId").empty();
                $("#themeId").append("<option value=''>请选择活动主题</option>");
                layui.each(d.data,function(index,item){
                    $("#themeId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                })
                form.render('select');
            },e=>{}).finally(_=>{app.closeLoading(load)});
        });
        //监听无基地
        form.on('checkbox(nobases)', function(data){
            if(data.elem.checked){
                $("#baseId").attr("disabled","disabled");
                $("#baseId").val(0);
            }else{
                $("#baseId").removeAttr("disabled");
                $("#baseId").val('');
            }

            form.render('select');
        });
        //监听不限制人数
        form.on('checkbox(nonumbers)', function(data){
            if(data.elem.checked){
                $("#number").attr("disabled","disabled").removeAttr("lay-verify");
                $("#closeType option").each(function(){
                    if($(this).val()==2){
                        $(this).attr("disabled","disabled");
                        return false;
                    }
                })

            }else{
                $("#number").removeAttr("disabled").attr("lay-verify","required|number");
                $("#closeType option").removeAttr("disabled");
            }
            $("#number").val('');
            $("#closeType").val('');
            form.render('select');
        });
        //监听是否免费
        form.on('checkbox(free)', function(data){
            if(data.elem.checked){
                $("#money").attr("disabled","disabled").removeAttr("lay-verify");
            }else{
                $("#money").removeAttr("disabled").attr("lay-verify","required|number");
            }
            $("#money").val('');
        });
        form.on('select(closeType)',function(data){
            if(data.value==2){
                $("#closeTime").val('').attr("disabled","disabled").removeAttr("lay-verify");
            }else{
                $("#closeTime").val('').removeAttr("disabled").attr("lay-verify","required");
            }
        })

        form.on("submit(submit)",data=>{
            if(!data.field.number)data.field.number='0';
            if(!data.field.money)data.field.money='0';
            if(!data.field.baseId)data.field.baseId='0';
            app.post(url,data.field).then(d=>{
                app.layerMessageS(d.message);
                app.time(function(){
                    app.back();
                })
            },e=>{
                app.layerMessageE(e);
            })
            return false;
        })



    })
</script>
</body>
</html>
