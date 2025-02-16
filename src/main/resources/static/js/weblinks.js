function toggleEditMode(webLinkId, cancel = false) {
    const nameText = document.getElementById(`text-name-${webLinkId}`);
    const nameInput = document.getElementById(`input-name-${webLinkId}`);
    const urlText = document.getElementById(`text-url-${webLinkId}`);
    const urlInput = document.getElementById(`input-url-${webLinkId}`);
    const categoryText = document.getElementById(`text-category-${webLinkId}`);
    const categorySelect = document.getElementById(`select-category-${webLinkId}`);

    const editBtn = document.getElementById(`edit-btn-${webLinkId}`);
    const saveBtn = document.getElementById(`save-btn-${webLinkId}`);
    const cancelBtn = document.getElementById(`cancel-btn-${webLinkId}`);

    if (cancel) {
        nameInput.value = nameText.innerText;
        urlInput.value = urlText.innerText;
        categorySelect.value = categoryText.innerText;
    }

    // 보이기/숨기기 전환
    const isEditing = nameText.style.display !== "none";

    nameText.style.display = isEditing ? "none" : "inline";
    nameInput.style.display = isEditing ? "inline" : "none";

    urlText.style.display = isEditing ? "none" : "inline";
    urlInput.style.display = isEditing ? "inline" : "none";

    categoryText.style.display = isEditing ? "none" : "inline";
    categorySelect.style.display = isEditing ? "inline" : "none";

    editBtn.style.display = isEditing ? "none" : "inline-block";
    saveBtn.style.display = isEditing ? "inline-block" : "none";
    cancelBtn.style.display = isEditing ? "inline-block" : "none";
}

async function updateWebLink(webLinkId) {
    const name = document.getElementById(`input-name-${webLinkId}`).value;
    const url = document.getElementById(`input-url-${webLinkId}`).value;
    const category = document.getElementById(`select-category-${webLinkId}`).value;

    if (!name || !url || !category) {
        alert("모든 값을 입력해야 합니다.");
        return;
    }


    try {
        const response = await fetch(`/api/weblink/${webLinkId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ name, url, category })
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            alert(`수정 실패: ${errorMessage}`);
            return;
        }

        alert("수정되었습니다.");
        location.reload();
    } catch (error) {
        console.error("수정 중 오류 발생:", error);
        alert("오류가 발생했습니다.");
    }
}



function deleteWebLink(webLinkId) {
    if (confirm("정말 삭제하시겠습니까?")) {
        fetch(`/api/weblink/${webLinkId}`, { // ✅ API 호출
            method: "DELETE"
        }).then(response => {
            if (response.ok) {
                alert("삭제되었습니다.");
                location.reload(); // ✅ 삭제 후 페이지 새로고침
            } else {
                alert("삭제에 실패했습니다.");
            }
        }).catch(error => {
            console.error("삭제 중 오류 발생:", error);
            alert("오류가 발생했습니다.");
        });
    }
}
