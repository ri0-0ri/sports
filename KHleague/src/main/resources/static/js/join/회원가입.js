const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');
const passwordError = document.getElementById('passwordError');
const confirmPasswordError = document.getElementById('confirmPasswordError');

function checkPassword() {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    let valid = true;

    // 비밀번호 유효성 검사
    if (password.length < 8) {
        passwordError.innerHTML = '비밀번호는 최소 8자 이상이어야 합니다!';
        passwordError.style.display = 'block';
        valid = false;
    } else {
        passwordError.innerHTML = '';
        passwordError.style.display = 'none';
    }

    // 비밀번호 일치 여부 검사
    if (password !== confirmPassword) {
        confirmPasswordError.innerHTML = '비밀번호가 일치하지 않습니다!';
        confirmPasswordError.style.display = 'block';
        valid = false;
    } else {
        confirmPasswordError.innerHTML = '';
        confirmPasswordError.style.display = 'none';
    }

    return valid; // 유효성 검사 결과 반환
}

// 입력 필드에 이벤트 리스너 추가
passwordInput.addEventListener('input', checkPassword);
confirmPasswordInput.addEventListener('input', checkPassword);
