import React from "react";
import axiosInstance from "../../axios";
class ClientWarranty extends React.Component {
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
      .get(`/warranty/${localStorage.getItem("USER_ID")}`)
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
      <tr key={computer.id}>
        <th>{computer.id}</th>
        <th>{computer.productName}</th>
        <th>{computer.days}</th>
        <th>{computer.date}</th>
        <th>{computer.expired + ""}</th>

        {computer.days > 0 ? (
          <th>
            <button
              onClick={() =>
                this.setState({
                  id: computer.id,
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
          <th>Id</th>
          <th>Product Name</th>
          <th>Days</th>
          <th>Date</th>
          <th>Repair Request</th>
        </tr>
        {computerList}
      </table>
    );
  };

  handleSave = (e) => {
    e.preventDefault();

    const body = {
      warrantyId: this.state.id,
      repairRequest: !this.state.repairRequest,
    };
    console.log(body);
    axiosInstance
      .put("/warranty/repairrequest", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
    window.location.reload();
  };

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const data = localStorage.getItem("USER");
    if (data === "CLIENT") {
      return (
        <div className="Computerscontainer">
          <div>
            {this.renderComputers()}
            Product id: {this.state.id}
            <button type="submit" onClick={this.handleSave}>
              Submit
            </button>
          </div>
        </div>
      );
    } else {
      return <div>You need to login</div>;
    }
  }
}

export default ClientWarranty;
