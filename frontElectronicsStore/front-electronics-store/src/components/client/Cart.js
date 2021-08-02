import React from "react";
import axiosInstance from "../../axios.js";
class Cart extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      computers: [],
      id: null,
      idClient: null,
      pret: null,
    };
    this.fetchAllComputers.bind(this);
  }

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  componentDidMount() {
    this.fetchAllComputers();
  }

  fetchAllComputers = () => {
    const data = localStorage.getItem("USER_ID");
    this.setState({ idClient: localStorage.getItem("USER_ID") });
    axiosInstance
      .get(`/cart/${localStorage.getItem("USER_ID")}`)
      .then((response) => {
        this.setState({
          computers: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  renderComputers = () => {
    const computerList = this.state.computers.map((computer) => (
      <tr key={computer.id}>
        <th>{computer.id}</th>
        <th>{computer.name}</th>
        <th>{computer.specifications}</th>
        <th>{computer.price}</th>
        <th>
          <button onClick={() => this.setState({ id: computer.id })}>
            Select to delete
          </button>
        </th>
      </tr>
    ));

    return (
      <table style={{ width: "70%" }}>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Specifications</th>
          <th>Price</th>
        </tr>
        {computerList}
      </table>
    );
  };

  handleSave = (e) => {
    e.preventDefault();
    const body = {
      idClient: this.state.idClient, //this.state.clientid,
      idProduct: this.state.id,
    };
    window.location.reload();
    axiosInstance
      .put("/cart/deleteproduct", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  handleSubmitOrder = (e) => {
    e.preventDefault();
    console.log(this.state.idClient);
    window.location.reload();
    axiosInstance
      .put(`/cart/submitorder/${this.state.idClient}`)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "CLIENT") {
      return (
        <div className="Computerscontainer">
          {this.renderComputers()}
          Product id: {this.state.id}
          <button type="submit" onClick={this.handleSave}>
            Delete
          </button>
          <div>
            {" "}
            <button type="submit" onClick={this.handleSubmitOrder}>
              Submit the order
            </button>
          </div>
        </div>
      );
    }
    if (data !== "CLIENT") {
      return <div>You need to login</div>;
    }
  }
}

export default Cart;
