import React from "react";
import ReactDOM from "react-dom/client";

import "./index.css";

import ProtectedRoute from "./middleware/ProtectedRoute";

import Login from "./Login/index";
import Page404 from "./404/index";
import Home from "./Home/index";
import Register from './Register/index';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";


const router = createBrowserRouter([
  {
    path: "/register",
    element: <Register />
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "*",
    element: <Page404 />,
  },
  {
    path: "/",
    element: <Home />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
