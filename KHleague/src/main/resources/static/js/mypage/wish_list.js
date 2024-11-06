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
    
    if (actionUrl === '/mypage/delete_wish') {
        const goodsnum = form.attr('id');
        const userid = $('input[name="userid"]').val();
        
        // wishnum을 가져오는 AJAX 요청
        $.ajax({
            type: 'POST',
            url: '/mypage/getwishnumBygoodsnum',
            data: { goodsnum: goodsnum, userid: userid },
            success: function(response) {
                const wishnum = response;
                console.log("받아온 wishnum: " + wishnum);

                // wishnum을 사용하여 삭제 요청
                $.ajax({
                    type: 'POST',
                    url: actionUrl,
                    data: { wishnum: wishnum },
                    success: function(response) {
                        alert("상품이 삭제되었습니다!");
                        close_modal();
						location.reload();
                    },
                    error: function(xhr, status, error) {
                        console.error("삭제 실패:", xhr.responseText);
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error("wishnum 가져오기 실패:", xhr.responseText);
            }
        });
    }
	else if(actionUrl === '/mypage/mypage_buy'){
        const formData = form.serialize();
        $.ajax({
            type: 'POST',
            url: actionUrl,
            data: formData,
            success: function(response) {
                if (actionUrl === '/mypage/mypage_buy') {
                    alert("장바구니에 상품이 추가되었습니다!");
                    if (confirm("장바구니 이동하기")) {
                        window.location.href = "/mypage/mypage_buy";
                    }
                }
                close_modal();
            },
            error: function(xhr, status, error) {
                console.error("Submission failed:", xhr.responseText);
            }
        });
    }
	else if (actionUrl === '/payment/single_payment') {
		$.ajax({
			type: 'GET',
			url: actionUrl,
			data: formData,
			success: function(response) {
				if (actionUrl === '/payment/single_payment') {
					// 쿼리스트링 만들고 넘겨주기
					// 가져가는것 userid, goodsnum, size, quantity > formData
					// formData 쿼리스트링으로 바꿔주기
					window.location.href = '/payment/single_payment?' + formData;
				}
				close_modal();
			},
			error: function(xhr, status, error) {
				console.error("Submission failed:", xhr.responseText);
			}
		});
	}
}
