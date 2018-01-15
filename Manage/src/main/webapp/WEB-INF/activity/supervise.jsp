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
    .layui-layer-shade {
        top: 90px!important;
    }
</style>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动监督人设置
    <div class="layui-form layui-inline" style="margin-left: 20px;">
        <input type="checkbox" title="开启" <c:if test="${status!=0}">checked</c:if>  lay-filter="close"/>
    </div>
    </blockquote>

    <div class="row" style="overflow: inherit">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">人员选择</label>
                    <div class="layui-input-inline">
                        <select name="user" id="user"  >
                            <option value=''>请选择</option>
                            <c:forEach items="${userList}" var="item">
                                <option value="${item.id}">${item.nickName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-normal newsAdd_btn"><i class="iconfont icon-add"></i>添加</a>
                </div>
            </div>
        </form>
    </div>
    <input type="hidden" value="${activityId}" id="activityId">
    <div class="layui-row">
        <table class="layui-table" >
            <thead>
            <tr>
                <th>人员名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.userName }}</td>
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

    <div class="layui-layer-shade" style="<c:if test="${status!=0}"> display: none;</c:if> z-index: 19891014; background-color: rgb(0, 0, 0); opacity: 0.3;"></div>

</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function() {
        var $ = layui.$, form = layui.form,  laytpl = layui.laytpl,app = layui.app;


        var load,data=[];

        var tpl = laytpl($("#tpl").html());

        var initList = function(){
            load = app.showLoading();
            app.get('/auth/activity/supervise/list/${activityId}').then(d=>{
                data = d.data;
                tpl.render(data,function(html){
                    $("#tbody").html(html);
                });
            },e=>{}).finally(_=>{app.closeLoading(load);})
        }

        initList();


        form.on('checkbox(close)', function(data){

            var status = 0;
            if(data.elem.checked){
                status =3;
            }
            app.get('/auth/activity/supervise/status/${activityId}/'+status).then(d=>{
                if(status==0){
                    $(".layui-layer-shade").show();
                }
                if(status==3){
                    $(".layui-layer-shade").hide();
                }
            },e=>{})

        })




        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);

        });


        $(".newsAdd_btn").click(function(){

            var userId = $("#user").val();
            if(userId){
                var verify = 0;
                $.each(data,function(index,item){
                    if(userId == item.userId){
                        verify = 1;
                        return false;
                    }
                })
                if(verify){
                    app.layerMessageE("该人员已存在");
                    return ;
                }
                load = app.showLoading();
                app.post("/auth/activity/supervise/add/${activityId}",{userId:userId}).then(d=>{
                    initList();
                },e=>{
                    app.layerMessageE(e);
                }).finally(_=>{app.closeLoading(load)})

            }else{
                layer.tips('请选择人员', '.layui-form-select', {
                    tips: [1, '#FF5722']
                });
            }

        });

        $('body').on("click", ".layui-btn-xs", function (e) {
            var index = $(this).data("index"),type = $(this).data("type");

            var item = data[index];


            app.layerDel('确定移除 '+item.userName +'?',_=>{
                load = app.showLoading();
                app.post('/auth/activity/supervise/del/'+item.id).then(d=>{
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
