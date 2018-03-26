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
<style>
    .layui-form-pane .layui-form-label {

        padding: 8px 10px;
    }
</style>
<body class="childrenBody tips_verify">

<fieldset class="layui-elem-field" style="width: 1020px;">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        <h3>活动基本信息</h3>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id" >
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动类型</label>
                        <div class="layui-input-block">
                            <select name="typeId" id="typeId" lay-filter="typeId" lay-verify="empty" lay-verType="tips" placeholder="请选择活动类型">
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
                            <select name="classifyId" id="classifyId" lay-filter="classifyId" lay-verify="empty" lay-verType="tips" placeholder="请选择活动分类" >

                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">活动主题</label>
                        <div class="layui-input-block">
                            <select name="themeId" id="themeId" lay-verify="empty" lay-verType="tips" placeholder="请选择活动主题">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-right: 0px;">
                    <label class="layui-form-label">活动名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" maxlength="20"  lay-verify="empty" lay-verType="tips" placeholder="请输入活动名称" style="width: 884px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" >
                        <label class="layui-form-label">活动基地</label>
                        <div class="layui-input-inline"  style="width: 548px">
                            <select name="baseId" id="baseId" lay-verify="empty" lay-verType="tips" placeholder="请选择活动基地">
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
                <div class="layui-form-item" >
                    <label class="layui-form-label">集体活动</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="collective" value="1" title="是" >
                        <input type="radio" name="collective" value="2" title="否" checked="" >
                    </div>
                    <div class="layui-form-mid layui-word-aux">集体活动，报名信息可以后台添加</div>
                </div>
                <div class="layui-form-item" >
                    <label class="layui-form-label">自助</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="self" value="1" title="开启" lay-filter="self">
                        <input type="radio" name="self" value="2" title="关闭" checked="" lay-filter="self">
                    </div>
                    <div class="layui-form-mid layui-word-aux">开启自助活动，可以设置自动开始，但不可用签到</div>
                </div>
                <div class="layui-form-item" id="autoBeginDiv" style="display: none">
                    <label class="layui-form-label">自动开始</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="immediately" value="1" title="开启" lay-filter="auto">
                        <input type="radio" name="immediately" value="2" title="关闭" checked="" lay-filter="auto">
                    </div>
                    <div class="layui-form-mid layui-word-aux">开启自动后，报名成功后，活动自动开始</div>
                </div>
                <div class="layui-form-item" id="signInRunDiv" style="display: none">
                    <label class="layui-form-label">开始后报名</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="signInRun" value="1" title="开启" lay-filter="auto">
                        <input type="radio" name="signInRun" value="2" title="关闭" checked="" lay-filter="auto">
                    </div>
                    <div class="layui-form-mid layui-word-aux">活动进行中可以报名</div>
                </div>
                <div class="layui-form-item" id="signDiv">
                    <label class="layui-form-label">签到</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sign" value="1" title="开启">
                        <input type="radio" name="sign" value="2" title="关闭" checked="">
                    </div>
                    <div class="layui-form-mid layui-word-aux">开启签到，可使用二维码扫描签到签退</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动日期</label>
                        <div class="layui-input-block" style="width: 212px">
                            <input type="text" name="timeStr" id="timeStr" class="layui-input" lay-verify="required" placeholder="请选择活动时间段">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-right: 2px;">
                        <label class="layui-form-label">活动时间</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="validTime" id="validTime" class="layui-input" lay-verify="required" placeholder="请选择活动时间">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-right: 0px;">
                        <label class="layui-form-label">任务截止时间</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="taskCloseTimeStr" id="taskClose" class="layui-input" lay-verify="required" placeholder="请选择活动时间">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" id="numDiv">
                    <div class="layui-inline" style="margin-right: 2px;">
                        <label class="layui-form-label">最多人数</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="number" id="number" class="layui-input" lay-verify="empty|number" lay-verType="tips" maxlength="5" placeholder="请输入活动人数">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">最少人数</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="minNum" id="minNum" class="layui-input" lay-verify="empty|number"  lay-verType="tips" maxlength="5" placeholder="请输入活动人数">
                        </div>
                    </div>
                    <div class="layui-inline" style="height: 43px;">
                        <input type="checkbox" id="nonumbers" lay-skin="primary" lay-filter="nonumbers" title="不限人数">
                    </div>
                </div>
                <div class="layui-form-item" id="enrollDiv">
                    <div class="layui-inline">
                        <label class="layui-form-label">报名截止方式</label>
                        <div class="layui-input-block" style="width: 212px">
                            <select name="closeType" id="closeType" lay-filter="closeType" lay-verify="empty" lay-verType="tips" placeholder="请选择报名截止方式">
                                <option value="">请选择报名截止方式</option>
                                <option value="1">指定截止时间</option>
                                <option value="2">人数满自动截止</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-right: 2px;">
                        <label class="layui-form-label">报名截止时间</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="closeTimeStr" id="closeTime" class="layui-input" lay-verify="empty" lay-verType="tips" placeholder="请输入报名截止时间">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">任务至少完成</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="minTaskNum" class="layui-input" lay-verify="empty|number"  lay-verType="tips" maxlength="5" placeholder="请输入活动费用">
                        </div>
                        <div class="layui-form-mid layui-word-aux">任务最少完成个数，注意活动的任务要大于等于这个数</div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动费用</label>
                        <div class="layui-input-inline" style="width: 212px">
                            <input type="text" name="price" id="money" class="layui-input" lay-verify="empty|number"  lay-verType="tips" maxlength="5" placeholder="请输入活动费用">
                        </div>
                    </div>
                    <div class="layui-inline" style="height: 43px;">
                        <input type="checkbox" id="free" lay-filter="free" lay-skin="primary" title="免费">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text" id="money_desc_item">
                    <label class="layui-form-label">费用说明</label>
                    <div class="layui-input-block">
                        <textarea placeholder="费用说明" class="layui-textarea" id="money_desc" maxlength="500" name="moneyDesc" lay-verify="empty" lay-verType="tips"></textarea>
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

        //绑定日期控件
        laydate.render({elem: '#closeTime',min: 0,type: 'datetime',btns: ['clear', 'confirm']});

        laydate.render({elem: '#taskClose',min: 0,type: 'datetime',btns: ['clear', 'confirm']});

        laydate.render({elem: '#timeStr',range: true,min: 0});

        laydate.render({elem: '#validTime',type: 'time',range: true});

        //自定义验证
        form.verify({
            empty: function(value, item) {
                if(!value){
                    return $(item).attr('placeholder');
                }
            }
        });


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
                                $("#number,#minNum").attr("disabled","disabled").removeAttr("lay-verify").val("");
                                $("#closeType option").each(function(){
                                    if($(this).val()==2){
                                        $(this).attr("disabled","disabled");
                                        return false;
                                    }
                                })
                                $("#nonumbers").attr("checked","checked");

                            }
                            if(Number(data.self)==1){
                                $("#signDiv").hide();
                                $("#autoBeginDiv,#signInRunDiv").show();
                            }

                            if(!data.money){
                                $("#money").attr("disabled","disabled").removeAttr("lay-verify").val("");
                                $("#free").attr("checked","checked");
                                $("#money_desc_item").hide();

                                $("#money_desc").removeAttr("lay-verify");
                            }else{
                                $("#money").val(data.price)
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
            if(data.value){
                load = app.showLoading();
                app.get('/auth/activity/classify/usable/'+data.value).then(d=>{
                    $("#classifyId").empty();
                    $("#classifyId").append("<option value=''>请选择活动分类</option>");
                    layui.each(d.data,function(index,item){
                        $("#classifyId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                    })
                    form.render('select');
                },e=>{}).finally(_=>{app.closeLoading(load)});
            }
        });

        //监听活动分类
        form.on('select(classifyId)', function(data){
            if(data.value){
                load = app.showLoading();
                app.get('/auth/activity/theme/usable/'+data.value).then(d=>{
                    $("#themeId").empty();
                    $("#themeId").append("<option value=''>请选择活动主题</option>");
                    layui.each(d.data,function(index,item){
                        $("#themeId").append("<option value='"+item.id+"' >"+item.value+"</option>");
                    })
                    form.render('select');
                },e=>{}).finally(_=>{app.closeLoading(load)});
            }
        });
        //监听无基地
        form.on('checkbox(nobases)', function(data){
            if(data.elem.checked){
                $("#baseId").attr("disabled","disabled");
                $("#baseId").val(0);
                $("#baseId").removeAttr("lay-verify");
            }else{
                $("#baseId").removeAttr("disabled");
                $("#baseId").val('');
                $("#baseId").attr('lay-verify','empty');
            }
            form.render('select');
        });
        //监听活动是否自助
        form.on('radio(self)',function(data){
            if(data.value==2){
                $("#signDiv").show();
                $("#autoBeginDiv,#signInRunDiv").hide();
            }else{
                $("#signDiv").hide();
                $("#autoBeginDiv,#signInRunDiv").show();
            }
        })

        //监听不限制人数
        form.on('checkbox(nonumbers)', function(data){
            if(data.elem.checked){
                $("#number,#minNum").attr("disabled","disabled").removeAttr("lay-verify");
                $("#closeType option").each(function(){
                    if($(this).val()==2){
                        $(this).attr("disabled","disabled");
                        return false;
                    }
                })
            }else{
                $("#number,#minNum").removeAttr("disabled").attr("lay-verify","empty|number");
                $("#closeType option").removeAttr("disabled");
            }
            $("#number,#minNum,#closeType").val('');
            form.render('select');
        });
        //监听是否免费
        form.on('checkbox(free)', function(data){
            if(data.elem.checked){
                $("#money").attr("disabled","disabled").removeAttr("lay-verify");

                $("#money_desc_item").hide();

                $("#money_desc").removeAttr("lay-verify");
            }else{
                $("#money").removeAttr("disabled").attr("lay-verify","empty|number");

                $("#money_desc_item").show();

                $("#money_desc").attr("lay-verify","required");
            }
            $("#money").val('');
        });
        form.on('select(closeType)',function(data){
            if(data.value==2){
                $("#closeTime").val('').attr("disabled","disabled").removeAttr("lay-verify");
            }else{
                $("#closeTime").val('').removeAttr("disabled").attr("lay-verify","empty");
            }
        })

        form.on("submit(submit)",data=>{
            if(!data.field.number)data.field.number='0';
            if(!data.field.minNum)data.field.minNum='0';
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
