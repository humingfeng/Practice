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
<%@include file="../head.jsp"%>
<style>
    .layui-form-pane .layui-form-label{
        width: 129px;
    }
    .layui-form-pane .layui-input-block {
        margin-left: 129px;
    }
    .layui-layer-shade {
         top: 90px!important;
    }

</style>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        活动签到设置
        <div class="layui-form layui-inline" style="margin-left: 20px;">
            <input type="checkbox" title="开启" <c:if test="${status!=2}">checked</c:if>  lay-filter="close"/>
        </div>
    </blockquote>

    <div class="layui-form layui-form-pane" id="form">
        <input type="hidden" id="activityId" value="${activityId}">
        <input type="hidden" name="id" value="${sign.id}" id="id">

        <div class="layui-row layui-col-space5">

            <div class="layui-col-md6 layui-col-sm6">
                <div class="layui-form-item " pane>
                    <label class="layui-form-label " >签到</label>
                    <div class="layui-input-block ">
                        <input type="checkbox" name="signIn" title="开启" <c:if test="${sign.signIn==1}">checked </c:if> lay-filter="close2">
                    </div>
                </div>
                <div <c:if test="${sign.signIn==0}">style="display: none" </c:if> id="signIn">
                    <div class="layui-form-item layui-form-text">
                        <div class="layui-inline">
                        <label class="layui-form-label " style="text-align: center" >签到二维码</label>
                        <div class="layui-input-block" >
                            <img class="layui-upload-img"
                                 <c:if test="${sign.signInErcode==null||sign.signInErcode==''}">src="/static/img/noimg.svg"</c:if>
                                 <c:if test="${sign.signInErcode!=null && sign.signInErcode!=''}">src="${sign.signInErcode}"</c:if>
                                 width="130" height="130" style="border:1px solid #e6e6e6">
                        </div>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn" data-type="signIn" data-url="/auth/activity/sign/ercode_in/${activityId}/${sign.id}">生成</a>
                            <a class="layui-btn layui-btn-warm" href="/download/ercode/${activityId}/1" id="signInD"
                               <c:if test="${sign.signInErcode==null || sign.signInErcode==''}">style="display:none"</c:if> >下载</a>

                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6 layui-col-sm6">
                <div class="layui-form-item " pane>
                    <label class="layui-form-label " >签退</label>
                    <div class="layui-input-block ">
                        <input type="checkbox" name="signOut" title="开启" <c:if test="${sign.signOut==1}">checked </c:if> lay-filter="close2">
                    </div>
                </div>
                <div <c:if test="${sign.signOut==0}">style="display: none" </c:if> id="signOut">
                    <div class="layui-form-item layui-form-text">
                        <div class="layui-inline">
                            <label class="layui-form-label " style="text-align: center">签退二维码</label>
                            <div class="layui-input-block" >
                                <img class="layui-upload-img"
                                     <c:if test="${sign.signOutErcode==null||sign.signOutErcode==''}">src="/static/img/noimg.svg"</c:if>
                                     <c:if test="${sign.signOutErcode!=null && sign.signOutErcode!=''}">src="${sign.signOutErcode}"</c:if>
                                     width="130" height="130" style="border:1px solid #e6e6e6">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn" data-type="signOut" data-url="/auth/activity/sign/ercode_out/${activityId}/${sign.id}/" >生成</a>
                            <a class="layui-btn layui-btn-warm" href="/download/ercode/${activityId}/2" id="signOutD"
                            <c:if test="${sign.signOutErcode==null || sign.signOutErcode==''}">style="display:none"</c:if> >下载</a>

                        </div>
                    </div>
                    <div class="layui-form-item ">
                        <label class="layui-form-label " >签退时间间隔</label>
                        <div class="layui-input-block ">
                            <input type="text" name="sign_out_time" value="${sign.signOutTime}" class="layui-input" maxlength="3" id="diff" placeholder="请输入签退时间间隔，单位分">
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="layui-layer-shade" style="<c:if test="${status!=0}"> display: none;</c:if> z-index: 19891014; background-color: rgb(0, 0, 0); opacity: 0.3;"></div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">

    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm'],function() {
        var $ = layui.$, form = layui.form, app = layui.app;

        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);

        });

        var id = $("#id").val(),load;

        form.on("checkbox(close2)",function(data){

            var status = 0,name = data.elem.name,param={id:id};

            if(data.elem.checked){
                status = 1;
            }
            param[name] = status;

            load = app.showLoading();
            app.post("/auth/activity/sign/update",param).then(d=>{
                if(status){
                    $("#"+name).show();
                }else{
                    $("#"+name).hide();
                }
            },e=>{app.layerMessageE(e);}).finally(_=>{app.closeLoading(load)});

        })

        $('.layui-btn').click(function(){

            var type = $(this).data("type"),url = $(this).data('url');



            if(type=='signOut'){

                var reg = /^[1-9]+\d*$/;

                var diff = $("#diff").val().trim();
                if(!reg.test(diff)){

                    layer.tips('请输入正确的时间间隔，单位分', '#diff', {
                        tips: [1, '#FF5722']
                    });
                    return;
                }
                url = url + diff;
            }else if(type=='signIn'){

            }else{
                return;
            }

            load = app.showLoading();

            app.get(url).then(d=>{

                $("#"+type+" .layui-upload-img").attr("src",d.data);
                $("#"+type+"D").show();


            },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)})
        })

        form.on('checkbox(close)', function(data){
            var status = 2;
            if(data.elem.checked){
                status =1;
            }
            app.get('/auth/activity/sign/status/${activityId}/'+status).then(d=>{
                if(status==2){
                    $(".layui-layer-shade").show();
                }
                if(status==1){
                    $(".layui-layer-shade").hide();
                }
            },e=>{})

        })

    });


</script>
</body>
</html>
