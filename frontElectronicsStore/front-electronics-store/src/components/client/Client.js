import React from "react";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";
import Background from "../../img/placa.jpg";
import axiosInstance from "../../axios.js";
import { Button, AppBar, Toolbar } from "@material-ui/core";
class Client extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.connect();
  }

  connect() {
    const URL = "http://localhost:8080/socket";
    const websocket = new SockJS(URL);
    const stompClient = Stomp.over(websocket);

    stompClient.connect({}, (frame) => {
      console.log("Conectat la " + frame);
      stompClient.subscribe("/topic/socket/telephones", (notification) => {
        let message = notification.body;
        console.log(message);
        alert(message);
      });

      stompClient.subscribe("/topic/socket/components", (notification) => {
        let message = notification.body;
        console.log(message);
        alert(message);
      });

      stompClient.subscribe("/topic/socket/computers", (notification) => {
        let message = notification.body;
        console.log(message);
        alert(message);
      });
    });
  }

  render() {
    const data = localStorage.getItem("USER");
    if (data === "CLIENT")
      return (
        <React.Fragment>
          <AppBar position="fixed">
            <Toolbar>
              <h3>ClientPage.</h3>
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
                    this.props.history.push("/clientcomputer");
                  }}
                >
                  Computers
                </Button>
              }
              {
                <Button
                  onClick={() => {
                    this.props.history.push("/clienttelephone");
                  }}
                >
                  Telephones
                </Button>
              }
              {
                <Button
                  onClick={() => {
                    this.props.history.push("/clientcomponent");
                  }}
                >
                  Components
                </Button>
              }
              {
                <Button
                  onClick={() => {
                    this.props.history.push("/cart");
                  }}
                >
                  Cart
                </Button>
              }
              <Button
                onClick={() => {
                  this.props.history.push("/clientwarranty");
                }}
              >
                Warranty
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
    else return <div>You need to login</div>;
  }
}

export default Client;
