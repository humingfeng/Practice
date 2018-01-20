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
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        活动适用学段设置
    </blockquote>
    <div class="row" style="overflow: inherit">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">适用年级</label>
                    <div class="layui-input-inline">
                        <select name="grade" id="grade"  >
                            <option value=''>请选择</option>
                            <c:forEach items="${periods}" var="item">
                                <option value="${item.id}">${item.name}</option>
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
                <th>适用年级</th>
                <th>学段</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.grade }}</td>
                    <td>{{ item.period }}</td>
                    <td>
                        <div class="layui-inline">
                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-index="{{index}}">
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
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','laytpl'],function() {
        var $ = layui.$, form = layui.form,  laytpl = layui.laytpl,app = layui.app;

        var load,data=[];


        var tpl = laytpl($("#tpl").html());



        var initList = function(){
            load = app.showLoading();
            app.get('/auth/activity/apply/list/${activityId}').then(d=>{
                data = d.data;
                tpl.render(data,function(html){
                    $("#tbody").html(html);
                });
            },e=>{}).finally(_=>{app.closeLoading(load);})
        }

        initList();

        $(document).ready(function () {
            parent.resetHeight(document.body.scrollHeight);
        });

        $(".newsAdd_btn").click(function(){

            var gradeId = $("#grade").val();
            if(gradeId){
                var verify = 0;
                $.each(data,function(index,item){
                    if(gradeId == item.gradeId){
                        verify = 1;
                        return false;
                    }
                })
                if(verify){
                    app.layerMessageE("该年级已存在");
                    return ;
                }
                load = app.showLoading();
                app.post("/auth/activity/apply/add/${activityId}",{gradeIds:gradeId}).then(d=>{
                    initList();
                },e=>{
                    app.layerMessageE(e);
                }).finally(_=>{app.closeLoading(load)})

            }else{
                layer.tips('请选择年级', '.layui-unselect', {
                    tips: [1, '#FF5722']
                });
            }

        });

        $('body').on("click", ".layui-btn-xs", function (e) {
            var index = $(this).data("index");

            var item = data[index];

            app.layerDel('确定移除 '+item.grade +'?',_=>{
                load = app.showLoading();
                app.post('/auth/activity/apply/del/'+item.id).then(d=>{
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
