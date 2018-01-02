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
<div class="layui-container" style="margin-left: 0px;">
    <input type="hidden" id="index" value="${index}">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <c:forEach items="${tabList}" var="item" varStatus="status">
                <li
                        <c:if test="${status.index == index}">class="layui-this"</c:if>
                >${item.key}</li>
            </c:forEach>
        </ul>
        <div class="layui-tab-content clildFrame">
            <c:forEach items="${tabList}" var="item">
                <div class="layui-tab-item" data-url="${item.value}" style="height: 100%"></div>
            </c:forEach>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    var $;
    layui.config({base:"/static/js/"}).use(['app','element'],function(){
        $ = layui.$,element = layui.element,app = layui.app;

        var index = $("#index").val();

        var curItem = $(".layui-tab-content .layui-tab-item").eq(index);

        var url = $(curItem).data('url');




        var childFrame = "<iframe src='"+url+"' data-id='"+index+"' id='frame_"+index+"'></frame>"

        $(curItem).append($(childFrame)).addClass("layui-show").data("status",1);

        element.on("tab(docDemoTabBrief)",function(data){
            index = data.index;
            $("#index").val(index);

            var curItem = $(".layui-tab-content .layui-tab-item").eq(index);

            if(!$(curItem).data("status")){

                var url = $(curItem).data('url');

                var childFrame = "<iframe src='"+url+"' data-id='"+index+"'></frame>"

                $(curItem).append($(childFrame)).addClass("layui-show").data("status",1);
            }

            if($(curItem).data("height")){
                $(".clildFrame").css("height",$(curItem).data("height"));
            }else{
                $(".clildFrame").css("height",500);
            }
        })

    })

    var resetHeight = function(height){

        var index= $("#index").val();

        $(".layui-tab-content .layui-tab-item").eq(index).data("height",height);

        $(".clildFrame").css("height",height);

    }
</script>
</body>
</html>
