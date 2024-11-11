// 투표 카운트 설정
let votes = {
	team1: 0,
	team2: 0
};

// 타이머 설정
let timer = document.getElementById('timer');
let countdown = setInterval(function() {
	let time = timer.innerText.split(':');
	let hours = parseInt(time[0]);
	let minutes = parseInt(time[1]);
	let seconds = parseInt(time[2]);

	if (seconds > 0) {
		seconds--;
	} else if (minutes > 0) {
		minutes--;
		seconds = 59;
	} else if (hours > 0) {
		hours--;
		minutes = 59;
		seconds = 59;
	}

	timer.innerText = `${hours < 10 ? '0' : ''}${hours}:${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
}, 1000);

// 투표 함수
function vote(team) {
	// 최대 5번까지 투표 가능
	if (votes[team] < 5) {
		votes[team]++;
		document.getElementById(`${team}_votes`).innerText = `${votes[team]} 표`;
		updateVoteBar();

		if (votes[team] === 5) {
			alert(`사용자당 최대 5표씩 투표 가능합니다!`);
		}
	} else {
		alert(`사용자당 최대 5표씩 투표 가능합니다!`);
	}
}

// 투표 바 업데이트
function updateVoteBar() {
	let totalVotes = votes.team1 + votes.team2;
	let team1Percentage = (votes.team1 / totalVotes) * 100 || 0;
	let team2Percentage = (votes.team2 / totalVotes) * 100 || 0;

	document.querySelector('.left p').style.width = `${team1Percentage}%`;
	document.querySelector('.right p').style.width = `${team2Percentage}%`;
}

