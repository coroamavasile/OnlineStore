import React from "react";
import axiosInstance from "../../../axios.js";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";
const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});
class Computer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      computers: [],
      id: null,
      pret: null,
    };
    this.fetchAllComputers.bind(this);
  }

  updatePrice = (event) => {
    event.preventDefault();
    this.setState({
      pret: event.target.value,
    });
    console.log(this.state.pret);
  };

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  componentDidMount() {
    this.connect();
    this.fetchAllComputers();
  }

  connect() {
    const URL = "http://localhost:8080/socket";
    const websocket = new SockJS(URL);
    const stompClient = Stomp.over(websocket);
    stompClient.connect({}, (frame) => {
      console.log("Conectat la " + frame);
      stompClient.subscribe("/topic/socket/computers", (notification) => {
        let message = notification.body;
        console.log(message);
        alert(message);
      });
    });
  }

  fetchAllComputers = () => {
    axiosInstance
      .get("/computer")
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
        <th>{computer.color}</th>
        <th>{computer.specifications}</th>
        <th>{computer.year}</th>
        <th>{computer.price}</th>
        <th>
          <button onClick={() => this.setState({ id: computer.id })}>
            Edit price
          </button>

          <button
            type="submit"
            onClick={() => {
              axiosInstance.delete(`computer/delete/${computer.id}`);
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
          <th>Color</th>
          <th>Specifications</th>
          <th>Year</th>
          <th>Price</th>
        </tr>
        {computerList}
      </table>
      // <TableContainer component={Paper}>
      //   <Table>
      //     <TableHead>
      //       <TableRow>
      //         <TableCell>Id</TableCell>
      //         <TableCell align="right">Calories</TableCell>
      //         <TableCell align="right">Fat&nbsp;(g)</TableCell>
      //         <TableCell align="right">Carbs&nbsp;(g)</TableCell>
      //         <TableCell align="right">Protein&nbsp;(g)</TableCell>
      //       </TableRow>
      //     </TableHead>
      //     <TableBody>
      //       {this.state.computers.map((row) => (
      //         <TableRow key={row.id}>
      //           <TableCell component="th" scope="row">
      //             {row.id}
      //           </TableCell>
      //           <TableCell align="right">{row.name}</TableCell>
      //           <TableCell align="right">{row.color}</TableCell>
      //           <TableCell align="right">{row.specifications}</TableCell>
      //           <TableCell align="right">{row.year}</TableCell>
      //           <TableCell align="right">{row.price}</TableCell>
      //         </TableRow>
      //       ))}
      //     </TableBody>
      //   </Table>
      // </TableContainer>
    );
  };

  handleSave = (e) => {
    e.preventDefault();
    const body = {
      price: this.state.pret,
    };
    console.log(body);
    window.location.reload();
    axiosInstance
      .put(`/computer/${this.state.id}`, body)
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  };

  render() {
    console.log(localStorage.getItem("USER"));
    const data = localStorage.getItem("USER");
    if (data === "ADMIN") {
      return (
        <div className="Computerscontainer">
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

export default Computer;
