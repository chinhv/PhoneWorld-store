const orderBtn = document.querySelector('.formbold-btn');

orderBtn.onclick = (e) => {
    e.preventDefault();
    let userName = document.querySelector("input[name='userName']").value
    let phone = document.querySelector("input[name='phone']").value
    let address = document.querySelector("input[name='address']").value
    let note = document.querySelector("input[name='note']").value
    let rawData = {
        userName: userName,
        phone: phone,
        address: address,
        note: note
    }
    fetch("http://localhost:8080/api/order/add" + "?userId=" + userId, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(rawData)
    })
    .then(response => response.text())
    .then(data => {
        console.log(data);
        if(data == "Order success!"){
            alert("Đặt hàng thành công")
        }else{
            alert("Lỗi thông tin")
        }
    })
}