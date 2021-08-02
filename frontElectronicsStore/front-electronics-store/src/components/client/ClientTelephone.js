import React from "react";
import axiosInstance from "../../axios.js";
class ClientTelephone extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      telephones: [],
      id: null,
      idClient: null,
      pret: null,
      minPrice: null,
      maxPrice: null,
      filteredTelephones: [],
      viewFilter: false,
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
      .get("/telephone")
      .then((response) => {
        this.setState({
          telephones: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  renderComputers = () => {
    const telephonesList = this.state.telephones.map((telephone) => (
      <tr key={telephone.id}>
        <th>{telephone.id}</th>
        <th>{telephone.name}</th>
        <th>{telephone.price}</th>
        <th>{telephone.company}</th>
        <th>{telephone.operatingSystem}</th>
        <th>{telephone.specifications}</th>
        <th>
          <button onClick={() => this.setState({ id: telephone.id })}>
            Select to add in cart
          </button>
        </th>
      </tr>
    ));

    return (
      <table style={{ width: "70%" }}>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Price</th>
          <th>Company</th>
          <th>OS</th>
          <th>Specifications</th>
          <th>Actions</th>
        </tr>
        {telephonesList}
      </table>
    );
  };

  handleSave = (e) => {
    e.preventDefault();
    const body = {
      idClient: this.state.idClient, //this.state.clientid,
      idProduct: this.state.id,
    };
    // console.log(body);
    axiosInstance
      .post("/client/addcart", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  deactivateFilter = (event) => {
    event.preventDefault();
    this.setState({
      viewFilter: false,
    });
  };

  handleInsert = (e) => {
    e.preventDefault();
    this.setState({
      viewFilter: true,
    });
    let body = {
      minPrice: this.state.minPrice,
      maxPrice: this.state.maxPrice,
    };

    axiosInstance
      .post("/telephone/pricefilter", body)
      .then((response) => {
        this.setState({
          filteredTelephones: response.data,
        });
        console.log(response);
      })
      .catch((e) => console.log(e));
  };

  renderFilteredTelephones = () => {
    const telephonesList = this.state.filteredTelephones.map((telephone) => (
      <tr key={telephone.id}>
        <th>{telephone.id}</th>
        <th>{telephone.name}</th>
        <th>{telephone.price}</th>
        <th>{telephone.company}</th>
        <th>{telephone.operatingSystem}</th>
        <th>{telephone.specifications}</th>
        <th>
          <button onClick={() => this.setState({ id: telephone.id })}>
            Select to add in cart
          </button>
        </th>
      </tr>
    ));

    return (
      <table style={{ width: "70%" }}>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Color</th>
          <th>Specifications</th>
          <th>Year</th>
          <th>Price</th>
        </tr>
        {telephonesList}
      </table>
    );
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
              Add in cart
            </button>
          </div>
          <div>Add a filter</div>
          <input
            type="number"
            placeholder="Min price"
            name="minPrice"
            value={this.state.minPrice}
            onChange={this.updateAttributes}
          ></input>
          <input
            type="number"
            placeholder="Max price"
            name="maxPrice"
            value={this.state.maxPrice}
            onChange={this.updateAttributes}
          ></input>
          <button type="submit" onClick={this.handleInsert}>
            Activate Filter
          </button>
          <button type="submit" onClick={this.deactivateFilter}>
            Deactivate Filter
          </button>

          {this.state.viewFilter ? (
            <div>
              {this.renderFilteredTelephones()}
              Product id: {this.state.id}
              <button type="submit" onClick={this.handleSave}>
                Add in cart
              </button>
            </div>
          ) : null}
        </div>
      );
    } else {
      return <div>You need to login</div>;
    }
  }
}

export default ClientTelephone;
