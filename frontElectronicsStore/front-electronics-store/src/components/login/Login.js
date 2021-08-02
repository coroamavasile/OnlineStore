import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import { Grid } from "@material-ui/core";
import axiosInstance from "../../axios.js";
import TwoStepAutentification from "./TwoStepAutentification.js";
class Login extends React.Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      showAutentificationCode: false,

      loginSucces: {
        role: "",
        id: 0,
        autentificationCodeAdmin: 0,
        blocked: null,
      },
    };
  }

  //   aici practic imi iau continutul din camp
  handleInput = (event) => {
    const { value, name } = event.target;
    this.setState({
      [name]: value,
    });
  };
  onSubmitFun = (event) => {
    event.preventDefault();
    let credentials = {
      username: this.state.username,
      password: this.state.password,
    };

    axiosInstance
      .post("/login", credentials)
      .then((res) => {
        const val = res.data;
        console.log(val);
        this.setState({
          loginSucces: val,
        });
        console.log("Succes");
        console.log(this.state.loginSucces);

        localStorage.setItem("USER", res.data.role);
        localStorage.setItem("USER_ID", res.data.id);
        localStorage.setItem(
          "token",
          "Basic " + btoa(this.state.username + ":" + this.state.password)
        );
        //tratam cazul in care user-ul e blocat
        if (this.state.loginSucces.blocked === true) {
          console.log("parola gresita");
          localStorage.removeItem("USER", res.data.role);
          localStorage.removeItem("USER_ID", res.data.id);
          localStorage.setItem(
            "token",
            "Basic " + btoa(this.state.username + ":" + this.state.password)
          );
        }

        if (
          this.state.loginSucces.role === "CLIENT" &&
          this.state.loginSucces.blocked === false
        ) {
          let body2 = {
            id: res.data.id,
            connected: true,
          };

          axiosInstance
            .put("/client/connection", body2)
            .then((response) => {
              console.log(response);
            })
            .catch((err) => console.log(err));
          this.props.history.push("/client");
        }

        if (this.state.loginSucces.role === "ADMIN") {
          this.setState({
            showAutentificationCode: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    if (localStorage.getItem("USER_ID")) {
      let body2 = {
        id: localStorage.getItem("USER_ID"),
        connected: false,
      };

      axiosInstance
        .put("/client/connection", body2)
        .then((response) => {
          console.log(response);
        })
        .catch((err) => console.log(err));
    }

    localStorage.removeItem("Active user");
    localStorage.removeItem("USER");
    localStorage.removeItem("USER_ID");
    return (
      <Container maxWidth="sm">
        <div>
          <Grid>
            <form onSubmit={this.onSubmitFun}>
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                onChange={this.handleInput}
                autoComplete="current-password"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
              >
                Sign In
              </Button>
            </form>
          </Grid>
        </div>
        <div>
          <button
            onClick={() => {
              this.props.history.push("/register");
            }}
          >
            Register
          </button>
          <button
            onClick={() => {
              this.props.history.push("/forgotpassword");
            }}
          >
            Forgot Password?
          </button>
          {this.state.showAutentificationCode ? (
            <TwoStepAutentification
              data={this.state.loginSucces.autentificationCodeAdmin}
              history={this.props.history}
              id={this.state.loginSucces.id}
            />
          ) : null}

          {this.state.loginSucces.blocked ? (
            <div>You username is blocked!</div>
          ) : null}
        </div>
      </Container>
    );
  }
}

export default Login;
