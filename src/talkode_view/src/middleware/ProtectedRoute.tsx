import Login from "../Login/index";

const ProtectedRoute = ({
  Component,
  ...rest
}: {
  Component: React.ComponentType;
}) => {
  let raw_cookies = decodeURIComponent(document.cookie);
  let c_arr = raw_cookies.split(";");
  let clen = c_arr.length;
  let cname  = "Authorization";
  let token = "";

  for(let i = 0; i < clen; i++) {
    let c = c_arr[i];
    
    while(c.charAt(0) == ' ') {
      c = c.substring(1);
    }

    if(c.substring(0, cname.length) == cname) {
      token = c.substring(cname.length, c.length - 13);
    }
  }

  const isAuthenticated = !!token;

  if (!isAuthenticated) {
    return <Login />;
  }

  return (
    <Component />
  );
};

export default ProtectedRoute;
