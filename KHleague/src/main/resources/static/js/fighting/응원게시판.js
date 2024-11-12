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
