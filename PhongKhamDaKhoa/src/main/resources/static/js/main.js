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
