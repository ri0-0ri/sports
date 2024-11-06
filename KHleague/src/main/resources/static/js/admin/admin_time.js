document.addEventListener("DOMContentLoaded", function() {
	const form = document.querySelector('form');

	form.addEventListener('submit', function(event) {
		// 폼이 제출되기 전에 알림창을 띄움
		alert("추가되었습니다!");
	});
});


