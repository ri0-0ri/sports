// 메뉴
$(document).ready(function() {
	$('.menu').mouseover(function() {
		$('.bar').addClass('show');
	});

	$('.menu').mouseover(function() {
		$(this).addClass('show');
	});

	$('.menu, .bar').mouseout(function() {
		$('.bar').removeClass('show');
	});
});

//종목 선택
$(document).ready(function() {
	$('.type').find('div').click(function() {
		$('.type').find('div').removeClass('type_act');
		$(this).addClass('type_act');
	});
});

// 굿즈샵 선택
$(document).ready(function() {
	$('.goods_type').click(function() {
		$('.goods_type').removeClass('act');
		$(this).addClass('act');
	});
});

// 달력 시작!
let date = new Date();

const renderCalendar = () => {
	const viewYear = date.getFullYear();
	const viewMonth = date.getMonth();

	document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

	const prevLast = new Date(viewYear, viewMonth, 0);
	const thisLast = new Date(viewYear, viewMonth + 1, 0);

	const PLDate = prevLast.getDate();
	const PLDay = prevLast.getDay();

	const TLDate = thisLast.getDate();
	const TLDay = thisLast.getDay();

	const prevDates = [];
	const thisDates = [...Array(TLDate + 1).keys()].slice(1);
	const nextDates = [];

	if (PLDay !== 6) {
		for (let i = 0; i < PLDay + 1; i++) {
			prevDates.unshift(PLDate - i);
		}
	}

	for (let i = 1; i < 7 - TLDay; i++) {
		nextDates.push(i);
	}

	const dates = prevDates.concat(thisDates, nextDates);
	const firstDateIndex = dates.indexOf(1);
	const lastDateIndex = dates.lastIndexOf(TLDate);

	dates.forEach((date, i) => {
		dates[i] = `<div class="date">${date}</div>`;
		const condition = i >= firstDateIndex && i < lastDateIndex + 1 ? 'this' : 'other';

		dates[i] = `<div class="date"><span class="${condition}">${date}</span></div>`;
	});

	document.querySelector('.dates').innerHTML = dates.join('');

	const today = new Date();
	if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
		for (let date of document.querySelectorAll('.this')) {
			if (+date.innerText === today.getDate()) {
				date.classList.add('today');
				break;
			}
		}
	}
}
renderCalendar();

const prevMonth = () => {
	date.setDate(1);
	date.setMonth(date.getMonth() - 1);
	renderCalendar();
}

const nextMonth = () => {
	date.setDate(1);
	date.setMonth(date.getMonth() + 1);
	renderCalendar();
}

const goToday = () => {
	date = new Date();
	renderCalendar();
}
// 달력 끝


// 메인 페이지 경기 정보 가져오기
document.addEventListener('DOMContentLoaded', function() {
	const matchDates = document.querySelectorAll('.match-date');

	// 경기 정보를 받아와서 각 날짜에 배경 추가
	matchDates.forEach(function(matchDateElement) {

		const gWdate = matchDateElement.getAttribute('data-date'); // 경기 날짜

		const date = new Date(gWdate);  // 경기 날짜를 Date 객체로 변환
		console.log(date);

		const day = date.getDate();  // 날짜만 가져옴
		const options = { weekday: 'long' };
		const dayOfWeek = new Intl.DateTimeFormat('ko-KR', options).format(date);  // 요일 계산

		// 경기 날짜와 요일 표시
		matchDateElement.textContent = `${day}일`;
		matchDateElement.nextElementSibling.textContent = dayOfWeek;

		// 달력에서 경기 날짜에 배경 추가
		addMatchBackground(gWdate, day);  // 경기 날짜에 해당하는 날짜에 배경 추가
	});
});

// 달력에 경기 날짜에 배경 추가하는 함수
function addMatchBackground(gWdate, day) {
	const dates = document.querySelectorAll('.date');  // 달력의 모든 날짜 요소
	const formattedDate = new Date(gWdate);
	const matchDay = formattedDate.getDate();  // 경기 날짜

	// 달력 날짜들 중 경기 날짜와 일치하는 날짜에 배경을 추가
	dates.forEach(dateElement => {
		const dateText = parseInt(dateElement.innerText);

		// 날짜가 일치하면 배경을 추가
		if (dateText === matchDay) {
			dateElement.classList.add('match-day');  // match-day 클래스 추가
		}
	});
}



// 메인 페이지 경기 정보 가져오깅
document.addEventListener('DOMContentLoaded', function() {
	const matchDates = document.querySelectorAll('.match-date');

	matchDates.forEach(function(matchDateElement) {

		const gWdate = matchDateElement.getAttribute('data-date');


		const date = new Date(gWdate);
		console.log(date)

		const day = date.getDate();
		const options = { weekday: 'long' };
		const dayOfWeek = new Intl.DateTimeFormat('ko-KR', options).format(date);


		matchDateElement.textContent = `${day}일`;
		matchDateElement.nextElementSibling.textContent = dayOfWeek;
	});
});