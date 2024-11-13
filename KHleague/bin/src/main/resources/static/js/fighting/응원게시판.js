// 로그인한 사용자 ID를 콘솔에 출력
console.log(loginUser);  // 세션에서 받은 로그인 사용자 ID 출력

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

// 투표 카운트 설정
let votes = {
	team1: 0,
	team2: 0
};

// 총 투표 수
let totalVotes = 0;

// 최대 투표 수 설정
const MAX_VOTES = 3;

// 투표 함수 (팀에 대한 투표를 처리)
function vote(team) {
	// 총 투표 수가 3표 미만일 경우에만 투표 가능
	if (totalVotes < MAX_VOTES) {
		// 팀에 대한 투표 카운트를 증가
		if (votes[team] < MAX_VOTES) {
			votes[team]++;
			totalVotes++;  // 전체 투표 수 증가

			// 서버에 투표 요청
			fetch(`/vote/voteForTeam?team=${team}`, {
				method: 'POST',
			})
				.then(response => response.text())
				.then(data => {
					getVoteCounts();  // 투표 후 상태 갱신
				})
				.catch(error => {
					console.error('투표 중 오류 발생:', error);
				});
		} else {
			alert(`${team === 'team1' ? '윤경수' : '윤혜정'} 팀에 이미 최대 투표수(${MAX_VOTES}회)를 초과했습니다.`);
		}
	} else {
		alert("사용자당 최대 3표씩 투표가 가능합니다!");
	}
}



// 투표 수를 가져오는 함수 (실시간 갱신)
function getVoteCounts() {
	$.ajax({
		url: '/vote/getVotes',
		method: 'GET',
		success: function(data) {
			// 받은 데이터에서 투표 수를 업데이트
			const team1Votes = data.team1Vote;
			const team2Votes = data.team2Vote;

			$('#team1_votes').text(team1Votes + '표');  // 팀1 투표 수를 'XX표'로 표시
			$('#team2_votes').text(team2Votes + '표');  // 팀2 투표 수를 'XX표'로 표시

			// 투표 수에 비례하여 배경 색상 너비 조정
			updateVoteBackground(team1Votes, team2Votes);
		},
		error: function(error) {
			console.error('투표 데이터 가져오기 실패:', error);
		}
	});
}

// 투표 수에 비례하여 배경 색상 너비를 조정하는 함수
function updateVoteBackground(team1Votes, team2Votes) {
	// 총 투표 수 계산
	const totalVotes = team1Votes + team2Votes;

	// 비율 계산 (0에서 100까지의 백분율로 변환)
	const team1Percentage = totalVotes > 0 ? (team1Votes / totalVotes) * 100 : 0;
	const team2Percentage = totalVotes > 0 ? (team2Votes / totalVotes) * 100 : 0;

	// 팀1 배경 너비 조정 (빨간색)
	$('.paper .point > div > div.left p').css('width', `${team1Percentage}%`);

	// 팀2 배경 너비 조정 (파란색)
	$('.paper .point > div > div.right p').css('width', `${team2Percentage}%`);
}

$(document).ready(function() {
	// 페이지 로딩 시 투표 수를 가져옵니다
	getVoteCounts();
});

// 전송 버튼 클릭 시 메시지 전송
document.querySelectorAll('.send-btn').forEach(button => {
	button.addEventListener('click', function() {
		const chatType = this.closest('.chat-section').id === 'left_chat' ? 1 : 2;  // 왼쪽 채팅 = 1, 오른쪽 채팅 = 2
		const messageContent = this.previousElementSibling.value.trim();  // 채팅 내용

		if (messageContent) {
			// 서버로 메시지 전송
			sendMessageToServer(messageContent, chatType);
		} else {
			alert("메시지를 입력해주세요.");
		}

		this.previousElementSibling.value = "";  // 메시지 입력칸 초기화
	});
});

// 메시지를 백엔드로 전송하는 함수
function sendMessageToServer(content, chatType) {
	fetch('/chat/sendMessage', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ content, chatType, userId: loginUser }), // userId를 세션에서 받아온 값으로 전송
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				console.log('메시지가 전송되었습니다.');
				// 전송 후 UI 갱신 처리 (예: 새 메시지를 채팅창에 추가)
				addMessageToChat(loginUser, content, chatType);  // 로그인된 사용자 ID와 메시지 내용 추가
			} else {
				console.error('메시지 전송에 실패했습니다.');
			}
		})
		.catch(error => console.error('Error:', error));
}

// 채팅 메시지를 화면에 추가하는 함수
function addMessageToChat(userId, content, chatType) {
	const chatContainer = chatType === 1 ? document.getElementById('left_chat_messages') : document.getElementById('right_chat_messages');
	const messageElement = document.createElement('div');
	messageElement.classList.add('chat_message');

	const username = document.createElement('div');
	username.classList.add('username');
	username.textContent = userId;

	const messageContent = document.createElement('div');
	messageContent.classList.add('message-content');
	messageContent.textContent = content;

	messageElement.appendChild(username);
	messageElement.appendChild(messageContent);

	chatContainer.appendChild(messageElement);
	// 스크롤을 최하단으로 이동
	chatContainer.scrollTop = chatContainer.scrollHeight;
}

document.addEventListener("DOMContentLoaded", function() {
	fetch('/chat/getMessages')
		.then(response => response.json())
		.then(messages => {
			messages.forEach(message => {
				addMessageToChat(message.userId, message.content, message.chatType);
			});
		})
		.catch(error => console.error('Error fetching messages:', error));
});
