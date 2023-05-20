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
    fetch("http://localhost:8080/api/cart/", {credentials: 'include'})
        .then(response => response.json())
        .then(data => {
           console.log(data.message);
           if(data.message == "No product yet !"){
                alert(data.message)
           }else{
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
                        <td class="payment" onclick="cashItem()">Mua hàng</td>
                    </tr>
               `
               cartItem.innerHTML = tableHeader + htmls.join("\n") + html;
           }
        })
    
    function deleteItem(id){
        fetch("http://localhost:8080/api/cart/deleteItem" + "?id=" + id +"&userId=" + userId,{
            method: "DELETE",
            headers: {
                'Accept': 'application/json', 
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.text())
        .then(data => {
            fetch("http://localhost:8080/api/cart/", {credentials: 'include'})
            .then(response => response.json())
            .then(data => {
                console.log(data.message);
                if(data.message == "No product yet !"){
                    alert(data.message)
                }else{
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
                                <td class="payment" onclick="cashItem()">Mua hàng</td>
                            </tr>
                    `
                    cartItem.innerHTML = tableHeader + htmls.join("\n") + html;
                }
            })
        })
    }

    function cashItem(){
        window.location.href = "order.html"
    }
}