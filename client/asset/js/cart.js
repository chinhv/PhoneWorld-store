const cartItem = document.querySelector("table")
const tableHeader = `
    <tr>
        <th>Mã sản phẩm</th>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Thành tiền</th>
        <th>Xóa</th>
    </tr>
`
if(localStorage.length < 1){
    window.location.href = "login&signup.html"
}else{
    fetch("http://localhost:8080/api/cart/")
        .then(response => response.json())
        .then(data => {
           let productList = data.products
           const htmls = productList.map(product =>{
                return `
                    <tr class="product-id-${product.id}">
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${data.count[product.id]}</td>
                        <td>${product.price * data.count[product.id]}đ</td>
                        <td onclick="deleteItem(${product.id})" class="deleteBtn">Xóa</td>
                    </tr>
                `
           })
           const html = `
                <tr class="table-footer">
                    <td class="total-price-title" colspan="3">Tổng tiền</td>
                    <td class="total-price-content">${data.totalPrice}đ</td>
                    <td class="payment" onclick="cashItem()">Thanh toán</td>
                </tr>
           `
           cartItem.innerHTML = tableHeader + htmls.join("\n") + html;
        })
    
    function deleteItem(id){
        fetch("http://localhost:8080/api/cart/deleteItem" + "?id=" + id,{
            method: "DELETE",
            headers: {
                'Accept': 'application/json', 
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.text())
        .then(data => {
            let product = document.querySelector(".product-id-" + id);
            if(product){
                product.remove();
            }
        })
    }

    function cashItem(){
        alert("Thanh toán thành công !")
    }
}