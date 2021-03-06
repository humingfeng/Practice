<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/9/28
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/head"></jsp:include>
<style>
    .layui-body{overflow:hidden; border-top:5px solid #1AA094;border-left:2px solid #1AA094;}
    .layui-tab-content{ height:100%; padding:0; }
    .layui-tab-item{ position: absolute; top: 41px; bottom:0; left: 0; right: 0; padding: 0; margin: 0; -webkit-overflow-scrolling:touch; overflow:auto;}
    .layui-tab-title .layui-this{ background-color:#1AA094; color:#fff; }
    .layui-tab-title .layui-this:after{ border:none; }
    .layui-tab-title li cite{ font-style: normal; padding-left:5px; }
</style>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main">
            <a href="#" class="logo">Practise CMS</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:;" class="iconfont hideMenu icon-menu1"></a>
            <!-- 搜索 -->
            <%--<div class="layui-form component">--%>
                <%--<select name="modules" lay-verify="required" lay-search="">--%>
                    <%--<option value="">直接选择或搜索选择</option>--%>
                <%--</select>--%>
                <%--<i class="layui-icon">&#xe615;</i>--%>
            <%--</div>--%>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item showNotice" id="showNotice" pc>
                    <a href="javascript:;"><i class="iconfont icon-gonggao"></i><cite>系统公告</cite></a>
                </li>
                <li class="layui-nav-item" mobile>
                    <a href="javascript:;" class="mobileAddTab" data-url="/auth/user/change/pwd"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>设置</cite></a>
                </li>
                <li class="layui-nav-item" mobile>
                    <a href="javascript:;" class="signOut"><i class="iconfont icon-exit"></i> 退出</a>
                </li>
                <%--<li class="layui-nav-item lockcms" pc>--%>
                    <%--<a href="javascript:;"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>--%>
                <%--</li>--%>
                <li class="layui-nav-item" pc>
                    <a href="javascript:;">
                        <img src="${headImg}" class="layui-circle" width="35" height="35">
                        <cite>${userName}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-url="/auth/user/change/userInfo">
                                <i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i>
                                <cite>个人资料</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="/auth/user/change/pwd">
                                <i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i>
                                <cite>修改密码</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="signOut">
                                <i class="iconfont icon-exit"></i>
                                <cite>退出</cite>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="navBar layui-side-scroll"></div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="iconfont icon-caozuo"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i class="iconfont icon-refresh"></i> 刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i class="iconfont icon-prohibit"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="/auth/welcome"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p>copyright @2017 Xushd529@github</p>
    </div>
</div>
<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/admin.js"></script>
</body>
</html>
