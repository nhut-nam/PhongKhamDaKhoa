function deleteDoiTuong(endpoint, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) { 
        fetch(endpoint + id, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa thành công!");
                location.reload();
            } else 
                alert("Có lỗi xảy ra!\nChi tiết: " + text);
                });
    }
}

// Hàm giúp reset form tìm kiếm
function resetForm(formId) {
    document.getElementById(formId).reset();
    document.getElementById(formId).submit();
}

// Khởi tạo các dropdown select2 nếu có thư viện
document.addEventListener('DOMContentLoaded', function() {
    // Thêm event listener cho các nút sắp xếp trên header của bảng
    const tableHeaders = document.querySelectorAll('th[data-sort]');
    tableHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const sortField = this.getAttribute('data-sort');
            const currentSort = new URLSearchParams(window.location.search).get('sort') || 'asc';
            const newSort = currentSort === 'asc' ? 'desc' : 'asc';
            
            // Lấy form tìm kiếm hiện tại
            const form = document.querySelector('#searchForm');
            
            // Đặt giá trị orderBy và sort
            let orderByInput = form.querySelector('select[name="orderBy"]');
            if (!orderByInput) {
                orderByInput = document.createElement('input');
                orderByInput.type = 'hidden';
                orderByInput.name = 'orderBy';
                form.appendChild(orderByInput);
            }
            orderByInput.value = sortField;
            
            let sortInput = form.querySelector('select[name="sort"]');
            if (!sortInput) {
                sortInput = document.createElement('input');
                sortInput.type = 'hidden';
                sortInput.name = 'sort';
                form.appendChild(sortInput);
            }
            sortInput.value = newSort;
            
            // Submit form
            form.submit();
        });
    });
});

//function showAddForm() {
//        document.getElementById("searchForm").style.display = "none";
//        document.getElementById("tableSection").classList.add("d-none");
//        document.getElementById("addForm").style.display = "block";
//    }
//
//function cancelAddForm() {
//        document.getElementById("searchForm").style.display = "block";
//        document.getElementById("tableSection").classList.remove("d-none");
//        document.getElementById("addForm").style.display = "none";
//}
