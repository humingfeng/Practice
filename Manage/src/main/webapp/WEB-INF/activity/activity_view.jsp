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
<%@include file="../head.jsp" %>
<style>
    .title {
        background: #f9f9f9;
    }
</style>
<body class="childrenBody">
<fieldset class="layui-elem-field" style="width: 800px">
    <blockquote class="layui-elem-quote">
        <h2>活动预览</h2>
    </blockquote>
    <div class="layui-row" id="content">
        <script id="tpl" type="text/html">
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">基本信息</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    <td class="title">类型</td>
                    <td>{{d.base.type}}</td>
                    <td class="title">分类</td>
                    <td>{{d.base.classify}}</td>
                    <td class="title">主题</td>
                    <td>{{d.base.theme}}</td>
                </tr>
                <tr>
                    <td class="title">名称</td>
                    <td colspan="5">{{d.base.name}}</td>
                </tr>
                <tr>
                    <td class="title">基地</td>
                    <td colspan="5">
                        {{# if(d.base.baseId){ }}
                        {{# }else{ }}
                        无基地
                        {{# } }}
                    </td>
                </tr>
                <tr>
                    <td class="title">活动时长</td>
                    <td>{{d.base.duration}} 小时</td>
                    <td class="title">时长类型</td>
                    <td colspan="3">
                        {{# if(d.base.durationType==1){ }}
                        当天
                        {{# }else { }}
                        时长大于一天
                        {{# } }}
                        {{d.base.timeStr}}
                    </td>
                </tr>
                <tr>
                    <td class="title">自助</td>
                    <td>
                        {{# if(d.base.self){ }}
                        是
                        {{# }else{ }}
                        否
                        {{# } }}
                    </td>
                    <td class="title">签到</td>
                    <td>
                        {{# if(!d.base.checkSign==2){ }}
                        否
                        {{# }else{ }}
                        是
                        {{# } }}
                    </td>
                    <td class="title">活动人数</td>
                    <td>
                        {{# if(d.base.number){ }}
                        {{d.base.number}}
                        {{# }else{ }}
                        不限
                        {{# } }}
                    </td>
                </tr>
                <tr>

                    <td class="title">费用</td>
                    <td>
                        {{# if(d.base.money){ }}
                        {{d.base.money}}
                        {{# }else{ }}
                        免费
                        {{# } }}
                    </td>
                    <td class="title">截至方式</td>
                    <td>
                        {{# if(d.base.closeType==1){ }}
                        指定时间
                        {{# }else{ }}
                        名额满
                        {{# } }}
                    </td>
                    <td class="title">截至日期</td>
                    <td>{{d.base.closeTimeStr}}</td>
                </tr>
                </tbody>
            </table>

            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">活动介绍</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    <td class="title">活动地区</td>
                    <td colspan="3">{{d.introduce.proName}} {{d.introduce.cityName}} {{d.introduce.areaName}}</td>
                    <td class="title" rowspan="2">活动封面图</td>
                    <td rowspan="2">
                        <img src="{{d.introduce.imgCover}}" alt="">
                    </td>
                </tr>
                <tr>
                    <td class="title">详细地址</td>
                    <td colspan="3">{{d.introduce.address}}</td>
                </tr>
                <tr>
                    <td class="title" colspan="6">活动图文</td>
                </tr>
                <tr>
                    <td align="center" colspan="6">
                        {{ d.introduce.detail }}
                    </td>
                </tr>
                </tbody>
            </table>
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">活动适用年段</th>
                </tr>
                <thead>
                <tbody>
                    <tr>
                        {{# layui.each(d.activityApplyList,function(index,item){ }}
                        <td>{{item.gradeName}}</td>
                        {{# }) }}
                    </tr>
                </tbody>
            </table>
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">活动负责人</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    {{# layui.each(d.leaderList,function(index,item){ }}
                    <td>
                        {{item.userName}}
                        {{# if (item.main==1) { }}
                        <span class="layui-badge">主负责人</span>
                        {{# } else { }}
                        <span class="layui-badge layui-bg-blue">副负责人</span>
                        {{# } }}
                    </td>
                    {{# }) }}
                </tr>
                </tbody>
            </table>

            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">报名信息采集</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    <td>
                        {{# if(d.enroll.phone==1){ }}
                        <span class="layui-badge">电话信息</span>
                        {{# } }}
                        {{# if(d.enroll.parentName==1){ }}
                        <span class="layui-badge">家长姓名</span>
                        {{# } }}
                        {{# if(d.enroll.name==1){ }}
                        <span class="layui-badge">学生姓名</span>
                        {{# } }}
                        {{# if(d.enroll.idNum==1){ }}
                        <span class="layui-badge">身份证</span>
                        {{# } }}
                        {{# if(d.enroll.passport==1){ }}
                        <span class="layui-badge">护照</span>
                        {{# } }}
                        {{# if(d.enroll.weight==1){ }}
                        <span class="layui-badge">体重</span>
                        {{# } }}
                        {{# if(d.enroll.height==1){ }}
                        <span class="layui-badge">身高</span>
                        {{# } }}
                        {{# if(d.enroll.sex==1){ }}
                        <span class="layui-badge">性别</span>
                        {{# } }}
                        {{# if(d.enroll.nation==1){ }}
                        <span class="layui-badge">民族</span>
                        {{# } }}
                        {{# if(d.enroll.birthday==1){ }}
                        <span class="layui-badge">生日</span>
                        {{# } }}
                    </td>
                </tr>
                </tbody>
            </table>
            {{# if(d.base.checkSign){ }}
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">签到信息</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    <td class="title">签到二维码</td>
                    <td>
                        <img src="{{d.sign.signInErcode}}" alt="">
                    </td>
                    <td class="title">签退二维码</td>
                    <td>
                        <img src="{{d.sign.signOutErcode}}" alt="">
                    </td>
                    <td class="title">时间间隔</td>
                    <td >{{d.sign.signOutTime}} 分钟</td>
                </tr>
                </tbody>
            </table>
            {{# } }}
            {{# if(d.base.checkSupervise){ }}
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">监督人员</th>
                </tr>
                <thead>
                <tbody>
                <tr>
                    {{# layui.each(d.superviseList,function(index,item){ }}
                    <td>
                        {{item.userName}}
                    </td>
                    {{# }) }}
                </tr>
                </tbody>
            </table>
            {{# } }}
            <table class="layui-table">
                <thead>
                <tr>
                    <th colspan="6">任务信息</th>
                </tr>
                <thead>
                <thead>
                    <tr><th>任务标题</th><th>任务内容</th><th>任务分值</th><th>题目限制</th></tr>
                <thead>
                <tbody>

                {{# layui.each(d.taskList,function(index,item){ }}
                <tr>
                    <td>{{ item.task.title }}</td>
                    <td>{{ item.task.description}}</td>
                    <td align="center">{{ item.task.score}}</td>
                    <td align="center">{{ item.task.limitNum}}</td>
                </tr>
                <tr>
                     <table class="layui-table">
                         {{# layui.each(item.questionList,function(j,i){ }}
                            <tr>
                                <td >
                                    题目 {{j+1}}
                                </td>
                                 <td colspan="5">
                                     {{i.question}}
                                 </td>
                            </tr>
                         {{# }) }}
                     </table>
                </tr>
                {{# }) }}
                </tbody>
            </table>

        </script>

    </div>
</fieldset>


<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base: "/static/js/"}).use(['app', 'form', 'laytpl'], function () {
        var $ = layui.$, form = layui.form, app = layui.app, laytpl = layui.laytpl;

        var load = app.showLoading();

        var tpl = laytpl($("#tpl").html());

        app.get('/auth/verify/activity/view/${id}').then(d => {
            console.log(d);

            tpl.render(d.data, function (html) {
                $("#content").html(html);
            });
        }, e => {
            app.layerMessageE(e)
        }).finally(_ => {
            app.closeLoading(load)
        })



    })
</script>
</body>
</html>
