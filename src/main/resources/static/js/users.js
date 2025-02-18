async function permitWriteWebLink(userId, webLinkId) {
    try {
        const response = await fetch(`/api/weblink/write?userId=${userId}&webLinkId=${webLinkId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });

        if (response.ok) {
            alert('쓰기 권한이 부여되었습니다.');
        } else if (response.status === 409) {
            alert('이미 권한이 부여되어 있습니다.');
        } else if (response.status === 404) {
            alert('사용자 또는 웹링크를 찾을 수 없습니다.');
        } else {
            throw new Error(`HTTP 오류: ${response.status}`);
        }
    } catch (error) {
        console.error(error);
        alert('권한 부여 중 오류가 발생했습니다.');
    }
}

async function permitReadWebLink(userId, webLinkId) {
    try {
        const response = await fetch(`/api/weblink/read?userId=${userId}&webLinkId=${webLinkId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });

        if (response.ok) {
            alert('읽기 권한이 부여되었습니다.');
        } else if (response.status === 409) {
            alert('이미 권한이 부여되어 있습니다.');
        } else if (response.status === 404) {
            alert('사용자 또는 웹링크를 찾을 수 없습니다.');
        } else {
            throw new Error(`HTTP 오류: ${response.status}`);
        }
    } catch (error) {
        console.error(error);
        alert('권한 부여 중 오류가 발생했습니다.');
    }
}




async function revokeWriteWebLink(userId, webLinkId) {
    try {
        const response = await fetch(`/api/weblink/write?userId=${userId}&webLinkId=${webLinkId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });

        if (response.status === 204) {
            alert('쓰기 권한이 해제되었습니다.');
        } else if (response.status === 404) {
            alert('삭제할 권한이 없습니다.');
        } else {
            throw new Error(`쓰기 권한 해제 실패 (HTTP ${response.status})`);
        }
    } catch (error) {
        console.error(error);
        alert('오류가 발생했습니다. 나중에 다시 시도하세요.');
    }
}


async function revokeReadWebLink(userId, webLinkId) {
    try {
        const response = await fetch(`/api/weblink/read?userId=${userId}&webLinkId=${webLinkId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });

        if (response.status === 204) {
            alert('읽기 권한이 해제되었습니다.');
        } else if (response.status === 404) {
            alert('삭제할 권한이 없습니다.');
        } else {
            throw new Error(`읽기 권한 해제 실패 (HTTP ${response.status})`);
        }

    } catch (error) {
        console.error(error);
        alert('권한 해제 중 오류가 발생했습니다.');
    }
}
