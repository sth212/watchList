/* Write here your custom javascript codes */

$(window).on('resize', function () {
    Footer();
});

function Footer() {

    var height = $(window).height();

    var div = $(".content").height();

    if (height < 600) {

        $('.footer-v1').removeClass('footer-bottom');

    }
    else {
        if (div > height - 100) {
            $('.footer-v1').removeClass('footer-bottom');
        }
        else {
            $('.footer-v1').addClass('footer-bottom');
        }

    }
}

$(function () {
    $('.dropdown').hover(function () {
            $(this).addClass('open');
        },
        function () {
            $(this).removeClass('open');
        });
});


