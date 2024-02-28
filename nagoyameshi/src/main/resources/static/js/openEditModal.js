function openEditModal(Id, categoryName) {
    $('#Id').val(Id);
    $('#editedCategoryName').val(categoryName);
    $('#editCategoryModal').modal('show');
}

function editCategory() {
    var categoryId = $('#Id').val();
    var editedCategoryName = $('#editedCategoryName').val();

    $.ajax({
        type: 'PUT',
        url: '/api/categories/' + categoryId,  // サーバー側のエンドポイントを指定
        contentType: 'application/json',
        data: JSON.stringify({ name: editedCategoryName }),
        success: function (response) {
            // 成功時の処理（例: モーダルを閉じる、テーブルのデータを更新など）
            $('#editCategoryModal').modal('hide');
            // 以下にテーブルの更新などの処理を追加
        },
        error: function (error) {
            // エラー時の処理
            console.error('Failed to edit category:', error);
        }
    });
}
