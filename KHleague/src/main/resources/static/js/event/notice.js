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