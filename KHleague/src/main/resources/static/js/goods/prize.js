// 모달 닫기
function close_modal() {
	$('.modal').css('display', 'none');
}

// goods js
// 팀 엠블럼 슬라이더
$(document).ready(function() {
	$('.teams').slick({
		arrows: false, // 버튼을 비활성화
		centerMode: true,
		infinite: true,
		centerPadding: '60px',
		slidesToShow: 3,
		responsive: [
			{
				breakpoint: 768,
				settings: {
					arrows: false,
					centerMode: true,
					centerPadding: '40px',
					slidesToShow: 3
				}
			},
			{
				breakpoint: 480,
				settings: {
					arrows: false,
					centerMode: true,
					centerPadding: '40px',
					slidesToShow: 1
				}
			}
		]
	});

	// 좌측 및 우측 버튼 클릭 이벤트
	$('.left_btn p').click(function() {
		$('.teams').slick('slickPrev');
	});

	$('.right_btn p').click(function() {
		$('.teams').slick('slickNext');
	});
});

// 폼 제출
function submitForm(actionUrl, event) {
	const userid = $('input[name="userid"]').val();
	console.log(userid);
	if (!userid) {
		alert("로그인 후 이용해주세요!");
		return;
	}

	event.preventDefault(); // 기본 제출 방지

	// 클릭된 버튼의 부모 폼 찾기
	const form = $(event.target).closest('form');
	const formData = form.serialize();
	console.log(formData);
	const goodsnum = form.find('input[name=goodsnum]').val();
	console.log(goodsnum);
	const size = form.find('input[name="size"], select[name="size"]').val();
	console.log("Selected size: " + size);
	const eventnum = form.find('input[name="eventnum"]').val();
	console.log(eventnum);

	$.ajax({
	   type: 'GET',
	   url: actionUrl,
	   data: formData,
	   success: function(response) {
	      if (actionUrl === '/payment/event_delivery') {
	         // 쿼리스트링 만들고 넘겨주기
	         // 가져가는것 userid, goodsnum, size, quantity > formData
	         // formData 쿼리스트링으로 바꿔주기
	         window.location.href = '/payment/event_delivery?' + formData;
	      }
	      close_modal();
	   },
	   error: function(xhr, status, error) {
	      console.error("Submission failed:", xhr.responseText);
	   }
	});
}

/* 당첨 - 적립금 수령 */
$(function() {
	$('.get_reward').click(function() {
		const reward = $(this).closest('.iteminfo').find('.reward').text().trim();
		const userid = $('.loginuser').text().trim();
		const eventnum = $(this).closest('.iteminfo').find('.eventnum').text().trim();
		console.log(userid + reward + eventnum);

		$.ajax({
			type: 'POST',
			url: '/goods/getReward',
			data: {
				userid: userid,
				userReward: reward,
				eventnum: eventnum
			},
			success: function(response) {
				alert(response);
				location.reload();
			},
			error: function(xhr, status, error) {
				console.error("Submission failed:", xhr.responseText);
			}
		})
	})

})

/* 당첨 - 굿즈 수령 */
$(function() {
	$('.select_goods').click(function() {
		const goodsnum = $(this).closest('.iteminfo').find('.itemnum').text().trim();
		const eventnum = $(this).closest('.iteminfo').find('.eventnum').text().trim();
	
		//모달열기
		const thismodal = $(`.modal#${goodsnum}`);
		thismodal.css('display', 'block');
		thismodal.find('input[name="eventnum"]').val(eventnum);
	})
})