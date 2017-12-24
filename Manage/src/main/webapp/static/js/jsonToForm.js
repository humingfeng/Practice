/**
 * Created by Xushd on 2017/11/17.
 */
layui.define(["jquery"], function (exports) {
    var jQuery = layui.jquery;
    (function ($) {
        $.fn.extend({
            initForm: function (options,isDebug) {
                var defaults = {
                    jsonVlaue: options,
                    isDebug: isDebug || false
                }
                var setting = defaults;
                var form = this, jsonValue = setting.jsonVlaue;
                if ($.type(setting.jsonVlaue) === 'string') {
                    jsonValue = $.parseJSON(jsonValue);
                }
                if (!$.isEmptyObject(jsonValue)) {
                    var debugInfo = '';
                    $.each(jsonValue, function (key, value) {
                        if (setting.isDebug) {
                            debugInfo += "name:" + key + "; value:" + value + " || ";
                        }
                        var formField = form.find("[name='" + key + "']");
                        if ($.type(formField[0]) === "undefined") {
                            if (setting.isDebug) console.error("can not find name:[" + key + "] in form !");
                        } else {
                            var fieldTagName = formField[0].tagName.toLowerCase();
                            if(fieldTagName == 'input'){
                                if(formField.attr('type') == "radio"){
                                    $("input:radio[name='"+key+"'][value='"+value+"']").attr("checked","checked");
                                }else{
                                    formField.val(value);
                                }
                            } else if (fieldTagName == "select"){
                                formField.val(value);
                            } else if (fieldTagName == "textarea"){
                                formField.val(value);
                            } else {
                                formField.val(value);
                            }
                        }
                    })
                    if(setting.isDebug)console.log(debugInfo);
                }

                return form;
            }

        });

    })(jQuery);
    exports("jsonToForm", null);
});