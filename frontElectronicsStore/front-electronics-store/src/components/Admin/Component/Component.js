import React from "react";
import axiosInstance from "../../../axios.js";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";

class Component extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      components: [],
      id: null,
      pret: null,
    };
    this.fetchAllComputers.bind(this);
  }

  connect() {
    const URL = "http://localhost:8080/socket";
    const websocket = new SockJS(URL);
    const stompClient = Stomp.over(websocket);
    stompClient.connect({}, (frame) => {
      console.log("Conectat la " + frame);
      stompClient.subscribe("/topic/socket/components", (notification) => {
        let message = notification.body;
        alert(message);
      });
    });
  }

  updatePrice = (event) => {
    event.preventDefault();
    this.setState({
      pret: event.target.value,
    });
  };

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  componentDidMount() {
    this.fetchAllComputers();
    this.connect();
  }

  fetchAllComputers = () => {
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
        <th>{component.specifications}</th>
        <th>{component.price}</th>
        <th>
          <button onClick={() => this.setState({ id: component.id })}>
            Edit price
          </button>

          <button
            type="submit"
            onClick={() => {
              axiosInstance.delete(`component/delete/${component.id}`);
              window.location.reload();
            }}
          >
            Delete me
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
      id: this.state.id,
      price: this.state.pret,
    };
    axiosInstance
      .put("component", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  render() {
    //console.log("am ajuns");
    console.log(localStorage.getItem("USER"));
    const data = localStorage.getItem("USER");
    if (data === "ADMIN") {
      return (
        <div className="componentContainers">
          {this.renderComputers()}
          Editing {this.state.id}
          <input
            type="text"
            placeholder="Change the price"
            name="price"
            value={this.state.pret}
            onChange={this.updatePrice}
          ></input>
          <button type="submit" onClick={this.handleSave}>
            Submit changes
          </button>
        </div>
      );
    }
    if (data !== "ADMIN") {
      return <div>You need to login</div>;
    }
  }
}

export default Component;
