import { useState } from "react";
import axios from 'axios';
import Background from "../components/Background";

import "../assets/font.css";

function Login() {
  const [ isLogged, setIsLogged ] = useState(false);
  const [ error, setError ] = useState("");
  const [ formData, setFormData ] = useState({
    username: "",
    password: "",
  });

  const url = "http://localhost:8080/auth/login";

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    
    try {
      const data = await axios.post(url, formData, {
          headers: {
              "Content-Type": "application/json",
          }
      });

      let response = await data.data;
      let token = response.token;
      let date = new Date();
      let expiration_date = date.setTime(date.getTime() + (1000 * 60 * 60 * 24)); // 1 day

      document.cookie = "Authorization=Bearer " + token + expiration_date + "; path=/";

      setIsLogged(true);
  
      window.location.href = "http://localhost:5173/";
    } catch(err) {
        console.log("Error: \n" + err);
    }
  };

  return (
    <div className="full-div overflow-hidden flex items-center justify-center lg:justify-start">
      <Background />
      <div className="bg-light lg:h-100 h-5/6 xl:w-2/5 lg:w-1/2 sm:w-5/6 w-90vw lg:m-0 flex-account rounded-2xl lg:rounded-none z-10">
        <p>{error ? error : null}</p>
        <form
          onSubmit={handleSubmit}
          className="flex-account md:p-20 p-10 text-center overflow-hidden"
        >
          <p className="mb-5 uppercase text-purple-1 font-oswald header-style">
            LogIn
          </p>
          <input
            name="username"
            type="text"
            placeholder="Username"
            required
            className="bg-gray-50ring-0 outline-none border-b-2 border-transparent text-neutral-600 placeholder-violet-700 rounded-2xl focus:ring-violet-500 focus:border-violet-500 hover:border-violet-300 block p-2.5 checked:bg-emerald-500 my-3 pl-5"
            value={formData.username}
            onChange={(e) =>
              setFormData({ ...formData, username: e.target.value })
            }
          />
          <input
            type="password"
            placeholder="Password"
            required
            className="bg-gray-50ring-0 outline-none border-b-2 border-transparent text-neutral-600 placeholder-violet-700 rounded-2xl focus:ring-violet-500 focus:border-violet-500 hover:border-violet-300 block p-2.5 checked:bg-emerald-500 my-3 pl-5"
            value={formData.password}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
          />
          <div className="flex flex-row">
            <button
              type="submit"
              className="flex-1 rounded-l-2xl h-10 p-2 text-center my-2 lg:my-5 button-primary"
            >
              <span className="button-content">Log In</span>
            </button>
          </div>

          <p className="my-5 text-purple-1 font-oswald small-style ">
            Don't have an account?&nbsp;
            <a
              className="button-secondary font-oswald small-style"
              href="/register"
            >
              REGISTER
            </a>
          </p>
        </form>
      </div>
      <div className="mx-auto hidden lg:block">
        <p></p>
      </div>
    </div>
  );
}

export default Login;
