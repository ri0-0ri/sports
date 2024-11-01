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
        const todayText = formatDate(date); // 오늘 날짜 형식화
        const todayParagraph = document.querySelector('.days.today p.toda'); // 오늘 날짜의 p 태그 선택

        if (date.toDateString() === today.toDateString()) {
            currentDateElement.classList.add('today');
            todayParagraph.textContent = todayText; // 오늘 날짜 텍스트 업데이트
        } else {
            currentDateElement.classList.remove('today');
        }
    };

    // 요일 표시 함수
    const displayWeekday = (date) => {
        const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
        const weekdayIndex = date.getDay(); // 요일 인덱스
        const weekdayText = weekdays[weekdayIndex]; // 해당 요일 텍스트
        const dayDateDiv = document.querySelector('.day_date'); // 요일을 표시할 div 선택
        dayDateDiv.innerHTML = `<p>${formatDate(date)} (${weekdayText})</p>`; // 날짜와 요일 표시
    };

    // 버튼 클릭 이벤트 리스너
    dayTitleButtons.forEach(button => {
        button.addEventListener('click', () => updateDate(button.innerHTML.includes('right') ? 1 : -1));
    });

    dayMainButtons.forEach(button => {
        button.addEventListener('click', () => updateDate(button.innerHTML.includes('right') ? 1 : -1));
    });

    highlightToday(new Date(currentDateElement.dataset.currentDate));
    displayWeekday(new Date(currentDateElement.dataset.currentDate)); // 초기 로드 시 요일 표시
});
