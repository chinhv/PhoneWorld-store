const loginBtn = document.querySelector("#login");
const registerBtn = document.querySelector("#register");

registerBtn.onclick = (e) => {
    e.preventDefault();
    let name = document.querySelector("input[name='username-register']").value;
    let password = document.querySelector("input[name='password-register']").value;
    let email = document.querySelector("input[name='email']").value;
    let phone = document.querySelector("input[name='phone']").value;
    const rawData = {
        username: name,
        password: password,
        email: email,
        phone: phone
    }
    fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(rawData)
    })
    .then(response => response.text())
    .then(data => {
        if(data = "Register successfully !"){
            formToggle.classList.remove("visible");
            panelOne.classList.remove("hidden");
            panelTwo.classList.remove("active");
            document.querySelector(".form").style.height = panelOneHeight + "px";
        }else{
            alert(data)
        }
    })
    .catch(err => console.error(err));
}


loginBtn.onclick = (e) => {
    e.preventDefault();
    let email = document.querySelector("input[name='username-login']").value;
    let password = document.querySelector("input[name='password-login']").value;
    const rawData = {
        email: email,
        password: password
    }
    fetch("http://localhost:8080/api/auth/log-in", {
        method: "POST",
        headers: {
            'Accept': 'application/json', 
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body: JSON.stringify(rawData)
    })
    .then(res => res.text())
    .then(data => {
        if(data == "Log-in successfully!"){
            fetch("http://localhost:8080/api/user/current", {credentials: 'include'})
                .then(res => res.json())
                .then(data => {
                    const userId = data.id
                    localStorage.setItem('userId', userId);
                    console.log(userId);
                    if(data.email === "admin@gmail.com"){
                        window.location.href = "admin.html";
                    }else{
                        window.location.href = "trangchu.html?" + userId;
                    }
                });
        }else{
            alert(data);
        }
    })
}



