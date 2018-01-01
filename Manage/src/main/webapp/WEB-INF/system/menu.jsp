<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/10/10
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../head.jsp"%>
<body class="childrenBody">
<!--title-->
<blockquote class="layui-elem-quote">
    <div class="layui-inline" style="width: 100px;">
        <h2>菜单管理</h2>
    </div>
    <div class="layui-inline">
         <a class="layui-btn layui-btn-normal newsAdd_btn do-action"
           data-type="handle" data-url="/auth/jump/system/menu_handle"
           data-name="菜单新增"><i class="iconfont icon-add"></i>菜单新增</a>
    </div>
</blockquote>

<!--table-->
<div class="layui-form">
    <div class="layui-collapse" id="content">

        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">{{ item.name }}</h2>
                    <div class="layui-colla-content layui-show">
                        <table class="layui-table" >
                            <colgroup>
                                <col width="10%">
                                <col width="15%">
                                <col width="15%">
                                <col width="20%">
                                <col width="10%">
                                <col >
                            </colgroup>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>菜单名称</th>
                                <th>菜单ICON</th>
                                <th>菜单URL</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            <tr>
                                <td>{{ item.id }}</td>
                                <td>{{ item.name }}</td>
                                <td style="text-align: center"><span class="iconfont {{ item.icon }}"></span></td>
                                <td>{{ item.url }}</td>
                                <td style="text-align: center">{{# if (item.status) { }}
                                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                                       data-url="/auth/system/menu/update" data-id="{{item.id}}" data-status="{{item.status}}" data-parent="0" ></a>
                                    {{# } else { }}
                                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                                       data-url="/auth/system/menu/update" data-id="{{item.id}}" data-status="{{item.status}}" data-parent="0"></a>
                                    {{# } }}
                                </td>
                                <td>
                                    <div class="layui-inline">
                                        <a class="layui-btn layui-btn-xs do-action"
                                           data-type="handle" data-url="/auth/jump/system/menu_handle?id={{item.id}}" data-name="编辑">
                                            <i class="iconfont icon-bianji"></i>
                                            编辑
                                        </a>
                                    </div>
                                    <div class="layui-inline">
                                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="delete"
                                           data-url="/auth/system/menu/update" data-name="{{item.name}}" data-id="{{item.id}}" data-parent="0">
                                            <i class="iconfont icon-shanchu"></i>
                                            删除
                                        </a>
                                    </div>
                                </td>
                            </tr>

                            {{# layui.each(item.children,function(j,child){ }}
                            <tr>
                                <td>{{ child.id }}</td>
                                <td>{{ child.name }}</td>
                                <td style="text-align: center"><span class="iconfont {{ child.icon }}"></span></td>
                                <td>{{ child.url }}</td>
                                <td style="text-align: center">{{# if (child.status) { }}
                                    <a class="iconfont icon-keyong do-action-page" data-type="update"
                                       data-url="/auth/system/menu/update" data-id="{{child.id}}" data-status="{{child.status}}" data-parent="{{child.parentId}}" ></a>
                                    {{# } else { }}
                                    <a class="iconfont icon-bukeyong do-action-page" data-type="update"
                                       data-url="/auth/system/menu/update" data-id="{{child.id}}" data-status="{{child.status}}" data-parent="{{child.parentId}}"></a>
                                    {{# } }}
                                </td>
                                <td>
                                    <div class="layui-inline">
                                        <a class="layui-btn layui-btn-xs do-action"
                                           data-type="handle" data-url="/auth/jump/system/menu_handle?id={{child.id}}" data-name="编辑">
                                            <i class="iconfont icon-bianji"></i>
                                            编辑
                                        </a>
                                    </div>
                                    <div class="layui-inline">
                                        <a class="layui-btn layui-btn-xs layui-btn-danger do-action-page" data-type="delete"
                                           data-url="/auth/system/menu/delete" data-name="{{child.name}}" data-id="{{child.id}}" data-parent="{{child.parentId}}">
                                            <i class="iconfont icon-shanchu"></i>
                                            删除
                                        </a>
                                    </div>
                                </td>
                            </tr>
                            {{# }); }}
                            </tbody>
                        </table>
                    </div>
                </div>
            {{# }); }}
        </script>
    </div>
</div>
<!--分页-->
<div id="page" style="text-align: right"></div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','element','laytpl'],function(){
        var $ = layui.$,
            element = layui.element,
            app = layui.app,
            laytpl = layui.laytpl,
            tpl = laytpl($("#tpl").html()),
            operate={};

        /**
         * 绑定页面do-action事件
         */
        $('body').on("click", ".do-action-page", function (e) {
            var type = $(this).data('type');
            operate[type] ? operate[type].call(this) : '';
            layui.stope(e);//阻止冒泡事件
        });

        operate.initData = function(){
            var load = app.showLoading();
            app.get('/auth/system/menu/list').then(d=>{
                tpl.render(d.data, function(html){
                    $("#content").html(html);
                    element.render('collapse');
                });
            },e=>{
                app.layerMessageE(e);
            }).finally(_=>{
                app.closeLoading(load);
            });
        };

        /**
         * 删除
         */
        operate.delete = function(){
            var url = $(this).data('url'),name = $(this).data('name')||'该条信息',
                id= $(this).data('id'),
                parent = $(this).data('parent');
            if (url) {
                var text = '确定删除 '+name +'?';
                if(parent==0)text+="，系统会自动删除旗下的子菜单";
                app.layerDel(text,_=>{
                    var load = app.showLoading();
                    app.post(url,{id:id,delflag:1,parentId:parent}).then(d=>{
                        app.layerMessageS(d.message);
                        app.time(function(){
                            operate.initData();
                        })
                    },e=>{app.layerMessageE(e)}).finally(d=>{});
                })
            }
            else {
                app.layerMessageE("没有设定删除链接");
            }
        }
        /**
         * 更新
         * @author Xushd
         */
        operate.update = function(){
            var url = $(this).data('url'),
                id = $(this).data('id'),
                status = $(this).data('status'),
                parent = $(this).data('parent');
            if(url){
                var load = app.showLoading();
                app.post(url,{id:id,status:status?0:1,parentId:parent}).then(d=>{
                    app.layerMessageS(d.message);
                    app.time(function(){
                        operate.initData();
                    })
                },e=>{app.layerMessageE(e)}).finally(d=>{});
            }else{
                app.layerMessageE('url is empty');
            }
        }

        operate.initData();

    })
</script>
</body>
</html>
