document.addEventListener("DOMContentLoaded", () => {
    let posts_titles = document.getElementsByClassName("post_title");
    let pt_length = posts_titles.length;
    let posts = document.getElementsByClassName("post_card");

    for(let i = 0; i < pt_length; i++) {
        posts_titles[i].addEventListener("click", () => {
            let id = posts[i].getAttribute("id");

            window.location.replace("http://localhost:8080/post?post_id=" + id);
        });
    }
});