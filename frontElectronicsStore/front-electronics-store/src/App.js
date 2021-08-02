import "./App.css";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";

import Home from "./components/home/Home.js";
import Login from "./components/login/Login.js";
import Admin from "./components/Admin/Admin.js";
import Computer from "./components/Admin/Computer/Computer.js";
import InsertComputer from "./components/Admin/Computer/InsertComputer.js";
import AddTelephone from "./components/Admin/Telephone/AddTelephone.js";
import Telephone from "./components/Admin/Telephone/Telephone.js";
import AddComponent from "./components/Admin/Component/AddComponent.js";
import Component from "./components/Admin/Component/Component";
import Register from "./components/login/Register.js";
import Client from "./components/client/Client.js";
import Cart from "./components/client/Cart.js";
import ClientComputer from "./components/client/ClientComputer.js";
import ClientTelephone from "./components/client/ClientTelephone.js";
import ClientComponent from "./components/client/ClientComponent";
import TwoStepAutentification from "./components/login/TwoStepAutentification";
import ForgotPassword from "./components/login/ForgotPassword.js";
import Clients from "./components/Admin/Clients.js";
import ClientWarranty from "./components/client/ClientWarranty.js";
import AdminWarranty from "./components/Admin/AdminWarranty.js";
import LoginTimestamp from "./components/Admin/LoginTimestamp.js";
const defaultRoute =
  window.location.pathname === "/" ? <Redirect to="/home" /> : undefined;
function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/login" component={Login} />
        <Route exact path="/admin" component={Admin} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/computers" component={Computer} />
        <Route exact path="/insertcomputer" component={InsertComputer} />
        <Route exact path="/addtelephone" component={AddTelephone} />
        <Route exact path="/telephone" component={Telephone} />
        <Route exact path="/addcomponent" component={AddComponent} />
        <Route exact path="/component" component={Component} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/client" component={Client} />
        <Route exact path="/clientcomputer" component={ClientComputer} />
        <Route exact path="/clienttelephone" component={ClientTelephone} />
        <Route exact path="/forgotpassword" component={ForgotPassword} />
        <Route exact path="/clientcomponent" component={ClientComponent} />
        <Route exact path="/clients" component={Clients} />
        <Route
          exact
          path="/twostepautentification"
          component={TwoStepAutentification}
        />
        <Route exact path="/cart" component={Cart} />
        <Route exact path="/clientwarranty" component={ClientWarranty} />
        <Route exact path="/adminwarranty" component={AdminWarranty} />
        <Route exact path="/logintimestamp" component={LoginTimestamp} />
      </Switch>
      {defaultRoute}
    </Router>
  );
}

export default App;
