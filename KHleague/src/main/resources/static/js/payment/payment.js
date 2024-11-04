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

// 전액사용시
$(document).ready(function () {
    $('.use_btn').click(function () {
        $('#use_reward').val($('.reward_num').text()).show();
    });
});

// 결제 수단
$(document).ready(function () {
    $('.choose_btn').click(function () {
        $('.in_btn').removeClass('actbtn');
        $(this).find('.in_btn').addClass('actbtn');
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