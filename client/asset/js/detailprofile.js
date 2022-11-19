const info = document.querySelector("#bar");
fetch("http://localhost:8080/api/user/" + userId)
    .then(response => response.json())
    .then(data => {
        const html = `
            <div id="avatar"></div>
                <section>
                <h3>${data.userName}</h3>
                <div id="desc">
                    <p>${data.phone}</p>
                    <p>${data.email}</p>
                </div>
                <div id="follow">
                <p onclick="logOut()">Đăng xuất</p>
                </div>
                </section>
            </div>
        `
        info.innerHTML = html
    })
function logOut(){
    localStorage.removeItem('userId')
    fetch("http://localhost:8080/api/auth/logout")
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(err => console.error(err))
    window.location.href = "trangchu.html?" + null
}