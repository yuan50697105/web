$(window).load(function () {
    var box = $('.banner');
    var oPic = $('.banner ul li');
    var oTab = $('.banner ol li');
    var num = 0;
    var timer = setInterval(turn, 5000);

    function fnTab() {
        oPic.fadeOut(1500);
        oPic.eq(num).fadeIn(1500);
        oTab.removeAttr('class');
        oTab.eq(num).attr('class', 'on');
    };
    fnTab();

    function turn() {
        num++;
        if (num == oPic.length) {
            num = 0;
        }
        ;
        fnTab();
    };
    oTab.mouseover(function () {
        if (!$(this).hasClass('on')) {
            oTab.removeAttr('class');
            $(this).attr('class', 'on');
            var i = $(this).index();
            oPic.fadeOut();
            oPic.eq(i).fadeIn();
            num = i;
        }
        ;
    });
    box.mouseover(function () {
        clearInterval(timer);
    })
    box.mouseout(function () {
        timer = setInterval(turn, 5000);
    })
});