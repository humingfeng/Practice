<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/1/7
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">

    <fieldset class="layui-elem-field"  style="width: 760px">
        <div class="layui-form layui-form-pane" id="form">
            <input type="hidden" value="${id}" name="id" id="id">
            <div class="layui-form-item">
                <label class="layui-form-label">题目类型</label>
                <div class="layui-input-block">
                    <select name="typeId" id="typeId" lay-filter="typeId" lay-verify="required" >

                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">题目内容</label>
                <div class="layui-input-block">
                        <textarea type="text" class="layui-textarea" name="question" maxlength="200"
                                  placeholder="请输入题目内容" lay-verify="required" style="resize: none"></textarea>
                </div>
            </div>
            <div class="layui-form-item" pane>
                <label class="layui-form-label">题目方案</label>
                <div class="layui-input-block">
                    <input type="radio" name="classify" value="1" title="选择题">
                    <input type="radio" name="classify" value="2" title="主观题">
                </div>
            </div>
            <div id="content1">

            </div>
            <div id="content2">

            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-warm do-action" data-type="backToList">返回</button>
            </div>
        </div>
    </fieldset>
    <script type="text/javascript" src="/static/layui/layui.js"></script>
    <script id="tpl_option" type="text/html">
        {{# layui.each(d,function(index,item){ }}
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">选项</label>
                <div class="layui-input-inline"style="width: 90px;" >
                    <input type="hidden" name="itemId" value="{{item.id}}">
                    <input type="text" name="optionMark" lay-verify="required" maxlength="20" value="{{item.optionMark}}" class="layui-input" placeholder="选项">
                </div>
                <div class="layui-input-inline" style="width: 400px;">
                    <input type="text" name="text" lay-verify="required" maxlength="100" value="{{item.text}}" class="layui-input" placeholder="选项内容">
                </div>
                <div class="layui-input-inline" style="width: 70px;">
                    <input type="checkbox" name="correct" title="正确" {{# if(item.correct){ }}checked{{# } }}>
                </div>
            </div>
            <div class="layui-inline" style="margin-right: 0px;float: right">
                <button class="layui-btn layui-btn-danger min" style="font-size: 35px;">-</button>
            </div>
        </div>
        {{# }); }}
    </script>
    <script id="tpl_answer" type="text/html">
        <div class="layui-form-item layui-form-text" id="answer">
            <label class="layui-form-label">主观题答案</label>
            <div class="layui-input-block">
                <textarea type="text" class="layui-textarea" name="answerText" maxlength="200"
                          placeholder="请输入主观题答案" lay-verify="required" style="resize: none"></textarea>
            </div>
        </div>
    </script>
    <script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm','laytpl'],function(){
        var $ = layui.$,form = layui.form,app = layui.app,laytpl = layui.laytpl;

        var id = $("#id").val(),
            url = "/auth/activity/question/add",
            load = app.showLoading(),
            tpl_option = laytpl($('#tpl_option').html()),
            tpl_answer = laytpl($('#tpl_answer').html()),
            option = [{id:0,optionMark:'',text:'',correct:0}];

        app.get('/auth/system/dictionary/type/QUESTION_TYPE').then(d=>{
            $("#typeId").empty();
            $("#typeId").append("<option value=''>请选择</option>");
            layui.each(d.data,function(index,item){
                $("#typeId").append("<option value='"+item.id+"' >"+item.name+"</option>");
            })



            if(id){
                url = "/auth/activity/question/update";
                app.get('/auth/activity/question/obj/'+id).then(d=>{

                    $("#content2").empty();
                    $("#content1").empty();

                    if(d.data.classify==2){
                        tpl_answer.render([],function(html){
                            $("#content2").append(html);
                            form.render();
                        })
                    }else{
                        var item = d.data.list;

                        $("#content1").append($('<div class="layui-form-item"><div class="layui-input-inline">' +
                            '<button class="layui-btn layui-btn-warm" id="plus">添加选项</button></div></div>'));
                        tpl_option.render(item, function(html){
                            $("#content1").append(html);
                            form.render();
                        });

                    }

                    $("#form").initForm(d.data);

                    form.render();
                },e=>{}).finally(_=>{app.closeLoading(load);});
            }else{
                form.render('select');
                app.closeLoading(load);
            }
        },e=>{}).finally(_=>{app.closeLoading(load)})

        form.on('radio',function(data){
            var value = data.elem.value;
            $("#content2").empty();
            $("#content1").empty();
            if(value==1){
                $("#content1").append($('<div class="layui-form-item"><div class="layui-input-inline">' +
                    '<button class="layui-btn layui-btn-warm" id="plus">添加选项</button></div></div>'));
                tpl_option.render(option, function(html){
                    $("#content1").append(html);
                    form.render();
                });

            }else{

                tpl_answer.render([],function(html){
                    $("#content2").append(html);
                    form.render();
                })
            }

        })


        $("body").on("click","#plus",function(){

            var item = [{id:0,optionMark:'',text:'',correct:0}];
            tpl_option.render(item, function(html){
                $("#content1").append(html);
                form.render();
            });
            return false;
        })
        $("body").on("click",".min",function(){
            $(this).parent().parent().remove();
            return false;
        });




        form.on("submit(submit)",data=>{
            if(!data.field.classify){
                app.layerMessageE('请选择题目方案');
                return false;
            }

            if(data.field.classify==1){

                var items = [];

                var titleArry = $("input[name='optionMark']");


                if(titleArry.length<=1){
                    app.layerMessageE('请添加选项,最少2个哦！');
                    return false;
                }

                var authorArry = $("input[name='text']");

                var correct = $("input[name='correct']");

                var ids = $("input[name='itemId']");

                var isChoose = false;

                layui.each(titleArry,function(index,item){
                    var obj = {id:ids[index].value,optionMark:item.value,text:authorArry[index].value,correct:correct[index].checked?1:0};
                    if(correct[index].checked){
                        isChoose = true;
                    }
                    items.push(obj);
                })

                if(!isChoose){
                    app.layerMessageE('请设置正确答案');
                    return false;
                }

                data.field.options = JSON.stringify(items);
            }


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
