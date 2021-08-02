import React from "react";
import axiosInstance from "../../../axios.js";
import { Grid, Container, TextField, Button } from "@material-ui/core";
class AddComponent extends React.Component {
  constructor() {
    super();
    this.state = {
      id: null,
      price: null,
      name: "",
      specifications: "",
    };
  }

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleInsert = (e) => {
    e.preventDefault();
    let body = {
      price: this.state.price,
      name: this.state.name,
      specifications: this.state.specifications,
    };

    axiosInstance
      .post("/component", body)
      .then((r) => alert("Componenta adaugata"))
      .catch((e) => console.log(e));
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "ADMIN")
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
                  type="text"
                  placeholder="Name"
                  name="name"
                  value={this.state.name}
                  onChange={this.updateAttributes}
                  autoFocus
                />
                <TextField
                  variant="outlined"
                  margin="normal"
                  required
                  fullWidth
                  type="number"
                  placeholder="Price"
                  name="price"
                  value={this.state.price}
                  onChange={this.updateAttributes}
                  autoComplete="current-password"
                />
                <TextField
                  variant="outlined"
                  margin="normal"
                  required
                  fullWidth
                  type="text"
                  placeholder="Specifications"
                  name="specifications"
                  value={this.state.specifications}
                  onChange={this.updateAttributes}
                  autoFocus
                />

                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  color="primary"
                  onClick={this.handleInsert}
                >
                  Add Component
                </Button>
              </form>
            </Grid>
          </div>
        </Container>
      );
    else return <div>You need to login</div>;
  }
}

export default AddComponent;
