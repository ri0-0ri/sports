// refund js
// 결제 수단 선택 > DB에서 수단값 받아와서 나머지 div에 selected 클래스 추가 및 해당 div에 actbtn클래스 추가

// 사유 선택
$(document).ready(function () {
    $('.choose_btn_reason').click(function () {
        $('.in_btn_reason').removeClass('actbtn');
        $(this).find('.in_btn_reason').addClass('actbtn');
    });
    // 입력 필드 클릭 시 actbtn 클래스 제거
    $('#self_reason_txt').on('focus', function () {
        $('.in_btn_reason').removeClass('actbtn');
    });
});

// 환불하기, 취소하기
$(document).ready(function () {
    $('.no_refund').click(function(){
        alert("환불이 취소되었습니다!");
        // 로케이션 리턴해주기~
    })
    $('.ok_refund').click(function(){
        confirm("정말 환불하시겠습니까?");
    });
});

$(document).ready(function () {
	// 배송지
	let deliveryplace = $('.deliveryplace').text().trim();
		if(deliveryplace.includes("//")){
			deliveryplace = deliveryplace.replace("//"," ");
		$('.deliveryplace').text(deliveryplace);		
	}
	
});