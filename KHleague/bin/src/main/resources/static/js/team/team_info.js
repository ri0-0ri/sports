// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal() {
    $('.modal').css('display', 'block');
}

// 호버 시 팀 목록, 포지션 목록 보여줌
$(document).ready(function () {
    $('.team_txt, .team_box').mouseover(function () {
        $('.team_box').addClass('show');
    });

    $('.team_box').mouseleave(function () {
		setTimeout(function() {
        $('.team_box').removeClass('show');
		}, 500); 
    });

    $('.position_txt, .position_box').mouseover(function () {
        $('.position_box').addClass('show');
    });

    $('.position_box').mouseleave(function () {
		setTimeout(function() {
		$('.position_box').removeClass('show');
		}, 500); 
    });
});

//종목 선택따라서 팀 5개 바로가기박스, 구단선택, 포지션 선택 바꿔주기
$(document).ready(function () {
	const sports_btn = $('.sports_btn').find('div');
	const select_team = $('.team_box').find('ul');
	const select_position = $('.position_box').find('ul');
	
	// 자동으로 축구선택
	sports_btn.filter(function() {
	   if($(this).text() === "축구"){
		$(this).addClass('act');
	   }
	});
	$('.team_for_type_soccer').css('display', 'flex');	
	select_team.css('display', 'none');
	select_team.eq(0).css('display','block');
	select_position.css('display', 'none');
	select_position.eq(0).css('display','block');
	
	// 버튼 누르면
	sports_btn.click(function() {
		// 초기화
		$('.team_for_type_soccer, .team_for_type_baseball, .team_for_type_basketball, .team_for_type_volleyball').css('display', 'none');
		sports_btn.removeClass('act');
		select_team.css('display', 'none');
		select_position.css('display', 'none');

		if ($(this).text() === "축구") {
			$('.team_for_type_soccer').css('display', 'flex');
			$(this).addClass('act');
			select_team.eq(0).css('display','block');
			select_position.eq(0).css('display','block');
		}
		else if ($(this).text() === "야구") {
			$('.team_for_type_baseball').css('display', 'flex');
			$(this).addClass('act');
			select_team.eq(1).css('display','block');
			select_position.eq(1).css('display','block');
		}
		else if ($(this).text() === "농구") {
			$('.team_for_type_basketball').css('display', 'flex');
			$(this).addClass('act');
			select_team.eq(2).css('display','block');
			select_position.eq(2).css('display','block');
		}
		else if ($(this).text() === "배구") {
			$('.team_for_type_volleyball').css('display', 'flex');
			$(this).addClass('act');
			select_team.eq(3).css('display','block');
			select_position.eq(3).css('display','block');
		}
	});


});