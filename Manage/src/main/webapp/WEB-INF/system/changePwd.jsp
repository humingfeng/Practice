<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2018/3/13
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/head"></jsp:include>
<body class="childrenBody">

<fieldset class="layui-elem-field"  style="width: 500px">
    <blockquote class="layui-elem-quote layui-quote-nm qute-blue">
        <p> 密码是隐私信息，请勿泄漏</p>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" id="form">

                <div class="layui-form-item">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="password1" class="layui-input" lay-verify="pass"  placeholder="请输入新密码">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码确认</label>
                    <div class="layui-input-block">
                        <input type="password" name="password2" class="layui-input" lay-verify="pass"  placeholder="请再次输入新密码">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="return false" lay-filter="submit">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</fieldset>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/static/js/"}).use(['app','form','jsonToForm'],function(){
        var $ = layui.$,form = layui.form,app = layui.app;
        form.verify({
          pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
        });

        form.on("submit(submit)",data=>{

            app.post('/auth/user/change/pwd',data.field).then(d=>{
                app.layerMessageS(d.message);
                app.time(function(){
                    parent.closeWinCur();
                })
            },e=>{
                app.layerMessageE(e);
            })
            return false;
        })

    })
</script>
</body>
</html>