var categoryName = window.location.href.split('?')[1];
const url = "category.html?" + categoryName
var hrefUrl = ""
const manufacturerLink = document.querySelectorAll(".manufacturer-item__link")
const list = document.querySelector(".column-list")
const selectInput = document.querySelectorAll(".select-input__link")
const pageBtn = document.querySelectorAll(".pagination p")
manufacturerLink.forEach((link) => {
    const manufacturerName = link.innerHTML
    hrefUrl = url + '?' + manufacturerName
    link.setAttribute("href", hrefUrl)
})

var category = window.location.href.split('?')[1];
category = category.split('-').join(' ')
category = decodeURI(category)
console.log(category)
var manufacturer = ""
if(window.location.href.split('?').length > 2){
    manufacturer = window.location.href.split('?')[2];
    console.log(manufacturer)
}

fetch("http://localhost:8080/api/product/options?categoryName=" + category + "&manufacturerName=" + manufacturer + "&page=0&pageSize=8")
    .then(res => res.json())
    .then(data => {
        let productsList = data.products
        const htmls = productsList.map(product =>{
            return `
            <li class="item small-item">
                <a href="chitietsanpham.html?${product.id}">
                    <div class="box-image">
                        <img width="250px" height="250px" src="${product.imgPath}">
                    </div>
                    <div class="item-title">
                        <span class="item-description">${product.name}</span>
                        <span class="item-price">${product.price}đ</span>
                    </div>
                </a>
            </li>
            `
        })
        list.innerHTML = htmls.join('\n')
    })
    .catch(err => console.error(err))

selectInput.forEach((select) => {
    select.onclick = () =>{
        list.innerHTML = ""
        let sort = select.innerHTML
        fetch("http://localhost:8080/api/product/search?categoryName=" + category + "&manufacturerName=" + manufacturer + "&sort=" + sort + "&page=0&pageSize=8")
            .then(res => res.json())
            .then(data => {
                let productsList = data.products
                const htmls = productsList.map(product =>{
                    return `
                    <li class="item small-item">
                        <a href="chitietsanpham.html?${product.id}">
                            <div class="box-image">
                                <img width="250px" height="250px" src="${product.imgPath}">
                            </div>
                            <div class="item-title">
                                <span class="item-description">${product.name}</span>
                                <span class="item-price">${product.price}đ</span>
                            </div>
                        </a>
                    </li>
                    `
                })
                list.innerHTML = htmls.join('\n')
            })
            .catch(err => console.error(err))
    }
})

pageBtn.forEach((page) => {
    page.onclick = () =>{
        document.querySelector(".pagination p.active").classList.remove("active");
        page.classList.add("active");
        let pageNumber = parseInt(page.innerHTML) - 1
        pageNumber = pageNumber.toString()
        console.log(pageNumber)
        fetch("http://localhost:8080/api/product/options?categoryName=" + category + "&manufacturerName=" + manufacturer + "&page=" + pageNumber +"&pageSize=8")
            .then(res => res.json())
            .then(data => {
                let productsList = data.products
                const htmls = productsList.map(product =>{
                    return `
                    <li class="item small-item">
                        <a href="chitietsanpham.html?${product.id}">
                            <div class="box-image">
                                <img width="250px" height="250px" src="${product.imgPath}">
                            </div>
                            <div class="item-title">
                                <span class="item-description">${product.name}</span>
                                <span class="item-price">${product.price}đ</span>
                            </div>
                        </a>
                    </li>
                    `
                })
                list.innerHTML = htmls.join('\n')
            })
            .catch(err => console.error(err))
    }
})

const searchBtn = document.querySelector(".search-btn")
var productList = {}
searchBtn.onclick = () =>{
    let keywords = document.querySelector("input[name='search']").value;
    console.log(keywords)
    if(keywords.length > 0){
        fetch("http://localhost:8080/api/product/search?" + "&keywords=" + keywords + "&page=0&pageSize=8")
            .then(response => response.json())
            .then(data => {
                productList = data.products
                const htmls = productList.map((product) => {
                    return `
                        <li class="item small-item">
                            <a href="chitietsanpham.html?${product.id}">
                                <div class="box-image">
                                    <img width="250px" height="250px" src="${product.imgPath}">
                                </div>
                                <div class="item-title">
                                    <span class="item-description">${product.name}</span>
                                    <span class="item-price">${product.price}đ</span>
                                </div>
                            </a>
                        </li>
                    `
                })
                list.innerHTML = htmls.join('\n')
                })
            .catch(err => console.log(err))
    }
}