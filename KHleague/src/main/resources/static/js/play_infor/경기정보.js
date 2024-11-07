document.addEventListener('DOMContentLoaded', () => {
	const dayTitleButtons = document.querySelectorAll('#day_title_btn');
	const dayMainButtons = document.querySelectorAll('#day_main_btn');
	const currentDateElement = document.getElementById('currentDate');
	const currentDateParagraph = currentDateElement.querySelector('p');

	// 날짜 형식 변환 함수
	const formatDate = (date) => {
		const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
		return date.toLocaleDateString('ko-KR', options).replace(/\//g, '.'); // '/'를 '.'로 교체
	};

	// 날짜 변경 함수
	const updateDate = (increment) => {
		const currentDate = new Date(currentDateElement.dataset.currentDate);
		currentDate.setDate(currentDate.getDate() + increment);
		currentDateElement.dataset.currentDate = currentDate.toISOString().split('T')[0];
		currentDateParagraph.textContent = formatDate(currentDate);
		highlightToday(currentDate);
		displayWeekday(currentDate); // 요일 표시 함수 호출
	};

	// 오늘 날짜 강조 함수
	const highlightToday = (date) => {
		const today = new Date();
		const todayFormatted = formatDate(today);
		const todayParagraph = document.querySelector('.days.today p.toda');

		if (todayFormatted === formatDate(date)) {
			currentDateElement.classList.add('today');
			todayParagraph.textContent = '오늘';
		} else {
			currentDateElement.classList.remove('today');
		}
	};

	// 요일 표시 함수
	const displayWeekday = (date) => {
		const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
		const weekdayText = weekdays[date.getDay()];
		currentDateParagraph.innerHTML = `${formatDate(date)} (${weekdayText})`; // 날짜와 요일 표시
	};

	// 버튼 클릭 이벤트 리스너
	dayTitleButtons.forEach(button => {
		button.addEventListener('click', () => updateDate(button.innerHTML.includes('right') ? 1 : -1));
	});

	dayMainButtons.forEach(button => {
		button.addEventListener('click', () => updateDate(button.innerHTML.includes('right') ? 1 : -1));
	});

	// 페이지 로드 시 오늘 날짜 강조 및 요일 표시
	highlightToday(new Date(currentDateElement.dataset.currentDate));
	displayWeekday(new Date(currentDateElement.dataset.currentDate));
});
