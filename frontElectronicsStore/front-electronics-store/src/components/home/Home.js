import React from "react";
import PropTypes from "prop-types";
import { Button, AppBar, Toolbar, TextField } from "@material-ui/core";
import Background from "../../img/img2.jpg";
import { withStyles } from "@material-ui/core/styles";
import axios from "axios";
import axiosInstance from "../../axios";
class Home extends React.Component {
  constructor(props) {
    super();
    this.state = {
      nume: null,
      products: [],
    };
  }
  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  search = () => {
    axiosInstance
      .get("product/find", {
        params: { nume: this.state.nume },
        responseType: "text",
      })
      .then((res) => {
        this.setState({ products: res.data });
        {
          this.renderClients();
        }
      })
      .catch((err) => console.log(err));
  };

  renderClients = () => {
    console.log("inainte");
    console.log(this.state.products);
    console.log("dupa");
    const clientsList = this.state.products.map((client) => (
      <tr key={client.id}>
        <th>{client.id}</th>
        <th>{client.name}</th>
        <th>{client.price}</th>
      </tr>
    ));

    return (
      <table
        align="right"
        style={{
          width: "30%",
          // right: "150px",
          height: "55px",
          border: "1px solid black",
          // top: `${this.state.yoffset}px`,
        }}
      >
        <tr style={{ backgroundColor: "gray" }}>
          <th>Id</th>
          <th>Name</th>
          <th>Price</th>
        </tr>
        {clientsList}
      </table>
    );
  };

  render(props) {
    return (
      <React.Fragment>
        <AppBar position="fixed">
          <Toolbar>
            {
              <Button
                onClick={() => {
                  this.props.history.push("/home");
                }}
              >
                Home
              </Button>
            }
            {
              <Button
                onClick={() => {
                  this.props.history.push("/register");
                }}
              >
                Register
              </Button>
            }
            {
              <Button
                onClick={() => {
                  this.props.history.push("/login");
                }}
              >
                Login
              </Button>
            }
            <TextField
              variant="filled"
              margin="normal"
              id="nume"
              label="Search products"
              name="nume"
              autoComplete="string"
              onKeyPress={(event) => {
                if (event.key === "Enter") {
                  this.search();
                  {
                    this.renderClients();
                  }
                }
              }}
              onChange={this.updateAttributes}
              // multiline={true}
              // InputProps={{
              //   className: classes.input,
              // }}
              // autoFocus
            />
          </Toolbar>
        </AppBar>
        <Toolbar />
        <br></br>
        {this.renderClients()}

        <div
          style={{
            backgroundImage: `url(${Background})`,
            backgroundPosition: "left center",
            backgroundSize: "cover",
            backgroundRepeat: "no-repeat",
            width: "70%",
            height: "900px",
          }}
        ></div>
      </React.Fragment>
    );
  }
}

export default Home;
