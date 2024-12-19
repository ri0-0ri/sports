$(document).ready(function() {
    // .makebtn을 클릭하면 해당하는 .make_wrap을 보이거나 숨기게 하기
    $('.makebtn').on('click', function() {
        var makeWrap = $(this).closest('.no_winner').find('.make_wrap');

        // make_wrap이 보이고 있다면 숨기고, 숨겨져 있다면 보이게 함
        if (makeWrap.css('display') === 'flex') {
            makeWrap.css('display', 'none');
            $(this).text('추첨하기');  // 텍스트를 '추첨하기'로 변경
        } else {
            makeWrap.css('display', 'flex');
            $(this).text('닫기');  // 텍스트를 '닫기'로 변경
        }
    });
});

let interval;
let isSpinning = false;
let isWinnerDrawn = false;
let userIds = [];

// START 버튼 클릭 시
$(document).on("click", ".startbtn", function() {
    if (isSpinning) return; // 이미 동작 중이면 중복 클릭 방지

    // 아이디 목록 초기화
    userIds = [];

    const rulletBox = $(this).closest('.rullet_box');
    const winnerBox = rulletBox.find('.winnerbox');
    const rullet = rulletBox.find('.rullet');
    const ids = rullet.find('.ids');

    // 두근두근~이 아닌 경우에만 룰렛 시작
    if (winnerBox.text().trim() !== "두근두근~") {
        alert("조작은 안돼요~ 이미 당첨자가 정해졌네요!");
        return;
    }

    // .ids 요소들의 텍스트를 배열에 저장
    ids.each(function() {
        userIds.push($(this).text().trim());
    });
    console.log(userIds);

    rulletBox.find('.rulletgotxt').hide();
    rullet.find('.ids').show(); 

    // 룰렛이 동작 중인 상태로 변경
    isSpinning = true;

    let currentTop = 0;
    ids.each(function(index) {
        $(this).css("position", "absolute");
        $(this).css("top", `${currentTop}px`);
        currentTop += 30; // 각 요소 사이에 30px 간격 두기
    });

    // 아이디들이 위로 올라가도록 setInterval 사용
    interval = setInterval(function() {
        ids.each(function() {
            const currentTop = parseInt($(this).css("top"), 10);

            // 2px씩 위로 이동
            $(this).css("top", (currentTop - 2) + "px");

            // 아이디들이 다 올라가면 처음 아이디로 돌아가도록 설정
            if (currentTop <= -30) {
                $(this).css("top", `${30 * userIds.length}px`);
            }
        });
    }, 30); // 30ms마다 위치 변경
});

// STOP 버튼 클릭 시
$(document).on("click", ".stopbtn", function() {
    if (!isSpinning) return; // 룰렛이 동작하지 않으면 멈추지 않음

    // 동작 중지
    clearInterval(interval);
    isSpinning = false;

    const rulletBox = $(this).closest('.rullet_box');
    const rullet = rulletBox.find('.rullet');
    const ids = rullet.find('.ids');

    // .rullet의 위치 정보
    const rulletRect = rullet[0].getBoundingClientRect();

    // 가장 위에 위치한 .ids 요소를 찾기
    let minTop = Infinity;
    let selectedIdElement = null;

    ids.each(function() {
        const idRect = this.getBoundingClientRect();
        const relativeTop = idRect.top - rulletRect.top;

        if (relativeTop < minTop) {
            minTop = relativeTop;
            selectedIdElement = this;
        }
    });

    // 선택된 아이디의 텍스트 가져오기
    const selectedUser = selectedIdElement ? $(selectedIdElement).text().trim() : null;
    rulletBox.find('.winnerbox').text(selectedUser);
	
	isWinnerDrawn = true;	
});

// 발표하러가기
$('.go_write').on('click', function() {
	const rulletBox = $(this).closest('.rullet_box');
	const winnerBox = rulletBox.find('.winnerbox');
	if(winnerBox.text().trim()=="두근두근~"){
		alert("추첨 먼저 하세요!");
	}
	
	const eventnum = rulletBox.find('#eventnum').text().trim();
	console.log(eventnum);
	const winner = winnerBox.text().trim();
	console.log(winner);
	
	$.ajax({
		url: '/event/updatewinner',
		type: 'POST',
		data: {
			eventnum:eventnum,
			winner:winner
		},
		success: function (response) {
			alert(response);
			location.href = "/event/write?eventnum=" + eventnum;
		},
		error: function (xhr, status, error) {
			console.error("당첨자 추가 실패 : ", error);
		}
	});
});

// 참여자 없을때 발표하러가기
$('.go_write_no_winner').on('click', function() {	
	
	const eventnum = $('#eventnum').text().trim();
	console.log(eventnum);
	const winner = "당첨자 없음";
	console.log(winner);
	
	$.ajax({
			url: '/event/updatenowinner',
			type: 'POST',
			data: {
				eventnum:eventnum,
				winner:winner
			},
			success: function (response) {
				alert(response);
				location.href = "/event/write?eventnum=" + eventnum;
			},
			error: function (xhr, status, error) {
				console.error("당첨자 추가 실패 : ", error);
			}
		});
});

// 페이지 이동 방지
$(window).on('beforeunload', function(e) {
    if (!isWinnerDrawn) {
        e.preventDefault();
        e.returnValue = '';
    }
});