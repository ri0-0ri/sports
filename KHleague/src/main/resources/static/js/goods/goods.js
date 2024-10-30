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
function submitForm(actionUrl) {
    // 'this'를 사용하여 클릭된 버튼의 context를 가져옵니다.
    const form = $(event.target).closest('form'); // jQuery로 form 선택
    const formData = form.serialize(); // 폼 데이터를 직렬화합니다.
    console.log("Form Data:", formData); 
    console.log("Submitting form with userid:", form.find('input[name="userid"]').val()); // userid 출력

    $.ajax({
        type: 'POST',
        url: actionUrl,
        data: formData,
        success: function(response) {
            close_modal();
        },
        error: function(xhr, status, error) {
            console.error("Submission failed:", xhr.responseText);
            alert("폼 제출에 실패했습니다. 다시 시도해 주세요.");
        }
    });
}
