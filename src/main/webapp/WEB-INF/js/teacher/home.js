//获取系统时间
let newDate = '';

//值小于10时，在前面补0
function dateFilter(date) {
    if (date < 10) {
        return "0" + date;
    }
    return date;
}

function getLangDate() {
    let dateObj = new Date(); //表示当前系统时间的Date对象
    let year = dateObj.getFullYear(); //当前系统时间的完整年份值
    let month = dateObj.getMonth() + 1; //当前系统时间的月份值
    let date = dateObj.getDate(); //当前系统时间的月份中的日
    let day = dateObj.getDay(); //当前系统时间中的星期值
    let weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    let week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    let hour = dateObj.getHours(); //当前系统时间的小时值
    let minute = dateObj.getMinutes(); //当前系统时间的分钟值
    let second = dateObj.getSeconds(); //当前系统时间的秒钟值
    let timeValue = "" + ((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午"); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year) + "年" + dateFilter(month) + "月" + dateFilter(date) + "日 " + " " + dateFilter(hour) + ":" + dateFilter(minute) + ":" + dateFilter(second);
    document.getElementById("nowTime").innerHTML = timeValue + "好！当前时间为： " + newDate + "　" + week;
    setTimeout("getLangDate()", 1000);
}

layui.define(['element', 'layer'], function (exports) {

    let $ = layui.$, $body = $('body'),
        element = layui.element,
        layer = layui.layer;

    let screen_size = {
        pc: [991, -1],
        pad: [768, 990],
        mobile: [0, 767]
    };

    let getDevice = function () {
        let width = $(window).width();
        for (let i in screen_size) {
            let sizes = screen_size[i],
                min = sizes[0],
                max = sizes[1];
            if (max === -1) max = width;
            if (min <= width && max >= width) {
                return i;
            }
        }
        return null;
    };

    let isDevice = function (label) {
        return getDevice() === label;
    };

    let isMobile = function () {
        return isDevice('mobile');
    };

    let Tab = function (el) {
        this.el = el;
        this.urls = [];
    };

    Tab.prototype.title = function (title, url) {
        if (title.search("首页") !== -1) {
            title = "首页";
        }
        let refresh = document.createElement("i");
        refresh.setAttribute("class", "layui-icon layui-icon-refresh");
        refresh.setAttribute("style", "margin-left:10px;font-size:14px;");
        refresh.setAttribute("onmousedown", "if(event.button == 0){this.classList.remove('layui-icon-refresh'); this.classList.add('layui-icon-refresh-3');}");
        refresh.setAttribute("onmouseup", "if(event.button == 0){this.classList.remove('layui-icon-refresh-3');" +
            "this.classList.add('layui-icon-refresh');" +
            "document.querySelector(\"iframe[data-id=\'" + this.urls.length + "\']\").src='" + url + "';}");
        refresh.setAttribute("onmouseleave", "this.classList.remove('layui-icon-refresh-3');this.classList.add('layui-icon-refresh');");
        return title + refresh.outerHTML;
    };

    Tab.prototype.content = function (src) {
        let iframe = document.createElement("iframe");
        iframe.setAttribute("frameborder", "0");
        iframe.setAttribute("src", src);
        iframe.setAttribute("data-id", this.urls.length);
        return iframe.outerHTML;
    };

    Tab.prototype.is = function (url) {
        return (this.urls.indexOf(url) !== -1)
    };

    Tab.prototype.add = function (title, url) {
        if (this.is(url)) return false;
        this.urls.push(url);
        element.tabAdd(this.el, {
            title: this.title(title, url)
            , content: this.content(url)
            , id: url
        });
        this.change(url);
    };

    Tab.prototype.change = function (url) {
        element.tabChange(this.el, url);
    };

    Tab.prototype.delete = function (url) {
        element.tabDelete(this.el, url);
    };

    Tab.prototype.onChange = function (callback) {
        element.on('tab(' + this.el + ')', callback);
    };

    Tab.prototype.onDelete = function (callback) {
        let self = this;
        element.on('tabDelete(' + this.el + ')', function (data) {
            let i = data.index;
            self.urls.splice(i, 1);
            callback && callback(data);
        });
    };

    let Home = function () {

        let tabs = new Tab('tabs'), navItems = [];

        $('#Nav a').on('click', function (event) {
            event.preventDefault();
            let $this = $(this), url = $this.attr('href'),
                title = $.trim($this.text());
            if (url && url !== 'javascript:' && url !== "javascript:;") {
                if (tabs.is(url)) {
                    tabs.change(url);
                } else {
                    navItems.push($this);
                    tabs.add(title, url);
                }
            }
            $this.closest('li.layui-nav-item')
                .addClass('layui-nav-itemed')
                .siblings()
                .removeClass('layui-nav-itemed');
        });

        // 默认触发第一个子菜单的点击事件
        $('#Nav li.layui-nav-item:eq(0) > a:eq(0)').trigger('click');

        tabs.onChange(function (data) {
            let i = data.index, $this = navItems[i];
            if ($this && typeof $this === 'object') {
                $('#Nav dd').removeClass('layui-this');
                $this.parent('dd').addClass('layui-this');
                $this.closest('li.layui-nav-item')
                    .addClass('layui-nav-itemed')
                    .siblings()
                    .removeClass('layui-nav-itemed');
                $('#Nav li.layui-nav-item:eq(0)').removeClass("layui-this");
            }
        });

        $('.layui-nav-item').on('click', function () {
            $(this).siblings('li').attr('class', 'layui-nav-item');
        });
        tabs.onDelete(function (data) {
            let i = data.index;
            navItems.splice(i, 1);
        });
        this.slideSideBar();
    };

    Home.prototype.slideSideBar = function () {
        let $slideSidebar = $('.slide-sidebar'),
            $pageContainer = $('.layui-body'),
            $mobileMask = $('.mobile-mask');

        let isFold = false;
        $slideSidebar.on('click', function (e) {
            e.preventDefault();
            let $this = $(this), $icon = $this.find('i'),
                $admin = $body.find('.layui-layout-admin');
            let toggleClass = isMobile() ? 'fold-side-bar-xs' : 'fold-side-bar';
            if ($icon.hasClass('ai-menufold')) {
                $icon.removeClass('ai-menufold').addClass('ai-menuunfold');
                $admin.addClass(toggleClass);
                isFold = true;
                if (isMobile()) $mobileMask.show();
            } else {
                $icon.removeClass('ai-menuunfold').addClass('ai-menufold');
                $admin.removeClass(toggleClass);
                isFold = false;
                if (isMobile()) $mobileMask.hide();
            }
        });

        let tipIndex;
        // 菜单收起后的模块信息小提示
        $('#Nav li > a').hover(function () {
            let $this = $(this);
            if (isFold) {
                tipIndex = layer.tips($this.find('em').text(), $this);
            }
        }, function () {
            if (isFold && tipIndex) {
                layer.close(tipIndex);
                tipIndex = null
            }
        });

        if (isMobile()) {
            $mobileMask.on('click', function () {
                $slideSidebar.trigger('click');
            });
        }
    };
    exports('home', new Home());
});


layui.use(['jquery', 'element', 'layer', 'upload'], function () {
    let $ = layui.jquery,
        layer = layui.layer;

    layer.ready(function () {
        let tcEmail = $(".showUserInfo").data("tcemail");
        if (!tcEmail) {
            layer.confirm('您的账号尚未绑定邮箱，建议前往绑定！否则无法找回密码！是否前往绑定？',
                function (index) {
                    // 点击确定
                    $("#change_info").click();
                    layer.close(index);
                });
        }
        getLangDate();
    });

    $(".showUserInfo").on("click",function () {
        $("#change_info").click();
    });

    $(".showChangePassword").on("click",function () {
        $("#change_pwd").click();
    });

    $(".logout").on("click", function () {
        $.post("./logout",
            {
                tcNum: $(this).data("tcnum")
            }, function (callback) {
            if(callback.code === 0){
                layer.alert(callback.msg, function () {
                    window.location.href = "../logintPage";
                });
            }else{
                layer.alert(callback.msg);
            }
        });
    });
});