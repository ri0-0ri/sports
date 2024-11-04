// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal() {
    $('.modal').css('display', 'block');
}

// payment js
// 배송메모 선택
$(document).ready(function () {
    $('.check_memo').click(function () {
        $('.check_memo').removeClass('act');
        $(this).addClass('act');

        if ($(this).is('#self_input')) {
            $('.delevery_txt_self').css('display', 'block');
            $('.delevery_txt_self').focus();
        }
        else {
            $('.delevery_txt_self').css('display', 'none');
        }

        if ($(this).text().trim()) {
            if (!$(this).is('#self_input')) {
                $('#selected_delivery_txt').text($(this).text()).show();
            }
        }

        $('.delevery_txt_self').on('input', function () {
            $('#selected_delivery_txt').text($(this).val());
        });
    });
});

// 결제 수단
$(document).ready(function () {
    $('.choose_btn').click(function () {
        $('.in_btn').removeClass('actbtn');
        $(this).find('.in_btn').addClass('actbtn');
		
		if($(this).next('.sudan').text().trim()==="간편결제"){
			$('.pays').css('display','flex');
		}
		else {
		   $('.pays').css('display', 'none');
		}
		
		// 선택된 결제 방법의 수단 번호 설정
		const paymentMethod = $(this).next('.sudan').text().trim();
		let sudannum;
		switch (paymentMethod) {
			case "계좌결제":
				sudannum = "1"; // 예시로 1을 설정
				break;
			case "카드결제":
				sudannum = "2"; // 예시로 2를 설정
				break;
			case "일반결제":
				sudannum = "3"; // 예시로 3을 설정
				break;
			case "간편결제":
				sudannum = "4"; // 예시로 4를 설정
				break;
			default:
				sudannum = "0"; // 기본값
				break;
		}
		$('input[name="sudannum"]').val(sudannum);

    });
});

// 주소 검색 기능
function openPostCode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 도로명 주소를 해당 입력란에 채우기
			$('#useraddr').val(data.roadAddress);

			// 상세 주소 입력란으로 포커스 이동
			document.getElementById('userdetailaddr').focus();
		}
	}).open();
}

// 적립금 사용
$(document).ready(function () {
    // 적립금 사용
    $('#use_reward').on('input', function () {
        const previous_reward = parseInt($('.privious_reward').text().replace(/,/g, ''));
        const useReward = parseInt($(this).val()) || 0;

        const after_reward = previous_reward - useReward;

        $('.after_reward').text(after_reward.toLocaleString());
    });

    // 전액 사용 버튼 클릭 시 처리
    $('.use_btn').click(function () {
        const totalReward = parseInt($('.reward_num').text().replace(/,/g, ''));
        $('#use_reward').val(totalReward);
        $('#use_reward').trigger('input');
    });
	
	// 3000원 미만일때
	const totalReward = parseInt($('.reward_num').text().replace(/,/g, ''));
	if(totalReward<3000){
		$('.reward_txt').css('color','#FF1700');
		$('#use_reward').prop('readonly', true);
		$('.use_btn').off('click');
	}
});

// 결제방법 선택
$(document).ready(function(){
	$(".pays img").click(function () {
		$('input[name="sudannum"]').val("4");
		$(".pays img").addClass('nochoose');
		$(this).removeClass('nochoose');
		let gannum;
		if($(this).attr('id')==='open_kakao'){
			gannum = "1";
		}
		else if($(this).attr('id')==='open_naver'){
			gannum = "2";
		}
		else{
			gannum = "3";
		}		
		$('input[name="gannum"]').val(gannum);
	});
});

// order DTO 만들기
$(document).ready(function () {			
	$(".go_payment").click(function(e) {
		const deliveryPlace = $('#useraddr').val()+"//"+$('#userdetailaddr').val();
		console.log(deliveryPlace);
		let deliveryMemo = $('#selected_delivery_txt').text().trim();
		if(deliveryMemo==="배송 메모를 선택해 주세요"){
			deliveryMemo="배송메모 없음";
		}
		console.log(deliveryMemo);
		const totalPrice = $('.after_reward').eq(0).text().trim();
		console.log(totalPrice);
		const sudannum = $('input[name="sudannum"]').val()+"//"+$('input[name="gannum"]').val();
		console.log(sudannum);
		const userid = $('input[name="userid"]').val();
		console.log(userid);
		let goodsnum = [];
		$('.goodsnums').each(function() {
			goodsnum.push($(this).val());
		});
		goodsnum = goodsnum.join("//");
		console.log(goodsnum);

		$('input[name="deliveryPlace"]').val(deliveryPlace);
		$('input[name="deliveryMemo"]').val(deliveryMemo);
		$('input[name="totalPrice"]').val(totalPrice);
		$('input[name="sudannum"]').val(sudannum);
		$('input[name="userid"]').val(userid);
		$('input[name="goodsnum"]').val(goodsnum);
		
					
	});
});

// 카카오페이 결제 팝업창
$(document).ready(function () {	
	$(".go_payment").click(function(e) {
		const pnames = $('.product_name').text() + "외 " + (parseInt($('.total_num').text().trim()) - 1) + "건";
		console.log(pnames);

		const total = parseInt($('.after_reward').eq(0).text().trim());		
		console.log(total);

		let data = {
			"cid": "TC0ONETIME",
			"partner_order_id": "partner_order_id",
			"partner_user_id": "partner_user_id",
			"item_name": "초코파이",
			"quantity": "1",
			"total_amount": "2200",
			"vat_amount": "200",
			"tax_free_amount": "0",
			"approval_url": "https://developers.kakao.com/success",
			"fail_url": "https://developers.kakao.com/fail",
			"cancel_url": "https://developers.kakao.com/cancel"
		};

		/*	$.ajax({
				type: 'POST',
				url: '/payment/open_kakao',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function(response) {
					location.href = response.next_redirect_pc_url;
				}
			});
		});*/


		$.ajax({
			type: 'POST',
			url: '/payment/open_kakao',
			dataType: 'json',
/*			data: JSON.stringify(data),
			contentType: 'application/json',*/
			success: function(data) {
				alert(JSON.stringify(data));
				var box = data.next_redirect_pc_url;
				window.open(box);
			}
		});
	})
});
