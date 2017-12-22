/**
 * Created by Xushd on 2017/2/7 0007.
 */
layui.define(['layer', 'util', 'sha256', 'cookie','laytpl'], function (e) {
    var $ = layui.jquery, util = layui.util, sha = layui.sha256,
        layer = parent.layer || layui.layer,laytpl= layui.laytpl;
    var app = {};


    var _ajax = $.ajax;
    // 重写ajax方法，先推断登录在运行success函数
    $.ajax = function (opt) {
        var _success = opt && opt.success || function (a, b) {
            };
        var _opt = $.extend(opt, {
            success: function (data, textStatus) {
                //如果返回的不是json对象 跳转到登录
                if (data.status == 403) {
                    window.location.href = "/";
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
     * HTTP 请求
     * @param url
     * @param method
     * @param param
     * @returns {Promise}
     * @constructor
     */
    var HTTP = function (url, method, param) {
        if (!arguments[2]) param = {};
        var token = app.getCookieToken() || "nologin";
        return new Promise((resolve, reject) => {
            $.ajax({
                type: method,  //提交方式
                url: url + "?" + Math.random(),//路径
                data: param,//数据，这里使用的是Json格式进行传输
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("platform-manage-token", token);
                },
                success: function (result) {//返回数据根据结果进行相应的处理
                    //成功
                    if (result.status == 200) {
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
        layer.msg(text || "Loading...", {icon: 16, shade: 0.01, time: 0});
    };
    app.closeLoading = function () {
        layer.closeAll();
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
    app.route = function (url) {
        window.location.href = url;
    };
    app.time = function (c, t) {
        setTimeout(c, t || 1000);
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
    app.layerConfirm = function (t, c) {

        layer.confirm(t, {
            title: "询问",
            resize: false,
            btn: ['确定', '取消'],
            icon: 3
        }, function () {
            c();
        }, function () {
            //app.layerMessage("好吧！");
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
        date.setTime(date.getTime() + 60 * 30 * 1000);//30min
        $.cookie("manage_token", value, {expires: date, path: '/'});
    }
    e('app', app);
});
