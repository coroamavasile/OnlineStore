import React from "react";
import axiosInstance from "../../axios.js";
class Clients extends React.Component {
  constructor() {
    super();
    this.state = {
      clients: [],
      id: null,
      blocked: null,
    };
    this.fetchAllClients.bind(this);
  }

  componentDidMount() {
    this.fetchAllClients();
  }

  fetchAllClients = () => {
    axiosInstance
      .get("/client")
      .then((response) => {
        this.setState({
          clients: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  renderClients = () => {
    const clientsList = this.state.clients.map((client) => (
      <tr key={client.id}>
        <th>{client.id}</th>
        <th>{client.firstName}</th>
        <th>{client.lastName}</th>
        <th>{client.email}</th>
        <th style={{ color: "blue" }}>{client.username}</th>
        <th>{client.address}</th>
        <th>{client.age}</th>
        <th>{client.telephone}</th>
        <th>{client.blocked + ""}</th>
        <th style={{ color: client.conected ? "green" : "red" }}>
          {client.conected + ""}
        </th>
        <th>
          <button
            onClick={() => this.setState({ id: client.id, blocked: true })}
          >
            Block
          </button>
        </th>
        <th>
          <button
            onClick={() => this.setState({ id: client.id, blocked: false })}
          >
            Unblock
          </button>
        </th>
      </tr>
    ));

    return (
      <table style={{ width: "80%" }}>
        <tr style={{ backgroundColor: "gray" }}>
          <th>Id</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
          <th>Username</th>
          <th>Address</th>
          <th>Age</th>
          <th>Telephone</th>
          <th>Blocked</th>
          <th>Connected</th>
        </tr>
        {clientsList}
      </table>
    );
  };

  handleSave = (e) => {
    e.preventDefault();
    const body = {
      id: this.state.id, //this.state.clientid,
      blocked: this.state.blocked,
    };
    window.location.reload();
    axiosInstance
      .put("/client/block", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  render() {
    if (localStorage.getItem("USER") === "ADMIN")
      return (
        <div className="ClientsContainer">
          {this.renderClients()}
          Client id: {this.state.id}
          <div>
            <button type="submit" onClick={this.handleSave}>
              Submit
            </button>
          </div>
        </div>
      );
    else return <div>You need to login</div>;
  }
}
export default Clients;
