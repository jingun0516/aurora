document.getElementById('signupForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const loginId = document.getElementById('loginId').value;
    const password = document.getElementById('password').value;

    if (!loginId || !password) {
        alert('아이디와 비밀번호를 입력하세요.');
        return;
    }

    try {
        const checkResponse = await fetch(`/api/users/check-id?loginId=${loginId}`);

        if (checkResponse.status === 409) {
            alert('이미 존재하는 아이디입니다.');
            return;
        }

        const formData = { loginId, password };
        const signupResponse = await fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (signupResponse.ok) {
            alert('회원가입이 완료되었습니다!');
            window.location.href = "/login";
        } else {
            alert('회원가입 중 오류가 발생했습니다.');
        }
    } catch (error) {
        alert('오류가 발생했습니다.');
    }
});
