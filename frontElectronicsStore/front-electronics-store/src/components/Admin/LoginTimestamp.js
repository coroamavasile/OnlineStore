import React from "react";
import axiosInstance from "../../axios.js";
import {
  Avatar,
  Button,
  List,
  ListItemIcon,
  ListItem,
  ListItemText,
  Typography,
  TextField,
} from "@material-ui/core";
import { saveAs } from "file-saver";
class LoginTimestamp extends React.Component {
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
      .get("/timestamp")
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
        <th style={{ color: "blue" }}>{client.username}</th>
        <th>{client.login}</th>
        <th>{client.logout}</th>
        <th>{!client.finished + ""}</th>
      </tr>
    ));

    return (
      <table style={{ width: "80%" }}>
        <tr style={{ backgroundColor: "gray" }}>
          <th>Id</th>
          <th>Username</th>
          <th>Login</th>
          <th>Logout</th>
          <th>Session</th>
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

  exportData(fileType) {
    axiosInstance
      .get("/exporttimestamp/", {
        params: { fileType: fileType },
        responseType: "text",
      })
      .then((res) => {
        let typeForBlob =
          fileType == "txt"
            ? "text/plain;charset=utf-8"
            : "text/xml;charset=utf-8";
        let blob = new Blob([res.data], { type: typeForBlob });
        saveAs(blob, "connection-time-stamp." + fileType);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    if (localStorage.getItem("USER") === "ADMIN")
      return (
        <div className="ClientsContainer">
          {this.renderClients()}

          <p>Exportare Timestamp</p>
          <Button onClick={() => this.exportData("xml")}>XML</Button>
          <Button onClick={() => this.exportData("txt")}>TXT</Button>
        </div>
      );
    else return <div>You need to login</div>;
  }
}
export default LoginTimestamp;
