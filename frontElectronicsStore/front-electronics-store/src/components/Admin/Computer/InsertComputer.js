import React from "react";
import axiosInstance from "../../../axios.js";
import { Grid, Container, TextField, Button } from "@material-ui/core";
class InsertComputer extends React.Component {
  constructor() {
    super();
    this.state = {
      id: null,
      price: null,
      color: "",
      name: "",
      specifications: "",
      year: "",
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
      color: this.state.color,
      price: this.state.price,
      name: this.state.name,
      specifications: this.state.specifications,
      year: this.state.year,
    };

    axiosInstance
      .post("/computer", body)
      .then((r) => console.log(r), alert("Calculator Adaugat"))
      .catch((e) => console.log(e));
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "ADMIN") {
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
                  type="text"
                  placeholder="Color"
                  name="color"
                  value={this.state.color}
                  onChange={this.updateAttributes}
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

                <TextField
                  variant="outlined"
                  margin="normal"
                  required
                  fullWidth
                  type="text"
                  placeholder="Year"
                  name="year"
                  value={this.state.year}
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
                  Add Computer
                </Button>
              </form>
            </Grid>
          </div>
        </Container>
      );
    } else {
      return <div>You need to login</div>;
    }
  }
}

export default InsertComputer;
