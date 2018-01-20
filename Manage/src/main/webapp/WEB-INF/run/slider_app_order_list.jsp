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
    .layui-unselect span{
        max-width: 100px;
    }
</style>
<body class="childrenBody">

<div class="layui-row layui-col-space5 layui-col-md-offset3" id="content">
    <script id="tpl" type="text/html">
        {{#  layui.each(d, function(index, item){ }}
        <div class="layui-col-sm3 layui-col-md3 layui-col-lg3">
            <img src="{{item.imgCover}}" alt="" width="100%">
            <div class="layui-unselect layui-form-checkbox mt5" data-index="{{index}}">
                <span>{{item.name}}</span>
                <i class="layui-icon"></i>
            </div>
        </div>
        {{# }) }}
    </script>
</div>
<div class="layui-row mt5" style="text-align: right">
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal ok">确定</a>
    </div>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','laytpl'],function(){
        var $ = layui.$,laytpl = layui.laytpl,app = layui.app;

        var tpl = laytpl($("#tpl").html());

        var load = app.showLoading();

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        var data = [];

        app.get('/auth/run/slider/app/usable/list/${id}').then(d=>{

            data = d.data;

            tpl.render(d.data, function(html){
                $("#content").html(html);
            });

        },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)});


        $('body').on("click", ".layui-form-checkbox", function (e) {

            if($(this).hasClass('layui-form-checked')){
                $(this).removeClass('layui-form-checked');
            }else{
                $(this).addClass('layui-form-checked');
            }

            layui.stope(e);//阻止冒泡事件
        });

        $('.ok').click(function(){

            var selectArry = [];

            $('.layui-form-checked').each(function(j,item){

                var index = $(item).data('index');
                var obj = data[index];

                selectArry.push({id:obj.id,name:obj.name,img:obj.imgCover,url:obj.url,type:obj.type});

            })

            if(selectArry.length){

                parent.selectData(selectArry);

                parent.layer.close(index);

            }else{
                app.layerMessageE('未选择 任何东西');
            }



        })
    })
</script>
</body>
</html>
