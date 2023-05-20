const product = document.querySelector(".product")
const user = document.querySelector(".user")
const order = document.querySelector(".order")
const pageBtn = document.querySelectorAll(".pagination p")
const footer = document.querySelector(".table-footer")
var category = ""
const productHeader = `
    <tr class="table-header-1">
        <th width="10%">ID</th>
        <th width="30%">Tên sản phẩm</th>
        <th width="20%">Giá</th>
        <th width="20%">Hình ảnh</th>
        <th width="10%">Hãng sản xuất</th>
        <th width="10%">Hành động</th>
    </tr>
`
const userHeader = `
    <tr class="table-header-2">
        <th width="15%">ID</th>
        <th width="30%">Tên khách hàng</th>
        <th width="20%">Email</th>
        <th width="20%">SĐT</th>
        <th width="15%">Hành động</th>
    </tr>
`
const orderHeader1 = `
    <tr class="table-header-3">
        <th width="10%">ID</th>
        <th width="20%">Tên khách hàng</th>
        <th width="20%">SĐT</th>
        <th width="30%">Địa chỉ</th>
        <th width="10%">Trạng thái</th>
        <th width="10%">Hành động</th>
    </tr>
`
const orderHeader2 = `
    <tr>
        <th width="20%">Mã sản phẩm</th>
        <th width="40%">Tên sản phẩm</th>
        <th width="20%">Số lượng</th>
        <th width="20%">Thành tiền</th>
    </tr>
`
pageBtn.forEach((page) => {
    page.onclick = () =>{
        document.querySelector(".pagination p.active").classList.remove("active");
        page.classList.add("active");
        let pageNumber = parseInt(page.innerHTML) - 1
        pageNumber = pageNumber.toString()
        fetch("http://localhost:8080/api/product/options" +"?categoryName=" + category + "&page=" + pageNumber)
            .then(response => response.json())
            .then(data => {
                let productList = data.products;
                const htmls = productList.map(product =>{
                    return `
                        <tr>
                            <td class="product-id">${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}đ</td>
                            <td><img width="50px" height="50px" src="${product.imgPath}" ></td>
                            <td>${product.manufacturer.manufacturerName}</td>
                            <td onclick="deleteProduct(${product.id})">Xóa</td>
                        </tr>
                    `
                })
                product.innerHTML = productHeader + htmls.join("\n")
            })
    }
})
function getAllProducts() {
    fetch("http://localhost:8080/api/product/all")
        .then(response => response.json())
        .then(data => {
            let productList = data.products;
            const htmls = productList.map(product =>{
                return `
                    <tr>
                        <td class="product-id">${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}đ</td>
                        <td><img width="50px" height="50px" src="${product.imgPath}" ></td>
                        <td>${product.manufacturer.manufacturerName}</td>
                        <td onclick="deleteProduct(${product.id})">Xóa</td>
                    </tr>
                `
            })
            product.innerHTML = productHeader + htmls.join("\n")
        })
}

getAllProducts();


function getAllOrders(){
    fetch("http://localhost:8080/api/order/all")
        .then(response => response.json())
        .then(data => {
            const orderList = data.orders
            const htmls = orderList.map((order) => {
                return `
                    <tr onclick="getOrderUser(${order.cart.id})">
                        <td>${order.id}</td>
                        <td>${order.userName}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td>${order.acception}</td>
                        <td><button onclick="acceptOrder(${order.id})" class="btn-accept">Xác nhận</button></td>
                    </tr>
                `
            })
            order.innerHTML = orderHeader1 + htmls.join("\n")
        })
}

getAllOrders();

function acceptOrder(id){
    fetch("http://localhost:8080/api/order/access" + "?id=" + id, {
        method: "POST",
        headers: {
            'Accept': 'application/json', 
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.text())
    .then(data => {
        console.log(data);
        getAllOrders();
    })
}

function getOrderUser(id){
    fetch("http://localhost:8080/api/cart/" + id)
        .then(response => response.json())
        .then(data => {
            let productList = data.products
            const htmls = productList.map(product =>{
                return `
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${data.count[product.id]}</td>
                        <td>${product.price * data.count[product.id]}đ</td>
                    </tr>
                `
            })
            order.innerHTML = orderHeader2 + htmls.join("\n")
            btnBack.style.display = 'block'
            btnBack.onclick = () =>{
                getAllOrders();
                btnBack.style.display = 'none'
            }
        })
}

function getAllUsers() {
    fetch("http://localhost:8080/api/user/all")
        .then(response => response.json())
        .then(data => {
            const htmls = data.map((user) => {
                return `
                    <tr class="user-id-${user.id}">
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td onclick="deleteUser(${user.id})">Xóa</td>
                    </tr>
                `
            })
            user.innerHTML = userHeader + htmls.join("\n")
        })
}

getAllUsers();

function deleteUser(id) {
    fetch("http://localhost:8080/api/user/delete/" + id,{
        method: "DELETE",
        headers: {
            'Accept': 'application/json', 
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.text())
        .then(data => {
            let user = document.querySelector(".user-id-" + id);
            if (user) {
                user.remove();
            }
        })

}

function deleteProduct(id) {
    fetch("http://localhost:8080/api/product/delete/" + id,{
        method: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.text())
    .then(data => {
        getAllProducts();
    })
}

const logOutBtn = document.querySelector(".nav-item-admin");
logOutBtn.onclick = () =>{
    localStorage.removeItem('userId')
    fetch("http://localhost:8080/api/auth/logout")
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(err => console.error(err))
    window.location.href = "trangchu.html?" + null
}

const selectInput = document.querySelector(".category-list")
selectInput.onchange = () =>{
    category = selectInput.value
    fetch("http://localhost:8080/api/product/options" + "?categoryName=" + category + "&page=0&pageSize=8")
        .then(response => response.json())
        .then(data => {
            let productList = data.products;
            const htmls = productList.map(product =>{
                return `
                    <tr>
                        <td class="product-id">${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}đ</td>
                        <td><img width="50px" height="50px" src="${product.imgPath}" ></td>
                        <td>${product.manufacturer.manufacturerName}</td>
                        <td>Xóa</td>
                    </tr>
                `
            })
            product.innerHTML = productHeader + htmls.join("\n")
        })
    
}


