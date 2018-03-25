/**
 * Created by Xushd on 2017/11/22.
 */
layui.define(["jquery"], function (exports) {
    var jQuery = layui.jquery,$ = layui.$;

    $.fn.countTo = function () {
        var settings = {
            from:            0,
            to:              $(this).data('to')||0,
            speed:           1000,
            refreshInterval: 100,
            decimals:        0
        }
        var loops = Math.ceil(settings.speed / settings.refreshInterval),
            increment = (settings.to - settings.from) / loops;
        var self = this,$self = $(this),loopCount = 0,value = settings.from,data = $self.data('countTo') || {};

        $self.data('countTo', data);
        if (data.interval) {
            clearInterval(data.interval);
        }
        data.interval = setInterval(updateTimer, settings.refreshInterval);

        render(value);

        function updateTimer() {
            value += increment;
            loopCount++;

            render(value);

            if (loopCount >= loops) {
                // remove the interval
                $self.removeData('countTo');
                clearInterval(data.interval);
                value = settings.to;
            }
        }

        function render(value) {
            var formattedValue = formatter(value, settings);
            $self.html(formattedValue);
        }
        function formatter(value, settings) {
            return value.toFixed(settings.decimals);
        }

    };


    exports("dynamic.count", null);
});