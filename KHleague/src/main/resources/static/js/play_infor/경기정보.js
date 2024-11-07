document.addEventListener('DOMContentLoaded', () => {
	const dayTitleButtons = document.querySelectorAll('#day_title_btn');
	const dayMainButtons = document.querySelectorAll('#day_main_btn');
	const currentDateElement = document.getElementById('currentDate');
	const currentDateParagraph = currentDateElement.querySelector('p');
	const daysElements = document.querySelectorAll('.days'); // 날짜 이동 시 부드럽게 처리할 요소들
	const weekdaysElements = document.querySelectorAll('.weekday'); // 요일 요소도 이동

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

		// 부드러운 이동 애니메이션 효과
		daysElements.forEach(day => {
			day.style.transition = 'transform 0.3s ease'; // 애니메이션 효과 적용
			day.style.transform = `translateX(${increment * 100}%)`; // 날짜 이동
		});

		weekdaysElements.forEach(weekday => {
			weekday.style.transition = 'transform 0.3s ease'; // 애니메이션 효과 적용
			weekday.style.transform = `translateX(${increment * 100}%)`; // 요일 이동
		});

		// 애니메이션이 끝난 후 위치 초기화
		setTimeout(() => {
			daysElements.forEach(day => {
				day.style.transition = 'none'; // 애니메이션 제거
				day.style.transform = 'translateX(0)'; // 원위치로 돌아가게
			});
			weekdaysElements.forEach(weekday => {
				weekday.style.transition = 'none'; // 애니메이션 제거
				weekday.style.transform = 'translateX(0)'; // 원위치로 돌아가게
			});
		}, 300); // 0.3초 후 애니메이션 초기화

		// 오늘 날짜 강조 및 요일 표시 함수 호출
		highlightToday(currentDate);
		displayWeekday(currentDate); // 요일 표시 함수 호출
		filterGamesByDate(currentDate); // 날짜에 따른 경기 필터링 함수 호출
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

	// 경기 일정 필터링 함수
	const filterGamesByDate = (selectedDate) => {
		const selectedDateStr = selectedDate.toISOString().split('T')[0];

		// 예정된 경기 필터링
		const upcomingGames = Array.from(document.querySelectorAll('.play_infor_main .hi')).filter(game => {
			const gameDate = game.querySelector('[th\\:text="${game.gwtime}"]').textContent;
			return gameDate === selectedDateStr;
		});

		// 종료된 경기 필터링
		const endedGames = Array.from(document.querySelectorAll('.play_infor_main .hi')).filter(game => {
			const gameDate = game.querySelector('[th\\:text="${game.gEdate}"]').textContent;
			return gameDate === selectedDateStr;
		});

		// 모든 경기 숨기고 필터링된 경기만 보이기
		document.querySelectorAll('.play_infor_main .hi').forEach(game => game.style.display = 'none');
		upcomingGames.concat(endedGames).forEach(game => game.style.display = 'flex');
	};

	// 페이지 로드 시 오늘 날짜 강조 및 요일 표시, 경기 일정 필터링
	const initialDate = new Date(currentDateElement.dataset.currentDate);
	highlightToday(initialDate);
	displayWeekday(initialDate);
	filterGamesByDate(initialDate);
});
