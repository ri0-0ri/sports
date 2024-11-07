// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal(element) {
	const goodsid = $(element).attr('id');
	
	const thismodal = $(`.modal#${goodsid}`);	
    thismodal.css('display', 'block');
	
}

// goods js
// 팀 엠블럼 슬라이더
$(document).ready(function () {
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
    $('.left_btn p').click(function () {
        $('.teams').slick('slickPrev');
    });

    $('.right_btn p').click(function () {
        $('.teams').slick('slickNext');
    });
});

// 굿즈 타입 선택하면 바뀌기
$(document).ready(function () {
	// 처음 자동 outerwear
	$('.goods').css('display', 'none');
	$('.goods_select_btn:contains("Outerwear")').addClass('act');
	$('.goods#Outerwear').css('display', 'block');
	
	
	$('.goods_select_btn').click(function(){
		$('.goods_select_btn').removeClass('act');
		$(this).addClass('act');
		
		const goodsbtn = $(this).text().trim();
		
		$('.goods').each(function() {
			const goodstype = $(this).attr('id');
		    if(goodstype!=goodsbtn){
				$(this).css('display', 'none');
			}
			else{
				$(this).css('display', 'block');
			}
		});
   })
});

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
	const goodsnum = form.find('input[name=goodsnum]').val(); 
	console.log(goodsnum);

	if(actionUrl === '/mypage/mypage_buy'){
		// 장바구니에 중복된 상품 있는지? 확인 ajax
		$.ajax({
			type: 'POST',
			url: '/mypage/getbuynumBygoodsnum',
			data: { goodsnum: goodsnum, userid: userid },
			success: function(response) {
				if (response > 0 || response == null) {
					// 네 하면 장바구니의 상품 찾아서 수량 1개 업
					confirm("장바구니에 이미 해당 상품이 존재합니다! 추가하시겠습니까?");
				}
				else{
					// 없으면 ajx 수행			
					$.ajax({
						type: 'POST',
						url: actionUrl,
						data: formData,
						success: function(response) {
							alert("장바구니에 상품이 추가되었습니다!");
							if (confirm("장바구니 이동하기")) {
								window.location.href = "/mypage/mypage_buy";
							}
						},
						error: function(xhr, status, error) {
							console.error("Submission failed:", xhr.responseText);
						}
					});
				}
			},
			error: function(xhr, status, error) {
				console.error("Submission failed:", xhr.responseText);
			}
		});
		
	}
		
	
			
	else if(actionUrl === '/mypage/mypage_wish'){
		// 위시리스트에 중복된 상품 있는지? 확인 ajax
					
		//있으면 폼 제출
					
		// 없으면 제출 ㄴㄴ
					
		alert("위시리스트에 상품이 추가되었습니다!");
		if(confirm("위시리스트 보러가기")){
		window.location.href = "/mypage/mypage_wish";
		}
	}
	close_modal();
			
		
		
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
