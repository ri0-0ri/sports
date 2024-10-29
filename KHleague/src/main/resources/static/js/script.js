// 모달 보여주기
function close_modal() {
    $('.modal').css('display', 'none');
}
function open_modal() {
    $('.modal').css('display', 'block');
}

// team_info js
// 호버 시 팀 목록, 포지션 목록 보여줌
$(document).ready(function () {
    $('.team_txt').mouseover(function () {
        $('.team_box').addClass('show');
    });

    $('.team_box').mouseover(function () {
        $(this).addClass('show');
    });

    $('.team_txt, .team_box').mouseout(function () {
        $('.team_box').removeClass('show');
    });

    $('.position_txt').mouseover(function () {
        $('.position_box').addClass('show');
    });

    $('.position_box').mouseover(function () {
        $(this).addClass('show');
    });

    $('.position_txt, .position_box').mouseout(function () {
        $('.position_box').removeClass('show');
    });
});

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
