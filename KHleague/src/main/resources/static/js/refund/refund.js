// refund js
// 결제 수단 선택 > DB에서 수단값 받아와서 나머지 div에 selected 클래스 추가 및 해당 div에 actbtn클래스 추가

// 사유 선택
$(document).ready(function () {
    $('.choose_btn_reason').click(function () {
        $('.in_btn_reason').removeClass('actbtn');
        $(this).find('.in_btn_reason').addClass('actbtn');
		
		const refundMethod = $(this).next('.sudan_reason').text().trim();
		let your_reason;
		switch (refundMethod) {
			case "단순 변심":
				your_reason = "단순 변심";
				break;
			case "상품이 마음에 들지 않음":
				your_reason = "상품이 마음에 들지 않음";
				break;
			case "다른 상품이 배송됨":
				your_reason = "다른 상품이 배송됨";
				break;
			case "배송 지연":
				your_reason = "배송 지연";
				break;
			default:
				your_reason = "기타 사유";
				break;
		}
		$('.your_reason').text(your_reason);
		console.log("이유"+$('.your_reason').text());
    });
	
    // 입력 필드 클릭 시 actbtn 클래스 제거
    $('#self_reason_txt').on('focus', function () {
        $('.in_btn_reason').removeClass('actbtn');
		$('#other_reason').addClass('actbtn');
		your_reason = "기타 사유";
		$('.your_reason').text(your_reason);
		console.log("이유"+$('.your_reason').text());
    });
	
	// 사용자가 입력할 때마다 기타 사유와 함께 입력값 추가
	$('#self_reason_txt').on('input', function () {
	    const inputValue = $(this).val();
	    if (inputValue.trim() !== '') {
	        your_reason = "기타 사유 - " + inputValue;
	    } else {
	        your_reason = "기타 사유";
	    }
	    $('.your_reason').text(your_reason);
	    console.log("이유: " + your_reason);
	});
});

// 배송지
$(document).ready(function () {
	let deliveryplace = $('.deliveryplace').text().trim();
		if(deliveryplace.includes("//")){
			deliveryplace = deliveryplace.replace("//"," ");
		$('.deliveryplace').text(deliveryplace);		
	}	
});

// 총 가격
$(document).ready(function () {
	let total = 5000;
	$('.goodsprice').each(function(){
		const goods = parseInt($(this).text().trim());
		total += goods;
		console.log(total);
	})
	$('.total').text(total+' 원');
});

// 환불수단
$(document).ready(function () {
    const sudan = $('.payment_choose_box').attr('id');
    console.log("결제수단"+sudan);
		
	if (sudan == 1) {
	      // 포인트결제
	      $('.payment_choose_box .choose').each(function () {
	          const paymentMethod = $(this).find('.sudan').text().trim();
	          if (paymentMethod === '포인트결제') {
	              $(this).find('.in_btn').addClass('actbtn');
	          }
	      });
	  }
	  else if (sudan == 2) {
	      // 간편 결제
	      $('.payment_choose_box .choose').each(function () {
	          const paymentMethod = $(this).find('.sudan').text().trim();
	          if (paymentMethod === '간편결제') {
	              $(this).find('.in_btn').addClass('actbtn');
				  $('.gan_refund').css('display','flex');
	          }
	      });
	  }
	  else if (sudan == 3) {
	      // 적립금 결제
	      $('.payment_choose_box .choose').each(function () {
	          const paymentMethod = $(this).find('.sudan').text().trim();
	          if (paymentMethod === '적립금결제') {
	              $(this).find('.in_btn').addClass('actbtn');
	          }
	      });
	  }
	
});

// 환불금액 계산
$(document).ready(function(){
	let refund_price = $('.total').eq(0).text().trim();
	console.log(refund_price);	
	$('.refund').text(refund_price);	
})

// 환불하기, 취소하기
$(document).ready(function () {
    $('.no_refund').click(function(){
        confirm("정말 환불을 취소하시겠습니까?");
		if(confirm){
			alert("환불이 취소되었습니다!");		
			window.history.back();
		}
    })
    $('.ok_refund').click(function(){
		const userConfirmed = confirm("정말 환불하시겠습니까?");
		if (userConfirmed) {
			const ordernum = $('.ordernum').text().trim();
			const userid = $('.userid').text().trim();
			const reason = $('.your_reason').text();
			console.log(ordernum+userid+"이유 : "+reason);
			
		    // 결제수단 확인
			// 포인트나 적립금 결제
		    if ($('#point').hasClass('actbtn') || $('#reward').hasClass('actbtn')) {
		        $.ajax({
					url: '/refund/okrefund',
					type: 'POST',
					data: {
					    ordernum: ordernum,
					    userid: userid,
						reason: reason
					},
					success: function(response) {
					    alert("환불이 완료되었습니다.");
					    window.location.href = "/mypage/mypage_order";
					},
					error: function(xhr, status, error) {
					    alert("환불 처리 중 오류가 발생했습니다.");
					}
				})
		    }
			
			// 간편결제
			else{
				
			}
		}
    });
});