const mobilesList = document.querySelector(".mobiles-list")
const tabletsList = document.querySelector(".tablets-list")
const accessoriesList = document.querySelector(".accessories-list")
const registerBtn = document.querySelector(".register-btn")
const loginBtn = document.querySelector(".login-btn")

const cart = document.querySelector(".cart-item")
fetch("http://localhost:8080/api/product/options?categoryName=Điện thoại&page=0&pageSize=4")
    .then(response => response.json())
    .then(data => {
        let productsList = data.products
        console.log(productsList)
        const htmls = productsList.map((product) => {
            return `<li class="item">
                        <a href="chitietsanpham.html?${product.id}">
                            <div class="box-image">
                                <img width="293px" height="293px" src="${product.imgPath}">
                            </div>
                            <div class="item-title">
                                <span class="item-description">${product.name}</span>
                                <span class="item-price">${product.price}đ</span>
                            </div>
                        </a>
                    </li>`
        })
        mobilesList.innerHTML = htmls.join("\n")
    })
    .catch(err => console.error(err))
fetch("http://localhost:8080/api/product/options?categoryName=Máy tính bảng&page=0&pageSize=4")
    .then(response => response.json())
    .then(data => {
        let productsList = data.products
        const htmls = productsList.map((product) => {
            return `<li class="item">
                        <a href="chitietsanpham.html?${product.id}">
                            <div class="box-image">
                                <img width="293px" height="293px" src="${product.imgPath}">
                            </div>
                            <div class="item-title">
                                <span class="item-description">${product.name}</span>
                                <span class="item-price">${product.price}đ</span>
                            </div>
                        </a>
                    </li>`
        })
        tabletsList.innerHTML = htmls.join("\n")
    })
    .catch(err => console.error(err))
fetch("http://localhost:8080/api/product/options?categoryName=Phụ kiện&page=0&pageSize=4")
    .then(response => response.json())
    .then(data => {
        let productsList = data.products
        const htmls = productsList.map((product) => {
            return `<li class="item" >
                        <a href="chitietsanpham.html?${product.id}">
                            <div class="box-image">
                                <img width="293px" height="293px" src="${product.imgPath}">
                            </div>
                            <div class="item-title">
                                <span class="item-description">${product.name}</span>
                                <span class="item-price">${product.price}đ</span>
                            </div>
                        </a>
                    </li>`
        })
        accessoriesList.innerHTML = htmls.join("\n")
    })
    .catch(err => console.error(err))
