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
<fieldset class="layui-elem-field"  style="width: 710px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">基地新增</blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">
                <input type="hidden" name="id" value="${id}" id="id">
                <div class="layui-form-item">
                    <label class="layui-form-label">省市区</label>
                    <div class="layui-input-inline">
                        <select name="pid" id="proviceId" lay-filter="proviceId" lay-search lay-verify="required">
                            <option value="">请选择省</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="cid" id="cityId" lay-filter="cityId" lay-search lay-verify="required">
                            <option value="">请选择市</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="aid" id="areaId" lay-verify="required">
                            <option value="">请选择县/区</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">基地名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" class="layui-input" lay-verify="required" maxlength="150"  placeholder="请输入基地名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系方式</label>
                    <div class="layui-input-block">
                        <input type="text" name="linkPhone" class="layui-input" lay-verify="required|number" maxlength="20"  placeholder="请输入基地联系方式">
                    </div>
                </div>
             
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">基地地址</label>
                    <div class="layui-input-block" >
                        <textarea  name="address" lay-verify="required" maxlength="200" class="layui-textarea" placeholder="请输入基地地址" style="resize: none;"></textarea>
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">基地图文介绍</label>
                    <div class="layui-input-block">
                        <div id="edit" >

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
<script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','layedit'],function(){
        var $ = layui.$,form = layui.form,app = layui.app,layedit= layui.layedit;

        var load;

        var editor = new window.wangEditor('#edit');
        editor.customConfig.uploadFileName = 'file'
        editor.customConfig.uploadImgServer = '/upload/img/bases';
        editor.customConfig.uploadImgHooks = {
            before: function (xhr, editor, files) {
                load = app.showLoading();
            },
            // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
            // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
            customInsert: function (insertImg, result, editor) {
                if(result.code==200){
                    insertImg(result.data);
                }else{
                    app.layerMessageE(result.message);
                }
                app.closeLoading(load);

            }
        }

        $(document).ready(function () {
            editor.create()
        });

        var id = $("#id").val();

        var url = "/auth/baseinfo/bases/add";

        load = app.showLoading();
        app.get('/auth/baseinfo/province/list').then(d=>{
            layui.each(d.data,function(index,item){
                $("#proviceId").append("<option value='"+item.pid+"' >"+item.name+"</option>");
            })
            form.render('select');
            if(id){
                url = "/auth/baseinfo/bases/update";
                app.get('/auth/baseinfo/bases/'+id).then(d=>{
                    provinceChange(d.data.pid).then(function(){
                        cityChange(d.data.cid).then(function(){
                            $("#form").initForm(d.data);
                            form.render('select');
                            editor.txt.html(d.data.description);
                            app.closeLoading(load);
                        })
                    });
                },e=>{}).finally(_=>{});
            }else{
                app.closeLoading(load);
            }
        },e=>{});

        form.on('select(proviceId)', function(data){
            provinceChange(data.value);

        });

        var provinceChange = function(pid){
            return new Promise((resolve) => {
                app.get('/auth/baseinfo/city/list/'+pid).then(d=>{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>请选择城市/区</option>");
                    layui.each(d.data,function(index,item){
                        $("#cityId").append("<option value='"+item.cid+"' >"+item.name+"</option>");
                    })
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>请选择区/县</option>");
                    form.render('select');
                },e=>{}).finally(_=>{resolve()});
            });
        }

        form.on('select(cityId)', function(data){
            cityChange(data.value);
        });

        var cityChange = function(cid){
            return new Promise((resolve) => {
                app.get('/auth/baseinfo/area/list/'+cid).then(d=>{
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>请选择区/县</option>");
                    if(d.data.length){
                        $("#areaId").removeAttr("disabled");
                        layui.each(d.data,function(index,item){
                            $("#areaId").append("<option value='"+item.aid+"' >"+item.name+"</option>");
                        })
                    }else{
                        $("#areaId").attr("disabled","disabled");
                        $("#areaId").val(0);
                    }

                    form.render('select');
                },e=>{}).finally(_=>{resolve();});
            });

        }

        form.on("submit(submit)",data=>{
            if(!data.field.aid)data.field.areaId=0;
            data.field.description = editor.txt.html();
            app.post(url,data.field).then(d=>{
                if(id){
                    app.layerMessageS(d.message);
                    app.time(function(){
                        app.back();
                    })
                }else{
                    app.layerSuccessConfirm(d.message+'，是否继续添加',function(){
                        app.layerMessage('好的');
                        $("button[type='reset']").click();
                    },function(){
                        app.back();
                    });
                }
            },e=>{
                app.layerMessageE(e);
            })
            return false;
        })

    })
</script>
</body>
</html>
