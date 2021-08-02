import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import { Grid } from "@material-ui/core";
import axiosInstance from "../../axios.js";
class Register extends React.Component {
  constructor() {
    super();
    this.state = {
      id: null,
      username: "",
      password: "",
      email: "",
      firstName: "",
      lastName: "",
      address: "",
      age: "",
      telephone: "",
    };
  }

  //   aici practic imi iau continutul din camp
  handleInput = (event) => {
    const { value, name } = event.target;
    this.setState({
      [name]: value,
    });
    // console.log(value)
  };
  onSubmitFun = (event) => {
    event.preventDefault();
    let credentials = {
      username: this.state.username,
      password: this.state.password,
      email: this.state.email,
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      address: this.state.address,
      age: this.state.age,
      telephone: this.state.telephone,
    };
    console.log(credentials);
    axiosInstance
      .post("/client", credentials)
      .then((res) => {
        this.props.history.push("/login");
      })
      .catch((error) => {
        console.log(error);
      });
  };
  handleChange = (e) => {
    if (e.currentTarget.value.includes(" ")) {
      e.currentTarget.value = e.currentTarget.value.replace(/\s/g, "");
      alert("Nu aveti voie cu spatii in username");
    }
    const { value, name } = e.target;
    this.setState({
      [name]: value,
    });
  };

  render() {
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
                onChange={this.handleChange}
                autoFocus
                inputProps={{ minLength: 3 }}
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
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                type="email"
                id="email"
                label="Email"
                name="email"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="firstName"
                label="First Name"
                name="firstName"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="age"
                label="Age"
                name="age"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="address"
                label="Address"
                name="address"
                autoComplete="string"
                onChange={this.handleInput}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="telephone"
                label="Telephone"
                name="telephone"
                autoComplete="string"
                inputProps={{ maxLength: 10 }}
                onChange={this.handleInput}
                autoFocus
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
              >
                Register
              </Button>
            </form>
          </Grid>
        </div>
      </Container>
    );
  }
}

export default Register;
