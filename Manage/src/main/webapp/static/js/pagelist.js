/**
 * Created by Xushd on 2017/10/16.
 */
layui.define(['app', 'form', 'laytpl', 'laypage'], exports => {
    var $ = layui.jquery,
        form = layui.form,
        laypage = layui.laypage,
        laytpl = layui.laytpl, app = layui.app,
        Page = function () {
            this.pageConfig = {
                isPage:1,//是否开启分页
                pageSize: 10,//分页大小
                pageIndex: 1,//分页开启页数
                url: undefined//数据接口
            }
        };
    var tpl,pageParam = {pageIndex:1,pageSize:10,searchFileds:{},pageStatus:1},operate={},listData=[];
    var obj = new Page();

    var load;
    $(function(){
        load = app.showLoading('加载中。。。');
    })

    $(document).ready(function(){
        app.closeLoading(load);
    })

    /**
     * 绑定页面do-action事件
     */
    $('body').on("click", ".do-action-page", function (e) {
        var type = $(this).data('type');
        operate[type] ? operate[type].call(this) : '';
        layui.stope(e);//阻止冒泡事件
    });



    /**
     * 删除
     */
    operate.delete = function(){
        var url = $(this).data('url'),name = $(this).data('name')||'该条信息',index= $(this).data('index');
        if (url) {
            app.layerDel('确定删除 '+name +'?',_=>{
                var ls = app.showLoading();
                var item = listData[index];


                app.post(url,{id:item.id,delflag:1}).then(d=>{
                    app.layerMessageS(d.message);
                    listData.splice(index,1);
                    obj.tplrender(listData);
                },e=>{app.layerMessageE(e)}).finally(d=>{app.closeLoading(ls)});
            })
        }
        else {
            app.layerMessageE("没有设定删除链接");
        }
    }

    /**
     * 删除
     */
    operate.deleteUrl = function(){
        var url = $(this).data('url'),name = $(this).data('name')||'该条信息',index= $(this).data('index');
        if (url) {
            app.layerDel('确定删除 '+name +'?',_=>{
                var ls = app.showLoading();
                var item = listData[index];
                app.get(url).then(d=>{
                    app.layerMessageS(d.message);
                    listData.splice(index,1);
                    obj.tplrender(listData);
                },e=>{app.layerMessageE(e)}).finally(d=>{app.closeLoading(ls)});
            })
        }
        else {
            app.layerMessageE("没有设定删除链接");
        }
    }

    operate.clearCache = function(){
        var url = $(this).data('url'),name = $(this).data('name');
        if (url) {
            app.layerConfirm('确定 '+name +'?',_=>{
                app.get(url).then(r=>{

                    app.layerMessageS(r.message);
                },e=>{
                    app.layerMessageE(e);
                })
            })
        }
        else {
            app.layerMessageE("没有设定链接");
        }
    }

    /**
     * 状态修改
     */
    operate.status = function(){
        var url = $(this).data('url'),index= $(this).data('index');
        if (url) {
            app.get(url).then(r=>{
                listData[index].status = listData[index].status?0:1;
                obj.tplrender(listData);
            },e=>{
                app.layerAlertE(e);
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
        var url = $(this).data('url'),index = $(this).data('index');
        if(url){
            var item = listData[index],status = item.status==1?0:1;
            var ls = app.showLoading();
            app.post(url,{id:item.id,status:status}).then(d=>{
                app.layerMessageS(d.message);
                listData[index].status = status;
                obj.tplrender(listData);
            },e=>{app.layerMessageE(e)}).finally(d=>{app.closeLoading(ls)});
        }else{
            app.layerMessageE('url is empty');
        }
    }


    //参数设置
    Page.prototype.set = function(option){
        var _this = this;
        $.extend(true,_this.pageConfig,option);
        pageParam.pageSize = option.pageSize;
        pageParam.pageIndex = option.pageIndex;
        pageParam.pageStatus = option.isPage;
        return _this;
    }

    //获取列表数据
    Page.prototype.getList = function(){
        var url = this.pageConfig.url;
        if(url){
            app.post(url,pageParam).then(d=>{
                if(this.pageConfig.isPage){
                    this.tplrender(d.data.list||d.data.rows);
                    this.pageInit(url,d.data);
                }else{
                    this.tplrender(d.data);
                }

            },e=>{
                app.layerAlertE(e);
            })
        }else{
            throw Error('url undefined');
        }
    }
    
    Page.prototype.pageInit = function (url,pageInfo) {

        var _this = this;
        laypage.render({
            elem: 'page',
            count: pageInfo.total,
            groups: 5, //连续显示分页数
            jump: function(obj_page, first) {
                //得到了当前页，用于向服务端请求对应数据
                pageParam.pageIndex = obj_page.curr;
                if(!first) {
                    app.post(url,pageParam).then(d=>{
                        _this.tplrender(d.data.list);
                    },e=>{app.layerAlertE(e)});
                }
            }
        });
    }
    
    //脚本渲染
    Page.prototype.tplrender=function (data) {
        listData = data;
        tpl.render(data, function(html){
            $("#tbody").html(html);
        });
    }
    //页面渲染
    Page.prototype.render = function(){
        //定义模版
        tpl = laytpl($("#tpl").html());

        this.initSearch();
        this.getList();
    }

    //页面渲染 不获取数据
    Page.prototype.renderNoGetData = function(){
        //定义模版
        tpl = laytpl($("#tpl").html());
        this.initSearch();
    }

    /**
     * 初始化查询字段
     */
    Page.prototype.initSearch = function(){
        var _this = this;
        form.on("submit(search)", function (formdata) {
            //查询字段不为空执行查询
            if(_this.isFormNull(formdata.field)){
                console.warn('field is empty');
                pageParam.searchFileds={};
                _this.getList();
            }else{
                pageParam.searchFileds = formdata.field;
                _this.getList();

            }
        });
    }

    /**
     * 判断查询字段是否全为空
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

    Page.prototype.getData = function(){

        return listData;
    }

    //
    exports("pagelist", function (option) {
        return obj.set(option);
    });
})