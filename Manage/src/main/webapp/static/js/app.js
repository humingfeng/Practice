/**
 * Created by Xushd on 2017/2/7 0007.
 */
layui.define(['layer', 'sha256', 'cookie'], function (e) {
    var $ = layui.jquery, sha = layui.sha256,
        layer = parent.layer || layui.layer;
    var app = {}, auction = {};


    var _ajax = $.ajax;
    // 重写ajax方法，先推断登录在运行success函数
    $.ajax = function (opt) {
        var _success = opt && opt.success || function (a, b) {
            };
        var _opt = $.extend(opt, {
            success: function (data, textStatus) {
                //如果返回的不是json对象 跳转到登录
                if (data.code == 403) {
                    if(parent.parent){
                        parent.parent.layer.alert('登录超时，请重新登录！', {icon: 4, offset: 't', closeBtn: 0}, function (index) {
                            layer.close(index);
                                parent.parent.window.location.href = "/login";
                        });
                    }else{
                        layer.alert('登录超时，请重新登录！', {icon: 4, offset: 't', closeBtn: 0}, function (index) {
                            layer.close(index);
                            parent.window.location.href = "/login";
                        });
                    }
                    return;
                } else if(data.code == 401){
                    layer.msg('您没有该功能权限，Sorry！', {icon:7});
                    return;
                }
                _success(data, textStatus);
            }
        });
        _ajax(_opt);
    };

    Promise.prototype.finally = function (callback) {
        var Promise = this.constructor;
        return this.then(
            function (value) {
                Promise.resolve(callback()).then(
                    function () {
                        return value;
                    }
                );
            },
            function (reason) {
                Promise.resolve(callback()).then(
                    function () {
                        throw reason;
                    }
                );
            }
        );
    }


    /**
     * 绑定页面do-action事件
     */
    $('body').on("click", ".do-action", function (e) {
        var type = $(this).data('type');
        auction[type] ? auction[type].call(this) : '';
        layui.stope(e);//阻止冒泡事件
    });

    /**
     * 页面新增修改打开窗口
     */
    auction.handle = function () {
        var url = $(this).data('url'), name = $(this).data('name');
        if (url) {
            app.openWindow(name, url);
        }
        else {
            app.layerMessageE("没有设定跳转地址")
        }
    }


    /**
     * 弹层返回列表
     */
    auction.backToList = function () {

        app.back();

    }

    /**
     * HTTP 请求
     * @param url
     * @param method
     * @param param
     * @returns {Promise}
     * @constructor
     */
    var HTTP = function (url, method, param) {
        if (!arguments[2]) param = {};
        var token = app.getCookieToken() || "";
        return new Promise((resolve, reject) => {
            $.ajax({
                type: method,  //提交方式
                url: url + "?" + Math.random(),//路径
                data: param,//数据，这里使用的是Json格式进行传输
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("manage-token", token);
                },
                success: function (result) {//返回数据根据结果进行相应的处理
                    //成功
                    if (result.code == 200) {
                        resolve(result);
                    } else {
                        reject(result.message);
                    }
                },
                error(xhr, status, error){
                    reject(error);
                }
            });
        })
    }
    /**
     * POST
     * @param url
     * @param param
     * @returns {Promise}
     */
    app.post = function (url, param) {

        return HTTP(url, 'post', param);
    }
    /**
     *
     * @param url
     * @returns {Promise}
     */
    app.get = function (url) {
        return HTTP(url, 'get');
    }

    /*提示*/
    app.showLoading = function (text) {
        return layer.msg(text || "Loading...", {icon: 16, shade: 0.01, time: 0});
    };
    app.closeLoading = function (index) {
        layer.close(index);
    };
    app.closeAll = function () {
        layui.layer.closeAll();
    };
    app.back = function () {
        parent.layui.layer.closeAll();
        parent.window.location.reload();
    };
    app.layerAlertS = function (text) {
        layer.alert(text, {
            title: "提示",
            icon: 1,
            time: 5000,
            resize: false,
            zIndex: layer.zIndex,
            anim: Math.ceil(Math.random() * 6)
        });
    };
    app.layerAlertE = function (text) {
        layer.alert(text, {
            title: "错误",
            icon: 2,
            time: 5000,
            resize: false,
            zIndex: layer.zIndex,
            anim: Math.ceil(Math.random() * 6)
        });
    };
    app.layerMessage = function (text) {
        layer.msg(text, {time: 5000, resize: false, zIndex: layer.zIndex});
    };
    app.layerMessageE = function (text) {
        layer.msg(text, {icon: 5, anim: 6});
    };
    app.layerMessageS = function (text) {
        layer.msg(text, {icon: 6, anim: 6});
    };
    app.route = function (url) {
        window.location.href = url;
    };
    app.time = function (c, t) {
        setTimeout(c, t || 1000);
    };

    app.openWindow = function (title, url) {
        var index = layui.layer.open({
            title: title,
            type: 2, area: ['400px', '500px'],anim:2,
            content: url,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
    };


    /*confirm*/
    /**
     * 删除询问
     * @param t{TEXT}
     * @param u{URL}
     * @param c{CALLBACK}
     */
    app.layerDel = function (t, c) {
        layer.confirm(t, {
            title: "询问",
            resize: false,
            btn: ['确定删除', '容我想想'],
            btnAlign: 'c',
            icon: 3
        }, function () {
            c();
        }, function () {
            app.layerMessage("好吧！");
        });
    };
    /**
     * confirm
     * @param t{TEXT}
     * @param b{BTN[]}
     * @param c{CALLBACK}
     */
    app.layerConfirm = function (t, c1) {

        layer.confirm(t, {
            title: "询问",
            resize: false,
            btn: ['确定', '取消'],
            icon: 3
        }, function () {
            c1();
        }, function () {

        });
    };

    /**
     * confirm
     * @param t{TEXT}
     * @param b{BTN[]}
     * @param c{CALLBACK}
     */
    app.layerSuccessConfirm = function (t, c1 ,c2) {

        layer.confirm(t, {
            title: "成功",
            resize: false,
            btn: ['继续添加', '返回列表'],
            icon: 6
        }, function () {
            c1();
        }, function () {
            c2();
        });
    };

    //sha256加密
    app.sha = function (p) {
        return sha.sha256_digest(p);
    };
    app.throwError = function (t) {
        console.log(t);
    };
    app.getCookieToken = function () {
        return $.cookie("manage_token");
    }
    app.delCookieToken = function () {
        var date = new Date();
        date.setTime(date.getTime() + -1);//30min
        $.cookie("manage_token", "", {expires: date, path: '/'});
    }
    app.setCookieToken = function (value) {
        var date = new Date();
        date.setTime(date.getTime() + 60 * 60 * 1000);//30min
        $.cookie("manage_token", value, {expires: date, path: '/'});
    }
    e('app', app);
});
