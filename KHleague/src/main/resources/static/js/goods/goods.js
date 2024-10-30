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
