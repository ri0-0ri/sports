// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal(element, buynum) {
	const goodsid = $(element).attr('id');
	console.log(buynum);
	const thismodal = $(`.modal#${goodsid}`);
	thismodal.find('.goods_options').hide();
	thismodal.find(`.goods_options[data-buynum="${buynum}"]`).show();
    thismodal.css('display', 'block');	
}

// 폼 제출 각각 action 설정해주기
function submitForm(actionUrl, event) {
	const userid = $('input[name="userid"]').val();
	console.log(userid);
	if(!userid){
		alert("로그인 후 이용해주세요!");
		return;
	}
	
    event.preventDefault(); // 기본 제출 방지
    
    // 클릭된 버튼의 부모 폼 찾기
   	const form = $(event.target).closest('form');
	const formData = form.serialize();
	console.log(formData);
	console.log(actionUrl);
	console.log(form);
	$.ajax({
	        type: 'POST',
	        url: actionUrl,
	        data: formData,
	        success: function(response) {
				if(actionUrl === '/mypage/mypage_buy_modify'){
					alert("상품 옵션이 변경되었습니다!");
					location.reload();
				}
	            close_modal();
	        },
	        error: function(xhr, status, error) {
	            console.error("Submission failed:", xhr.responseText);
	        }
	    });
}

// 전체선택 누르면 다 선택
$(document).ready(function() {
	function checkall(){
		const checkall = $('#checkall');
		const checkboxes = $('input[type="checkbox"]').not('#checkall');
		checkall.click(function(){
			checkboxes.prop('checked', checkall.is(':checked'));
		});
	}
	checkall();
});

// 결제창 가기
function go_buy(event) {
    event.preventDefault();
    const buynums = [];
    
    // 체크박스들 찾고
    const checkboxes = $('input[type="checkbox"]:not(#checkall):checked');
    checkboxes.each(function() {
        const buynum = $(this).val();
        buynums.push(buynum);
    });

    if (buynums.length > 0) {
		const buynumsQS = buynums.map(buynum => `buynum=${buynum}`).join('&');
		window.location.href = '/payment/payment?' + buynumsQS;
    } else {
        alert("상품을 선택해 주세요.");
    }
}
