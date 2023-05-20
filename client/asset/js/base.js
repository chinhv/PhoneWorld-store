const logo = document.querySelector('.logo a');
var userId = localStorage.getItem('userId');
var userName = ""
const menuItem = document.querySelector(".menu-items");
logo.setAttribute("href", "trangchu.html?" + userId);
if(userId != null || userId != undefined){
    fetch("http://localhost:8080/api/user/" + userId)
        .then(response => response.json())
        .then(data => {
            userName = data.userName
            const html = `
                <a href="detailprofile.html?${userId}">
                    <li class="account">
                        <div class="account-img">
                            <img width="33px" height="30px" src="./asset/img/c6b3d31539b5f51e68dc08bbde85fcec.png" alt="">
                        </div>
                        <span class="account-name">${data.userName}</span>
                    </li>
                </a>
                <li class="cart-item">
                    <a href="cart.html" class="cart-item-title">Giỏ hàng</a>
                </li>
            `
            menuItem.innerHTML = html
            
        })
}


