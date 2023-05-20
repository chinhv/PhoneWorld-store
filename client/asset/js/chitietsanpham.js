var productId = window.location.href.split('?')[1];
const productDetail = document.querySelector(".product-detail");
const submitBtn = document.querySelector(".submit-comment");
const commentList = document.querySelector(".comment-list");
console.log(productId);
fetch("http://localhost:8080/api/product/" + productId)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let htmls
        let product = data
        if(product.category.categoryName == "Phụ kiện"){
            htmls = 
                 `
                    <div class="product-img">
                        <img src="${product.imgPath}" alt="">
                    </div>
                    <div class="product-info">
                        <div class="nav-route">
                            <a href="">Trang chủ</a>
                            <span class="divider">/</span>
                            <a href="">${product.category.categoryName}</a>
                            <span class="divider">/</span>
                            <a href="">${product.manufacturer.manufacturerName}</a>
                        </div>
                        <div class="product-title">${product.name}</div>
                        <div class="big-divider"></div>
                        <div class="product-price">${product.price}đ</div>
                        <ul class="product-description">
                            <li>${product.description}</li>
                            <li>Hãng sản xuất: ${product.manufacturer.manufacturerName}</li>
                            <li>${product.battery}</li>
                            <li>${product.cpu}</li>
                        </ul>
                        <div class="add-to-cart">
                            <div class="quantity">
                                <span>Số lượng:</span>
                                <input type="number" min="1" max="100" value="1" step="1" inputmode="numeric" size="4" class="product-quantity">
                            </div>
                            <button onclick="addProduct(${product.id})" class="add-product">Thêm vào giỏ</button>
                        </div>
                    </div>
                `
        }else{
            htmls = 
                 `
                    <div class="product-img">
                        <img src="${product.imgPath}" alt="">
                    </div>
                    <div class="product-info">
                        <div class="nav-route">
                            <a href="">Trang chủ</a>
                            <span class="divider">/</span>
                            <a href="">${product.category.categoryName}</a>
                            <span class="divider">/</span>
                            <a href="">${product.manufacturer.manufacturerName}</a>
                        </div>
                        <div class="product-title">${product.name}</div>
                        <div class="big-divider"></div>
                        <div class="product-price">${product.price}đ</div>
                        <ul class="product-description">
                            <li>${product.description}</li>
                            <li>Hãng sản xuất: ${product.manufacturer.manufacturerName}</li>
                            <li>Dung lương pin: ${product.battery}mAh</li>
                            <li>CPU: ${product.cpu}</li>
                            <li>RAM: ${product.ram}GB</li>
                        </ul>
                        <div class="add-to-cart">
                            <div class="quantity">
                                <span>Số lượng:</span>
                                <input type="number" min="1" max="100" value="1" step="1" inputmode="numeric" size="4" class="product-quantity">
                            </div>
                            <button onclick="addProduct(${product.id})" class="add-product">Thêm vào giỏ</button>
                        </div>
                    </div>
                `
        }
        
        productDetail.innerHTML = htmls;
    })
    .catch(err => console.error(err));

submitBtn.onclick = () =>{
    let comment = document.querySelector("input[name='comment']").value;
    fetch("http://localhost:8080/api/comment/add?" + "&comment=" + comment, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    })
    .then(response => response.text())
    .then(data => {
        if(window.localStorage.length < 1){
            window.location.href = "login&signup.html"
        }else{
            fetch("http://localhost:8080/api/comment/all")
                .then(response => response.json())
                .then(data => {
                    const htmls = data.map((item) => {
                        return `
                            <li class="comment-item">
                                <div class="avatar">
                                    <img src="./asset/img/c6b3d31539b5f51e68dc08bbde85fcec.png">
                                </div>
                                <div class="comment-content">
                                    <span>${userName}</span>
                                    <span>${item.comment}</span>
                                </div>
                            </li>
                        `
                    })
                    commentList.innerHTML = htmls.join("\n")
                })
        }
    })
        
}

function addProduct(id){
    if(window.localStorage.length < 1){
        window.location.href = "login&signup.html"
    }else{
        let count = document.querySelector(".product-quantity").value;
        fetch("http://localhost:8080/api/cart/addToCart" + "?id=" + id + "&count=" + count + "&userId=" + userId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: id, count: count})
        })
        .then(response => response.text())
        .then(data => {
            alert("Đã lưu vào giỏ hàng !")
        })
    }
}



