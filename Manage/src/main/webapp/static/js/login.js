/**
 * Created by Xushd on 2017/10/9.
 */
layui.config({
    base : "/static/js/"
}).use(['form','app'],function(){
    var form = layui.form,app = layui.app;



    //登录按钮事件
    form.on("submit(login)",function(data){
        data.field.pass = app.sha(data.field.pass);
        app.post('/login/check',data.field).then(d=>{
            app.layerMessage('登录成功!');
            app.setCookieToken(d.message);
            app.time(_=>{
                window.location.href = "/";
            })
        },e=>{
            app.layerMessageE(e);
        })
        return false;
    })
})
