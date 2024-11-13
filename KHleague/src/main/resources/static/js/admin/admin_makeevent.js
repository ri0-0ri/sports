$(document).ready(function() {
  // .makebtn을 클릭하면 해당하는 .make_wrap을 보이거나 숨기게 하기
  $('.makebtn').on('click', function() {
    var makeWrap = $(this).closest('.no_winner').find('.make_wrap');

    // make_wrap이 보이고 있다면 숨기고, 숨겨져 있다면 보이게 함
    if (makeWrap.css('display') === 'flex') {
      makeWrap.css('display', 'none');
      $(this).text('추첨하기');  // 텍스트를 '추첨하기'로 변경
    } else {
      makeWrap.css('display', 'flex');
      $(this).text('닫기');  // 텍스트를 '닫기'로 변경
    }
  });
});
