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
<%@include file="../head.jsp" %>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">活动任务设置</blockquote>
    <a class="layui-btn layui-btn-normal add"><i class="iconfont icon-add"></i>新增任务</a>

    <div class="layui-row" style="margin-top: 10px;" id="list">
        <script id="tpl" type="text/html">
            <table class="layui-table">
                <thead>
                <colgroup><col width="12%"><col width="45%"> <col width="12%"> <col width="12%"><col ></colgroup>
                <tr><th>任务标题</th><th>任务内容</th><th>任务分值</th><th>题目限制</th><th>操作</th></tr>
                </thead>
                <tbody>
                {{# layui.each(d, function(index, item){ }}
                <tr>
                    <td>{{ item.title }}</td>
                    <td>{{ item.description}}</td>
                    <td align="center">{{ item.score}}</td>
                    <td align="center">{{ item.limitNum}}</td>
                    <td>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs" data-type="edit" data-index="{{ index }}">修改</a>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-warm" data-type="set" data-index="{{ index }}">设置</a>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" data-url="/auth/activity/task/del/{{item.activityId}}/{{item.id}}" data-type="del" data-index="{{index}}" >删除</a>
                        </div>
                    </td>
                </tr>
                </tbody>
                {{# }); }}
            </table>
        </script>
    </div>

    <div class="row" style="overflow: inherit;display: none;" id="add">
        <fieldset class="layui-elem-field" style="margin-top: 1px;">
            <div class="layui-form layui-form-pane">
                <input type="hidden" value="${activityId}" name="activityId" id="activityId">
                <input type="hidden" value="0" name="id" id="id">
                <div class="layui-row" style=>
                    <div class="layui-form-item">
                        <label class="layui-form-label">任务标题</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="title" maxlength="200" placeholder="请输入任务标题"
                                   lay-verify="required">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">任务分值</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="score" maxlength="5" placeholder="请输入任务分值"
                                   lay-verify="required|score">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">最少答题数</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="limitNum" maxlength="2" placeholder="请输入最少答题数"
                                   lay-verify="required|limitum">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">任务描述</label>
                        <div class="layui-input-block">
                            <textarea type="text" class="layui-textarea" name="description" maxlength="500"
                                      placeholder="请输入任务描述" lay-verify="required" style="resize: none"></textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    <button class="layui-btn layui-btn-warm close">关闭</button>
                </div>
            </div>
        </fieldset>
    </div>

</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base: "/static/js/"}).use(['app', 'form', 'laytpl', 'element','jsonToForm'], function () {
        var $ = layui.$, form = layui.form, laytpl = layui.laytpl, app = layui.app, element = layui.element;

        var load, data = [];

        var tpl = laytpl($("#tpl").html());

        var initList = function () {
            load = app.showLoading();
            app.get('/auth/activity/task/list/${activityId}').then(d => {
                data = d.data;
                tpl.render(data, function (html) {
                    $("#list").html(html);
                });
            }, e => {
            }).finally(_ => {
                app.closeLoading(load);
            })
        }

        initList();

        var alerWinIndex = 0;

        $(".add").click(function () {

            var item = {id:0,title:'',description:'',score:'',limitNum:''};

            $("#add").initForm(item);

            alerWinIndex = layer.open({
                title: '任务新增',
                type: 1, offset: 't',
                area: ['600px', '450px'],
                content: $('#add')
            });
        });

        form.verify({
            score: [/^[1-9]+([.]{1}[0-9]){0,1}$|^[0]+([.]{1}[0-9]){0,1}$/, '请输入正整数，或一位小数'],
            limitum: [/^[0-9]\d*$/, '请输入正确的数值']
        });


        $('body').on("click", ".layui-btn-xs", function (e) {

            var index = $(this).data('index'),type = $(this).data('type');

            if(type == 'edit'){
                $("#add").initForm(data[index]);
                alerWinIndex = layer.open({
                    title: '任务修改',
                    type: 1, offset: 't',
                    area: ['600px', '450px'],
                    content: $('#add')
                });
            }else  if(type == 'del'){
                var url = $(this).data('url');

                app.layerConfirm('确定要删除 '+data[index].title +' ?',function(){
                    load = app.showLoading();
                    app.get(url).then(d=>{
                        initList();
                    },e=>{app.layerMessageE(e)}).finally(_d=>{app.closeLoading(load)})
                });
            }else{
                var title = data[index].title+'设置';
                var index = layui.layer.open({
                    title: title,
                    type: 2, area: ['400px', '500px'],anim:2,
                    content: '/auth/activity/task/set/item/'+data[index].activityId+'/'+data[index].id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500)
                    }
                })
                layui.layer.full(index);

            }
            layui.stope(e);//阻止冒泡事件
        });

        form.on("submit", function (data) {
            load = app.showLoading();
            var url = '/auth/activity/task/add';
            if(Number(data.field.id)){
                url = '/auth/activity/task/update';
            }
            app.post(url, data.field).then(d => {
                app.layerMessageS(d.message);

                layer.close(alerWinIndex);
                initList();
            }, e => {
                app.layerMessageE(e);
            }).finally(_ => {
                app.closeLoading(load)
            })

            return false;
        })

        $(".close").click(function () {
            layer.close(alerWinIndex);
        })

        $(document).ready(function () {

            parent.resetHeight(document.body.scrollHeight);

        });
    });
</script>
</body>
</html>
