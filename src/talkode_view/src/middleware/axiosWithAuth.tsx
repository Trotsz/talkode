import axios from "axios";

const axiosWithAuth = axios.create();

axiosWithAuth.interceptors.request.use(
  (config) => {
    let cname = "Authorization=";
    let raw_cookies = decodeURIComponent(document.cookie);
    let cookies = raw_cookies.split(";");
    let clen = cookies.length;

    let token = "";

    for(let i = 0; i < clen; i++) {
      let c = cookies[i];

      while(c.charAt(0) == ' ') {
        c = c.substring(1);
      }

      if(c.substring(0, cname.length) == cname) {
        token = c.substring(cname.length, c.length - 13);
        break;
      }
    }

    if (token != "") {
      config.headers["Authorization"] = `${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosWithAuth;
