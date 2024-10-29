// index js
// 메뉴
$(document).ready(function () {
    $('.menu').mouseover(function () {
        $('.bar').addClass('show');
    });

    $('.menu').mouseover(function () {
        $(this).addClass('show');
    });

    $('.menu, .bar').mouseout(function () {
        $('.bar').removeClass('show');
    });
});

//종목 선택
$(document).ready(function () {
    $('.type').find('div').click(function () {
        $('.type').find('div').removeClass('type_act');
        $(this).addClass('type_act');
    });
});

// 굿즈샵 선택
$(document).ready(function () {
    $('.goods_type').click(function () {
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