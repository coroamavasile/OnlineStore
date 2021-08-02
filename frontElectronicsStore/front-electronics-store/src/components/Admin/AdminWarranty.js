import axios from "axios";
import React from "react";
import axiosInstance from "../../axios";
class AdminWarranty extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      computers: [],
      id: null,
      idClient: null,
      repairRequest: null,
      pret: null,
      filteredComputers: [],
      viewFilter: false,
    };
    this.fetchAllComputers.bind(this);
  }

  updateAttributes = (event) => {
    event.preventDefault();
    console.log(this.state.repairRequest);
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
      .get("/warranty/adminwarranty")
      .then((response) => {
        console.log(response);
        this.setState({
          computers: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  renderComputers = () => {
    const computerList = this.state.computers.map((computer) => (
      <tr key={computer.idWarranty}>
        <th>{computer.idWarranty}</th>
        <th>{computer.firstName}</th>
        <th>{computer.lastName}</th>
        <th>{computer.username}</th>
        <th>{computer.address}</th>
        <th>{computer.productName}</th>
        <th>{computer.data}</th>
        <th>{computer.days}</th>

        {computer.days > 0 ? (
          <th>
            <button
              onClick={() =>
                this.setState({
                  id: computer.idWarranty,
                  repairRequest: computer.expired,
                })
              }
            >
              Select
            </button>
          </th>
        ) : (
          <th>Expired Warranty</th>
        )}
      </tr>
    ));

    return (
      <table style={{ width: "70%" }}>
        <tr>
          <th>IdW</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Address</th>
          <th>Product Name</th>
          <th>Data</th>
          <th>Days</th>
        </tr>
        {computerList}
      </table>
    );
  };

  handleSave = (e) => {
    e.preventDefault();
    let rel = false;
    const body = {
      warrantyId: this.state.id,
      repairRequest: false,
    };
    console.log(body);
    axiosInstance
      .put("/warranty/repairrequest", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
    axiosInstance
      .post(`/warranty/sendemail/${this.state.id}`)
      .then((res) => {
        console.log(res);
        rel = true;
      })
      .catch((err) => console.log(err));

    if (rel) {
      window.location.reload();
    }
  };

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "ADMIN") {
      return (
        <div className="Computerscontainer">
          <div>
            {this.renderComputers()}
            Product id: {this.state.id}
            <button type="submit" onClick={this.handleSave}>
              Accept Warranty
            </button>
          </div>
        </div>
      );
    } else {
      return <div>You need to login</div>;
    }
  }
}

export default AdminWarranty;
