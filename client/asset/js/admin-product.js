const addBtn = document.querySelector('.btn-add')
const backBtn = document.querySelector('.btn-back')
const form = document.querySelector('.form')


addBtn.onclick = (e) =>{
    e.preventDefault();
    const formData = new FormData(form);
    fetch("http://localhost:8080/api/product/add", {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        if(data == 'Saved !'){
            alert("Đã thêm sản phẩm vào csdl !")
        }else{
            alert("Lỗi thông tin !")
        }
    });
}

backBtn.onclick = (e) =>{
    e.preventDefault();
    window.location.href = "admin.html"
}