// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal() {
    $('.modal').css('display', 'block');
}
function close_payment() {
    $('.wrapper').css('display', 'none');
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

let isRewardPay = false;
// 결제 수단
$(document).ready(function () {
    $('.choose_btn').click(function () {
		if (isRewardPay) {
			return; 
		}
				
        $('.in_btn').removeClass('actbtn');
        $(this).find('.in_btn').addClass('actbtn');
		
		if($(this).next('.sudan').text().trim()==="포인트결제"){
			$('.point').css('display','block');
			const have_point = parseInt($('.have_point').text().trim());
			const totalPrice = parseInt($('.after_reward').eq(0).text().trim());
			if(have_point>totalPrice){
				$('#for_point').addClass('opa');
				$('.userpoint').prop('readonly', true);
			}
		}
		else {
		   $('.point').css('display', 'none');
		}
		
		// 선택된 결제 방법의 수단 번호 설정
		const paymentMethod = $(this).next('.sudan').text().trim();
		let sudannum;
		switch (paymentMethod) {
			case "포인트결제":
				sudannum = "1"; // 예시로 1을 설정
				break;
			case "간편결제":
				sudannum = "2"; // 예시로 4를 설정
				break;
			default:
				sudannum = "3"; // 포인트결제
				break;
		}
		$('input[name="sudannum"]').val(sudannum);
		console.log(sudannum);
    });
});

function cancelpoint(){
	$('.userpoint').val('');
}

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

        $('.after_reward').text(after_reward);
    });

    // 전액 사용 버튼 클릭 시 처리
    $('.use_btn').click(function () {
        const totalReward = parseInt($('.reward_num').text().replace(/,/g, ''));
		const totalPrice = parseInt($('.after_reward').eq(0).text().trim());
		console.log(totalReward + "+"+totalPrice);
		// 적립금 결제한것
		if(totalReward>totalPrice){
			$('#use_reward').val(totalPrice);
			$('#rewardpay').find('.in_btn').addClass('actbtn');
			isRewardPay = true;
			$('input[name="sudannum"]').val("3");
		}
		else{
        	$('#use_reward').val(totalReward);			
		}
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

// order DTO 만들고 결제진행
$(document).ready(function () {			
	$(".go_payment").click(function(e) {
/*		const deliveryPlace = $('#useraddr').val()+"//"+$('#userdetailaddr').val();*/
		let deliveryPlace = $('#useraddr').val();
		const userdetailaddr = $('#userdetailaddr').val();
		if (userdetailaddr !== "") {
		    deliveryPlace = deliveryPlace+"//"+userdetailaddr;
		}			
		console.log("배송장소 "+deliveryPlace);
		
		let deliveryMemo = $('#selected_delivery_txt').text().trim();
		if(deliveryMemo==="배송 메모를 선택해 주세요"){
			deliveryMemo="배송메모 없음";
		}
		console.log("배송메모 "+deliveryMemo);
		
		const totalPrice = parseInt($('.after_reward').eq(0).text().trim());
		console.log("총 금액 "+totalPrice);
		
		const sudannum = $('input[name="sudannum"]').val();
		console.log("수단넘버 "+sudannum);
		
		const userid = $('input[name="userid"]').val();
		console.log(userid);
		
		let goodsnum = [];
		$('.goodsnums').each(function() {
			goodsnum.push($(this).val());
		});
		if (goodsnum.length > 1) {
		    goodsnum = goodsnum.join("//");
		} else {
		    goodsnum = goodsnum[0] || "";
		}
		console.log("굿즈넘버들 "+goodsnum);
		
		let ordername = "";
		if(parseInt(document.querySelector('.total_num').textContent.trim()) > 1){		
			ordername = document.querySelector('.product_name').textContent + "외 " + (parseInt(document.querySelector('.total_num').textContent.trim()) - 1) + "건";
		}
		else{
			ordername = document.querySelector('.product_name').textContent
		}
		console.log("오더네임"+ordername);
		
		let finalBuynum = [];
		$('input[name="buynums"]').each(function() {
		    finalBuynum.push($(this).val());
		});
		finalBuynum = finalBuynum.join("//");
		$('input[name="buynum"]').val(finalBuynum); 
		console.log("최종확인: " + $('input[name="buynum"]').val());
	
		const userReward = $('input[name="userReward"]').val();
		console.log("리워드"+userReward);
		const userpoint = $('input[name="userpoint"]').val();
		console.log("포인트"+userpoint);	

		$('input[name="deliveryPlace"]').val(deliveryPlace);
		$('input[name="deliveryMemo"]').val(deliveryMemo);
		$('input[name="totalPrice"]').val(totalPrice);
		$('input[name="sudannum"]').val(sudannum);
		$('input[name="userid"]').val(userid);
		$('input[name="ordername"]').val(ordername);
		
		$('input[name="goodsnums"]').val(goodsnum);
		$('input[name="userReward"]').val(userReward);
		$('input[name="userpoint"]').val(userpoint);
		
		// 수단넘버에 따라 결제 진행
		if (sudannum === "1") {
			const form = $('#paymentForm');
			const formDataArray = form.serializeArray();
			console.log(formDataArray);				
			form.submit();
		}			
		// 토스페이먼츠 오픈
		else if (sudannum === "2") {
			$('.wrapper').css('display', 'block');
		}		
	});
});

// 포인트 충전
$(document).ready(function () {	
	$(".putpoint").click(function() {
		const userpointhap = (parseInt($('.userpoint').val())+parseInt($('.have_point').text()));
		console.log(userpointhap);
		const totalPrice = parseInt($('.after_reward').eq(0).text().trim());
		console.log(totalPrice);
				
		if(userpointhap<totalPrice){
			alert("결제 잔액보다 충전금액이 모자랍니다!");
		}
		else{
			alert("해당 금액만큼 충전 후 결제 진행됩니다!");
			$(".putpoint").css('background-color','#0344DC');
		}
	})
});