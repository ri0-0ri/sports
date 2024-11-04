document.addEventListener("DOMContentLoaded", () => {
	loadUsers();

	function loadUsers() {
		fetch('/admin/users')
			.then(response => response.json())
			.then(users => {
				const userList = document.getElementById("user-list");
				userList.innerHTML = ''; // 기존 내용 초기화
				users.forEach(user => {
					const row = document.createElement("tr");
					row.innerHTML = `
                        <td>${user.userid}</td>
                        <td>${user.userpw}</td>
                        <td>${user.username}</td>
                        <td>${user.userphone}</td>
                        <td>${user.useraddr}</td>
                        <td>${user.userReward}</td>
                        <td>${user.userbirth}</td>
                        <td>${user.usergender}</td>
                        <td>${user.userhomenum}</td>
                        <td>${user.userjoin}</td>
                        <td><button class="delete-btn" data-id="${user.userid}">삭제</button></td>
                    `;
					userList.appendChild(row);
				});

				// 삭제 버튼 이벤트 리스너
				document.querySelectorAll(".delete-btn").forEach(button => {
					button.addEventListener("click", () => {
						const userid = button.getAttribute("data-id");
						if (confirm("정말로 삭제하시겠습니까?")) {
							fetch(`/admin/deleteUser?userid=${userid}`, {
								method: 'DELETE',
							})
								.then(response => {
									if (response.ok) {
										alert("유저가 삭제되었습니다.");
										loadUsers(); // 유저 리스트 새로고침
									} else {
										alert("유저 삭제에 실패했습니다.");
									}
								});
						}
					});
				});
			});
	}
});
