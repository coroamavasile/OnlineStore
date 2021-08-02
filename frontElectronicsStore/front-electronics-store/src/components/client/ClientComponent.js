import React from "react";
import axiosInstance from "../../axios.js";
class ClientComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      components: [],
      id: null,
      idClient: null,
      pret: null,
      minPrice: null,
      maxPrice: null,
      filteredComponents: [],
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
      .get("/component")
      .then((response) => {
        this.setState({
          components: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  renderComputers = () => {
    const computerList = this.state.components.map((component) => (
      <tr key={component.id}>
        <th>{component.id}</th>
        <th>{component.name}</th>
        <th>{component.price}</th>
        <th>{component.specifications}</th>

        <th>
          <button onClick={() => this.setState({ id: component.id })}>
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
          <th>Specifications</th>
          <th>Actions</th>
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
      .post("/component/pricefilter", body)
      .then((response) => {
        this.setState({
          filteredComponents: response.data,
        });
      })
      .catch((e) => console.log(e));
  };

  renderFilteredComputers = () => {
    const computerList = this.state.filteredComponents.map((component) => (
      <tr key={component.id}>
        <th>{component.id}</th>
        <th>{component.name}</th>
        <th>{component.specifications}</th>
        <th>{component.price}</th>
        <th>
          <button onClick={() => this.setState({ id: component.id })}>
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
          <th>Specifications</th>
          <th>Actions</th>
        </tr>
        {computerList}
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
              {this.renderFilteredComputers()}
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

export default ClientComponent;
