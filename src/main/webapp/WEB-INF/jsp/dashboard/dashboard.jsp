<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="static/style/dashboard.css" rel="stylesheet">
</head>
<script type="text/javascript" src="static/javascript/jquery.js"></script>
<body>
<div class="container">
    <div class="line-function">
        <div class="function-class">
            <div class="col-function function"  t-url="/wordDasboard" t-index="2" t-name="单词列表">
                <img class="word-icon" src="static/image/1403319.png" />
                <div class="word-text">
                    <span class="function-word">新概念英語</span>
                </div>
            </div>
        </div>

        <div class="function-class">
            <div class="col-function function"  t-url="/memoryRuleDasboard" t-index="3" t-name="记忆列表">
                <img class="word-icon" src="static/image/1859394.png" />
                <div class="word-text">
                    <span class="function-word">每日单词</span>
                </div>
            </div>
        </div>

    </div>

    <div class="information-container">

        <div class="col-information-left"></div>

    </div>

</div>

<script>
    $('.function').on('click', function (obj) {

        var dataUrl = $(this).attr("t-url"),
        dataIndex = $(this).attr("t-index"),
            menuName = $(this).attr("t-name"),
        flag = true;

        $('.J_menuTab',parent.document).each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.J_menuTab',parent.document).removeClass('active');
                    scrollToTab(this);
                    $('.J_mainContent .J_iframe',parent.document).each(function () {
                        if ($(this).data('id') == dataUrl) {
                            // 显示tab对应的内容区
                            $(this).show().siblings('.J_iframe',parent.document).hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });

        if (flag) {

            var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i><!--&nbsp;<i class="fa fa-refresh"></i>--></a>';
            $('.J_menuTab',parent.document).removeClass('active');

            // 添加选项卡对应的iframe

            var str1 = '<iframe class="J_iframe"  name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';

            $('.J_mainContent',parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);


            // 添加选项卡
            $('.J_menuTabs .page-tabs-content',parent.document).append(str);

            scrollToTab($('.J_menuTab.active',parent.document));

        }

        obj.preventDefault();
    });

    function scrollToTab(element){

        //debugger
        var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs",parent.document).children().not(".J_menuTabs",parent.document));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs",parent.document).outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content",parent.document).outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content",parent.document).outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content',parent.document).animate({
            marginLeft: 0 - scrollVal + 'px'
        }, "fast");
    }

    function calSumWidth(elements){
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }

</script>
</body>
</html>
