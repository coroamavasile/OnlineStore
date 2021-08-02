import React from "react";
import axiosInstance from "../../../axios.js";
import * as Stomp from "stompjs";
import * as SockJS from "sockjs-client";
class Telephone extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      telephones: [],
      id: null,
      price: null,
    };

    this.fetchAllTelephones.bind(this);
  }

  componentDidMount() {
    this.fetchAllTelephones();
    this.connect();
  }

  connect() {
    const URL = "http://localhost:8080/socket";
    const websocket = new SockJS(URL);
    const stompClient = Stomp.over(websocket);
    stompClient.connect({}, (frame) => {
      console.log("Conectat la " + frame);
      stompClient.subscribe("/topic/socket/telephones", (notification) => {
        let message = notification.body;
        console.log(message);
        alert(message);
      });
    });
  }

  fetchAllTelephones = () => {
    axiosInstance
      .get("/telephone")
      .then((response) => {
        //console.log(response.data);
        this.setState({
          telephones: response.data,
        });
      })
      .catch((error) => console.log(error));
  };

  updatePrice = (event) => {
    this.setState({
      price: event.target.value,
    });
    // console.log(this.state.price);
  };

  handleSave = (e) => {
    e.preventDefault();
    const body = {
      id: this.state.id,
      price: this.state.price,
    };
    axiosInstance
      .put("/telephone/updateprice", body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
    window.location.reload();
  };

  renderTelephones = () => {
    const telephonesList = this.state.telephones.map((telephone) => (
      <tr key={telephone.id}>
        <th>{telephone.id}</th>
        <th>{telephone.name}</th>
        <th>{telephone.price}</th>
        <th>{telephone.company}</th>
        <th>{telephone.os}</th>
        <th>{telephone.specifications}</th>
        <button
          onClick={() => {
            axiosInstance.delete(`telephone/delete/${telephone.id}`);
            window.location.reload();
          }}
        >
          Delete me
        </button>
        <button onClick={() => this.setState({ id: telephone.id })}>
          Edit price
        </button>
      </tr>
    ));
    return (
      <table style={{ width: "70" }}>
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

  render() {
    // console.log(this.state.telephones);
    const data = localStorage.getItem("USER");
    if (data === "ADMIN")
      return (
        <div className="TelephoneContainer">
          {this.renderTelephones()}
          Editing {this.state.id}
          <input
            type="text"
            placeholder="Change the price"
            name="price"
            value={this.state.pret}
            onChange={this.updatePrice}
          ></input>
          <button type="submit" onClick={this.handleSave}>
            Submit
          </button>
        </div>
      );
    else return <div>You need to login</div>;
  }
}

export default Telephone;
