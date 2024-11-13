$(document).ready(function() {
	// 모달 열기
	$('.change').on('click', function() {
		const modalId = $(this).data('modal');
		$('#' + modalId).show();
	});

	// 모달 닫기
	$('.closeModal').on('click', function() {
		$(this).closest('.modal').hide();
	});

	// 사용자 이름 변경
	$('#confirmUsername').on('click', function() {
		const newUsername = $('#usernameInput').val();
		updateUserInfo('username', newUsername, 'usernameModal');
	});

	// 전화번호 변경
	$('#confirmUserphone').on('click', function() {
		const newUserphone = $('#userphoneInput').val();
		updateUserInfo('userphone', newUserphone, 'userphoneModal');
	});

	// 주소 검색 (이벤트 바인딩)
	$(document).on('click', '#searchAddress', function() {
		openPostCode();
	});

	// 주소 변경
	$('#confirmUseraddr').on('click', function() {
		const useraddr = $('#useraddr').val();
		const userdetailaddr = $('#userdetailaddr').val();
		const fullAddress = useraddr + ' ' + userdetailaddr; // 전체 주소 조합
		updateUserInfo('useraddr', fullAddress, 'useraddrModal');
	});

	// 사용자 정보 업데이트 함수
	function updateUserInfo(field, value, modalId) {
		$.ajax({
			url: '/updateUserInfo',
			method: 'POST',
			data: { field: field, value: value },
			success: function() {
				alert('변경되었습니다!');
				$('span[data-field="' + field + '"]').text(value);
				$('#' + modalId).hide();
			},
			error: function() {
				alert('정보 업데이트 실패');
			}
		});
	}

	// 주소 검색 함수
	function openPostCode() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 도로명 주소를 해당 입력란에 채우기
				document.getElementById('useraddr').value = data.roadAddress;

				// 상세 주소 입력란으로 포커스 이동
				document.getElementById('userdetailaddr').focus();
			}
		}).open();
	}
});
