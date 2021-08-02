import React from "react";
import Background from "../../img/admin_back.jpg";
import { Button, AppBar, Toolbar } from "@material-ui/core";
import axiosInstance from "../../axios.js";
// import EditIcon from "@material-ui/icons/Edit";

class Admin extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const data = localStorage.getItem("USER");
    if (data === "ADMIN")
      return (
        <React.Fragment>
          <AppBar position="fixed">
            <Toolbar>
              <h3>AdminPage.</h3>
              {
                <Button
                  onClick={() => {
                    const body = {
                      id: localStorage.getItem("USER_ID"),
                      connected: true,
                    };

                    axiosInstance
                      .post("/logout", body)
                      .then((response) => console.log(response))
                      .catch((e) => console.log(e));
                    this.props.history.push("/login");
                  }}
                >
                  Log out
                </Button>
              }
              {
                <Button
                  onClick={() => {
                    this.props.history.push("/computers");
                  }}
                >
                  Computers
                </Button>
              }
              <Button
                onClick={() => {
                  this.props.history.push("/insertcomputer");
                }}
              >
                Add Computers
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/component");
                }}
              >
                Components
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/addcomponent");
                }}
              >
                Add Componentes
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/telephone");
                }}
              >
                Telephone
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/addtelephone");
                }}
              >
                Add Telephone
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/clients");
                }}
              >
                Clients
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/adminwarranty");
                }}
              >
                Warranty
              </Button>
              <Button
                onClick={() => {
                  this.props.history.push("/logintimestamp");
                }}
              >
                Login Timestamp
              </Button>
            </Toolbar>
          </AppBar>
          <Toolbar />

          <div
            style={{
              backgroundImage: `url(${Background})`,
              backgroundPosition: "center",
              backgroundSize: "cover",
              backgroundRepeat: "no-repeat",
              width: "100%",
              height: "1000px",
            }}
          ></div>
        </React.Fragment>
      );
    else return <div>You need to login </div>;
  }
}

export default Admin;
