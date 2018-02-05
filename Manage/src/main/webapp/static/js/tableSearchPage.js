/**
 * List table page search
 * Created by Xushd on 2018/1/20.
 */

layui.define(['app','form','table','laypage'], exports => {
    var $ = layui.$,
        form = layui.form,
        app=layui.app,
        table = layui.table,
        laypage = layui.laypage,load;

    var Page = function(){
        this.pageConfig = {
            isPage:1,//是否开启分页
            pageSize: 10,//分页大小
            pageIndex: 1,//分页开启页数
            url: ''//数据接口
        };
        this.tableConfig = {};
        this.searchParam = {};
    }

    Page.prototype.set = function(option){
        this.pageConfig = option.pageConfig;
        this.tableConfig = option.tableConfig;
        this.searchParam.pageIndex = this.pageConfig.pageIndex;
        this.searchParam.pageSize = this.pageConfig.pageSize;
        this.searchParam.searchFileds = {};
        return this;
    }

    /**
     * Page render
     */
    Page.prototype.render = function(){
        this.initSearch();
        this.initData();
        this.initListenEvent();
    }
    /**
     * Page init data
     */
    Page.prototype.initData = function(){
        var _this = this;
        if(_this.pageConfig.url){
            load = app.showLoading();
            app.post(_this.pageConfig.url,_this.searchParam).then(d=>{
                _this.renderTable(d.data);
            },e=>{app.layerMessageE(e)}).finally(_d=>{app.closeLoading(load)});
        }else{
            console.error('url is undefined');
        }
    }
    /**
     * tbale render
     * @param data
     */
    Page.prototype.renderTable = function(data){
        var _this = this;
        table.render({
            elem: _this.tableConfig.elem,
            cols: _this.tableConfig.cols,
            data:data.list,
            cellMinWidth: 80,
            done: function(res, curr, count){
                if(_this.pageConfig.isPage){
                    $('.layui-table-box').append($('<div class="layui-table-page" id="page"></div>'));
                    laypage.render({
                        elem: 'page',
                        count: data.total,
                        layout: ['count', 'prev', 'page', 'next'],
                        prev: '<i class="layui-icon"></i>',
                        next: '<i class="layui-icon"></i>',
                        curr:data.pageNum,
                        jump: function(obj_page, first) {
                            _this.searchParam.pageIndex = obj_page.curr;
                            if(!first) {
                                _this.initData();
                            }
                        }
                    });
                }
            }
        });
    }
    /**
     * From search init
     */
    Page.prototype.initSearch = function(){
        var _this = this;
        form.on("submit(search)", function (formdata) {
            _this.searchParam.pageIndex = 1;
            _this.searchParam.pageSize = _this.pageConfig.pageSize;
            if(_this.isFormNull(formdata.field)){
                _this.searchParam.searchFileds={};
                _this.initData();
            }else{
                _this.searchParam.searchFileds = formdata.field;
                _this.initData();

            }
        });
    }
    /**
     * Is empty search form
     * @param field
     * @returns {number}
     */
    Page.prototype.isFormNull = function(field){
        var r = 1;
        $.each(field,function (key, value) {
            if(value){r =0 ;return false;}
        });
        return r;
    }
    Page.prototype.initListenEvent = function(){
        var _this = this;
        var operate = {
            delete:function(){
                var url = $(this).data('url'),
                    name = $(this).data('name')||'该条信息',
                    text = '确定要删除 '+ name + '?';
                if(url){
                    app.layerConfirm(text,function(){
                        load = app.showLoading();
                        app.get(url).then(d=>{
                            app.layerMessageS(d.message);
                            _this.refresh();
                        },e=>{app.layerMessageE(e)}).finally(_=>{app.closeLoading(load)});
                    })
                }else{
                    app.layerMessageE("没有设定链接");
                }
            }
            ,confrim:function(){
                var url = $(this).data('url'),
                    name = $(this).data('name'),
                    text = '确定要 ' + name + '？';
                if (url) {
                    app.layerConfirm(text,function(){
                        load = app.showLoading();
                        app.get(url).then(d=>{
                            app.layerMessageS(d.message);
                            _this.refresh();
                        },e=>{
                            app.layerMessageE(e);
                        }).finally(()=>{app.closeLoading(load)})
                    })
                }
                else {
                    app.layerMessageE("没有设定链接");
                }
            }
            ,handle:function(){
                var url = $(this).data('url'),
                    name = $(this).data('name');
                if (url) {
                    app.openWindow(name, url);
                }
                else {
                    app.layerMessageE("没有设定跳转地址")
                }
            }
            ,status:function(){
                var _this = $(this),
                    url = _this.data('url'),
                    id = _this.data('id'),
                    status = _this.data('status')?0:1;
                if (url) {
                    load = app.showLoading();
                    app.post(url,{id:id,status:status}).then(d=>{
                        if(status){
                            _this.addClass('layui-form-onswitch');
                        }else{
                            _this.removeClass('layui-form-onswitch');
                        }
                        _this.data('status',status);
                    },e=>{app.layerMessageE(e)}).finally(()=>{app.closeLoading(load)});
                }
                else {
                    app.layerMessageE("没有设定跳转地址")
                }
            }

        }
        $('body').on("click", ".do-action-page", function (e) {
            var type = $(this).data('type');
            operate[type] ? operate[type].call(this) : '';
            layui.stope(e);//阻止冒泡事件
        });


    }
    Page.prototype.refresh = function(){

        this.searchParam.pageIndex = this.pageConfig.pageIndex;
        this.searchParam.pageSize = this.pageConfig.pageSize;
        this.searchParam.searchFileds = {};
        this.initData();

    }

    var obj = new Page();

    exports("tableSearchPage", function (option) {
        return obj.set(option);
    });
});