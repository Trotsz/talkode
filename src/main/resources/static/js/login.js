async function login() {
    let username = document.getElementsByName("username")[0].value;
    let password = document.getElementsByName("password")[0].value;

    const data = await fetch(window.location.href.replace("/login", "/auth/login"), {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify(
            {
                username: username,
                password: password
            }
        )
    });

    const response = await data.json();
    const token = response.token;

    let cookie_name = "Authorization";
    let date = new Date();
    let expiration_date = date.setTime(date.getTime() + (1000 * 60 * 60 * 24)); // 1 day only

    document.cookie = cookie_name + "=Bearer " + token + expiration_date + "; path=/";
    window.location.href = window.location.href.replace("/login", "/");
}