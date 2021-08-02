import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import Login from "./components/login/Login";
import axiosInstance from "./axios";

axiosInstance.interceptors.request.use(
  (request) => {
    let basicToken = localStorage.getItem("token");
    if (basicToken) {
      request.headers["Authorization"] = basicToken;
    }
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
