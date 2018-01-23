<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/1/20
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">
    <div class="layui-row">
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal add1">选择投放广告</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-danger save">保存</a>
        </div>
    </div>
    <div class="item_content">
        <ul id="imgs"></ul>
    </div>
    <div id="template" style="display: none;">
        <li>
            <div class="item">
                <a href="javascript:; " class="delete">x</a>
                <img src="/static/img/noimg.svg" />
                <div class="info"></div>
            </div>
        </li>
    </div>
    <script type="text/javascript" src="/static/layui/layui.js"></script>
    <script type="text/javascript">
        var DATA = [];
        layui.config({base:"/static/js/"}).use(['drag','layer','app'],function(){
            var $ = layui.$,app = layui.app,Drag = layui.drag;


            //开启拖拽
            Drag.start();

            var LIST = [];

            var load = app.showLoading();

            app.get('/auth/run/slider/app/order/list/${type}').then(d=>{
                LIST = d.data;
                createImgElement(LIST);


            },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)});


            $('.add1').click(function(){
                DATA = [];
                var index = layui.layer.open({
                    title: '请选择',
                    type: 2, area: ['400px', '500px'],offset: 't',
                    content: '/auth/jump/run/slider_app_order_list?id=${type}',
                    end:function(){

                        var list = [];
                        $.each(DATA,function(j,i){

                            var mark = 0;

                            $.each(LIST,function(x,y){
                                if(y.id == i.id){
                                    mark = 1;
                                    return;
                                }
                            })

                            if(!mark){
                                list.push(i);
                            }

                        })
                        createImgElement(list);
                    }
                })
                layui.layer.full(index);
            })

            $('.save').click(function(){

                var imgArry = getImgArry();

                if(imgArry.length){

                    load = app.showLoading();

                    app.post('/auth/run/slider/app/order/save/${type}',{list:JSON.stringify(imgArry)}).then(d=>{
                        app.layerMessageS(d.message);
                    },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)});

                }else{
                    app.layerMessageE("无数据");
                }


            })

            //重新创建
            function  rescreateImgElement(item){
                var li = $("#template li:first").clone(true);
                li.find('img').attr('src',item.img);
                li.find(".info").data("id",item.id);
                li.find(".info").data("url",item.url);
                li.find(".info").data("type",item.type);
                li.find(".info").text(item.name);
                li.appendTo(document.getElementById("imgs"));
            }



            //创建图片
            var createImgElement=function(list){
                var imgsSort = getImgArry();
                $(".item_content").html('<ul id="imgs"></ul>');
                $.each(imgsSort,function(i,j) {
                    rescreateImgElement(j);
                })
                $.each(list,function(i,j){
                    rescreateImgElement(j);
                })
                setTimeout(function(){
                    Drag.reclear();
                },100);
            }

            //获取排序后的 图片数组
            var getImgArry = function(){
                var imgArry =[];
                $(".item_content .item").each(function(){
                    var id = $(this).find(".info").data('id');
                    var url = $(this).find(".info").data('url');
                    var name = $(this).find(".info").text();
                    var img = $(this).find("img").attr('src');
                    var type = $(this).find(".info").data('type');
                    var index = $(this).attr('index');
                    imgArry.push({id:id,name:name,img:img,url:url,type:type,index:index});
                })
                return imgArry.sort(function(a,b){return a.index - b.index;})
            }


            //绑定删除
            $('.item .delete').click(function(){

                var imgurl = $(this).next().attr("src");

                var imgsSort = getImgArry();

                $(".item_content").html('<ul id="imgs"></ul>');

                $.each(imgsSort,function(i,j) {
                    if(j.img!=imgurl){
                        rescreateImgElement(j);
                    }
                })

                setTimeout(function(){
                    Drag.reclear();
                },100);
            });

        })

        var selectData = function(data){

            DATA = data;
        }
    </script>
</body>
</html>
