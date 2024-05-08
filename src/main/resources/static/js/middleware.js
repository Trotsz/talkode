let cname = "Authorization=";
let raw_cookies = decodeURIComponent(document.cookie);
let cookies_arr = raw_cookies.split(";");
let clen = cookies_arr.length;

let authorization_exists = false;

let token = "";
let url = window.location.href;

for(let i = 0; i < clen; i++) {
    let c = cookies_arr[i];

    while(c.charAt(0) == ' ') {
        c = c.substring(1);
    }

    if(c.indexOf(cname) == 0) {
        token = c.substring(cname.length, c.length - 13); // minus 13 random digits that were being generated and added to the end of the cookie for some reason
        authorization_exists = true;
        break;
    }
}

if(!authorization_exists) {
    window.location.href = "http://localhost:8080/login";
}
var xhr = new XMLHttpRequest();

xhr.open("get", url);
xhr.setRequestHeader("Authorization", token);
xhr.send(null);