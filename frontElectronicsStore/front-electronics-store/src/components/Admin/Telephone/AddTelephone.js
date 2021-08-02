import React from "react";
import axiosInstance from "../../../axios.js";
import { Grid, Container, TextField, Button } from "@material-ui/core";
class AddTelephone extends React.Component {
  constructor() {
    super();
    this.state = {
      id: null,
      price: null,
      name: "",
      company: "",
      operatingSystem: "",
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
      company: this.state.company,
      operatingSystem: this.state.operatingSystem,
    };

    axiosInstance
      .post("/telephone", body)
      .then((r) => console.log(r), alert("Telefon adaugat"))
      .catch((e) => console.log(e));
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "ADMIN")
      return (
        // <div className="add computer">
        //   <form>
        //     <input
        //       type="text"
        //       placeholder="Name"
        //       name="name"
        //       value={this.state.name}
        //       onChange={this.updateAttributes}
        //     />
        //     <input
        //       type="text"
        //       placeholder="Operating System"
        //       name="operatingSystem"
        //       value={this.state.operatingSystem}
        //       onChange={this.updateAttributes}
        //     />
        //     <input
        //       type="number"
        //       placeholder="Price"
        //       name="price"
        //       value={this.state.price}
        //       onChange={this.updateAttributes}
        //     />
        //     <input
        //       type="text"
        //       placeholder="Specifications"
        //       name="specifications"
        //       value={this.state.specifications}
        //       onChange={this.updateAttributes}
        //     ></input>
        //     <input
        //       type="text"
        //       placeholder="Company"
        //       name="company"
        //       value={this.state.company}
        //       onChange={this.updateAttributes}
        //     ></input>
        //     <button type="submit" onClick={this.handleInsert}>
        //       Insert me
        //     </button>
        //   </form>
        // </div>
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
                  placeholder="Operating System"
                  name="operatingSystem"
                  value={this.state.operatingSystem}
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
                  placeholder="Company"
                  name="company"
                  value={this.state.company}
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
                  Add Telephone
                </Button>
              </form>
            </Grid>
          </div>
        </Container>
      );
    else return <div>You need to login</div>;
  }
}

export default AddTelephone;
