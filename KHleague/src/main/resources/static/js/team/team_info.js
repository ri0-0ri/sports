// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal() {
    $('.modal').css('display', 'block');
}

// team_info js
// 호버 시 팀 목록, 포지션 목록 보여줌
$(document).ready(function () {
    $('.team_txt').mouseover(function () {
        $('.team_box').addClass('show');
    });

    $('.team_box').mouseover(function () {
        $(this).addClass('show');
    });

    $('.team_txt, .team_box').mouseout(function () {
        $('.team_box').removeClass('show');
    });

    $('.position_txt').mouseover(function () {
        $('.position_box').addClass('show');
    });

    $('.position_box').mouseover(function () {
        $(this).addClass('show');
    });

    $('.position_txt, .position_box').mouseout(function () {
        $('.position_box').removeClass('show');
    });
});