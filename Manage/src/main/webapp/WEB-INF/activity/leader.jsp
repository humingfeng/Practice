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
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动负责人管理</blockquote>
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
                <th>身份</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <script id="tpl" type="text/html">
                {{#  layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.userName }}</td>
                    <td>
                        {{# if (item.main==1) { }}
                        <span class="layui-badge">主负责人</span>
                        {{# } else { }}
                        <span class="layui-badge layui-bg-blue">副负责人</span>
                        {{# } }}
                    </td>
                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" data-type="del" data-index="{{index}}">
                                <i class="iconfont icon-shanchu"></i>
                                删除
                            </a>
                        </div>
                        {{# if (!item.main) { }}
                        <a class="layui-btn layui-btn-xs" data-type="update"  data-index="{{index}}">
                            设为主负责人
                        </a>
                        {{# } else { }}
                        <a class="layui-btn layui-btn-xs" data-type="update"  data-index="{{index}}">
                            设为副负责人
                        </a>
                        {{# } }}
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
            app.get('/auth/activity/leader/list/${activityId}').then(d=>{
                data = d.data;
                tpl.render(data,function(html){
                    $("#tbody").html(html);
                });
            },e=>{}).finally(_=>{app.closeLoading(load);})
        }

        initList();

        $(document).ready(function () {

            console.log(document.body.scrollHeight);
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
                app.post("/auth/activity/leader/add/${activityId}",{userIds:userId}).then(d=>{
                    initList();
                },e=>{
                    app.layerMessageE(e);
                }).finally(_=>{app.closeLoading(load)})

            }else{
                layer.tips('请选择人员', '.layui-unselect', {
                    tips: [1, '#FF5722']
                });
            }

        });

        $('body').on("click", ".layui-btn-xs", function (e) {
            var index = $(this).data("index"),type = $(this).data("type");

            var item = data[index];

            if(type=="del"){
                app.layerDel('确定移除 '+item.userName +'?',_=>{
                    load = app.showLoading();
                    app.post('/auth/activity/leader/del/'+item.id).then(d=>{
                        app.layerMessageS(d.message);
                        initList()
                    },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)});
                })
            }else{
                if(item.main){
                    app.layerDel('确定将 '+item.userName +' 设置为副负责人?',_=>{
                        load = app.showLoading();
                        app.post('/auth/activity/leader/main/'+item.id+'/${activityId}/0').then(d=>{
                            app.layerMessageS(d.message);
                            initList()
                        },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)});
                    })
                }else{
                    app.layerDel('确定将 '+item.userName +' 设置为主负责人?',_=>{
                        load = app.showLoading();
                        app.post('//auth/activityleader/main/'+item.id+'/${activityId}/1').then(d=>{
                            app.layerMessageS(d.message);
                            initList()
                         },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)});
                    })
                }
            }
            layui.stope(e);//阻止冒泡事件
        });
    });
</script>
</body>
</html>
