document.getElementById("loginButton").addEventListener("click", function() {
	const userid = document.getElementById("id").value;
	const password = document.getElementById("password").value;

	if (!userid || !password) {
		alert("아이디와 비밀번호를 입력하세요.");
		return;
	}

	// AJAX 요청 보내기
	fetch("/login_page/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({ userid, password })
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				// 로그인 성공 시 알림 메시지 띄우고 index 페이지로 이동
				alert("로그인 되었습니다.");
				window.location.href = data.redirect; // 로그인 후 리다이렉션 URL
			} else {
				// 로그인 실패 시 오류 메시지 표시
				document.querySelector(".error-message").innerText = data.error;
			}
		})
		.catch(error => console.error("Error:", error));
});
