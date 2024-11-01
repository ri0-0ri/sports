// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal(element) {
	const goodsid = $(element).attr('id');
	
	const thismodal = $(`.modal#${goodsid}`);	
    thismodal.css('display', 'block');
	
}

// 폼 제출 각각 action 설정해주기
function submitForm(actionUrl, event) {
    event.preventDefault(); // 기본 제출 방지
    
    // 클릭된 버튼의 부모 폼 찾기
    const form = $(event.target).closest('form'); 
	const formData = form.serialize();
	$.ajax({
	        type: 'POST',
	        url: actionUrl,
	        data: formData,
	        success: function(response) {
				if(actionUrl === '/mypage/mypage_buy'){
					alert("장바구니에 상품이 추가되었습니다!");
				}
				else if(actionUrl === '/mypage/mypage_wish'){
					alert("위시리스트에 상품이 추가되었습니다!");
				}
	            close_modal();
	        },
	        error: function(xhr, status, error) {
	            console.error("Submission failed:", xhr.responseText);
	        }
	    });
}
