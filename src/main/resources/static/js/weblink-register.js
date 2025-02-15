document.getElementById('weblinkRegisterForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const weblinkName = document.getElementById('weblink-name').value;
    const weblinkUrl = document.getElementById('weblink-url').value;
    const weblinkCategory = document.getElementById('weblink-category').value;

    if(!weblinkName || !weblinkUrl || !weblinkCategory) {
        alert('입력을 확인해주세요');
        return;
    }

    try {
        const formData = {
            name: weblinkName,
            url: weblinkUrl,
            category: weblinkCategory
        };

        console.log("FormData:", formData);

        const registerResponse = await fetch('/api/weblink', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if(registerResponse.ok) {
            alert('웹 링크 등록이 완료되었습니다.');
        } else {
            alert('웹 링크 등록에 실패하였습니다.');
        }
    } catch (error) {
        console.log(error);
        alert('오류가 발생했습니다.');
    }
});