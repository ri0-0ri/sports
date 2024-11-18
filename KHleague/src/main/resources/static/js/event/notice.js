/* 모든 plus_info와 close_info에 대해 이벤트 리스너 설정 */
document.querySelectorAll(".plus_info").forEach(button => {
	button.addEventListener("click", function() {
		// 현재 m_list 내에서 list_info 찾기
		const list_info = this.closest(".notice_list").querySelector(".list_info");
		// 현재 row 찾기
		const list = this.closest(".row");

		// 클릭 시 효과
		list.classList.add("active");

		// 리스트 보여주기
		list_info.style.display = "block";
		list_info.style.opacity = "0";
		list_info.style.transform = "translateY(20px)";

		// 요청 애니메이션 프레임
		requestAnimationFrame(() => {
			// 두 번 호출하여 부드러운 애니메이션 효과 추가
			requestAnimationFrame(() => {
				// 불투명도 설정
				list_info.style.opacity = "1";
				// 원래 위치로 이동
				list_info.style.transform = "translateY(0)";
			});
		});
	});
});

document.querySelectorAll(".close_info").forEach(button => {
	button.addEventListener("click", function() {
		const list_info = this.closest(".list_info");
		const list = list_info.closest(".notice_list").querySelector(".row");

		list.classList.remove("active");
		
		list_info.style.opacity = "0";
		list_info.style.transform = "translateY(20px)";
		setTimeout(() => {
			list_info.style.display = "none";
		}, 500); // 애니메이션 시간과 맞추기
	});
});

$(document).ready(function(){
	$('.boardtime').each(function(){	
		const boardtime = $(this).text().trim().replace("T", " ");
		$(this).text(boardtime);
	})
	
	$('.infoboardtime').each(function(){	
		const infoboardtime = $(this).text().trim().replace("T", " ");
		$(this).text(infoboardtime);
	})
})

function back(){
	const confirmAction = confirm("정말 작성을 취소하시겠습니까?");
	if(confirmAction){
		 window.history.back();
	}
}

function submitform(){
	const form = $('#writeform');

	// 각 input, textarea의 값을 직접 가져와서 출력
	const eventnum = $('input[name="eventnum"]').val();
	const eboardtitle = $('input[name="eboardtitle"]').val();
	const eboardcontent = $('textarea[name="eboardcontent"]').val();
	const str = $('input[name="str"]').val();
	
	if($('.str')!=null){
		$('input[name="eventcon"]').val(str);
	}

	// 콘솔에 출력
	console.log('eventnum:', eventnum);
	console.log('eboardtitle:', eboardtitle);
	console.log('eboardcontent:', eboardcontent);
	console.log('str:', str);
	
	form.submit();
}

function submitform_update(){
	const form = $('#updateform');

	// 각 input, textarea의 값을 직접 가져와서 출력
	const eventnum = $('input[name="eventnum"]').val();
	const eboardtitle = $('input[name="eboardtitle"]').val();
	const eboardcontent = $('textarea[name="eboardcontent"]').val();
	const str = $('input[name="str"]').val();

	// 콘솔에 출력
	console.log('eventnum:', eventnum);
	console.log('eboardtitle:', eboardtitle);
	console.log('eboardcontent:', eboardcontent);
	console.log('str:', str);
	
	form.submit();
}

function delete_board(e){	
	const notice_list = $(e).closest('.notice_list'); 

	const eboardnum = notice_list.find('.eboardnum').text();
	console.log('eboardnum:', eboardnum);  // eboardnum 값 확인

    $.ajax({
        url: '/admin/deleteebaord',
        type: 'POST',
        data: { eboardnum: eboardnum },
        success: function (response) {
            alert(response);
            location.reload();
        },
        error: function (xhr, status, error) {
            console.error("이벤트보드 삭제 실패 : ", error);
        }
    });
}

function modify_board(e){
	const notice_list = $(e).closest('.notice_list'); 

	const eboardnum = notice_list.find('.eboardnum').text();
	console.log('eboardnum:', eboardnum);  // eboardnum 값 확인
	
	location.href = "/event/modify?eboardnum=" + eboardnum;
}

// 조회수
$(document).ready(function() {
    // .plus_info 클릭 시
    $(".plus_info").on("click", function() {
        const eboardnum = $(this).closest('.row').find('.eboardnum').text();
		console.log("쿠키보드넘"+eboardnum);
		let eboardcount = $(this).closest('.row').find('.eboardcount');
		let intcount = parseInt(eboardcount.text());
		console.log("보드카운트"+intcount);
		
		$.ajax({
		    url: '/admin/pluscount', 
		    type: 'POST',
		    data: { eboardnum: eboardnum }, 
		    success: function(response) {
		        if (response) {
					console.log("쿠키증가완료");
					intcount += 1;
		            eboardcount.text(intcount);
		        }
		    },
		    error: function() {
		        alert("서버와의 통신에 실패했습니다.");
		    }
		});	
				
    });
});

/* 보기 구현 */
function change_view() {
    const view_type = $("#view_type").val();
    
	const currentUrl = new URL(window.location.href);	   
	currentUrl.searchParams.set('view_type', view_type);	   
	window.location.href = currentUrl.toString();	
}