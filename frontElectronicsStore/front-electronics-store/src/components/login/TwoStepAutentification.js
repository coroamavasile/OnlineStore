import React from "react";
import { Route, Link, Redirect, Switch, withRouter } from "react-router-dom";
class TwoStepAutentification extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      autentificationCode: "",
      codeFromLogin: "",
    };
    this.fetchCodeFromLogin.bind(this);
  }

  updateAttributes = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  componentDidMount() {
    this.fetchCodeFromLogin();
  }

  fetchCodeFromLogin = () => {
    this.setState({
      //   codeFromLogin: this.props.data,
    });
  };

  handleInsert = (e) => {
    e.preventDefault();

    //console.log(this.state.autentificationCode);
    console.log(this.props.data);
    if (this.state.autentificationCode == this.props.data) {
      console.log(this.props.history);
      localStorage.setItem("USER", "ADMIN");
      localStorage.setItem("USER_ID", this.props.id);
      this.props.history.push("/admin");
    } else {
      return <div>Codul introdus este gresit!</div>;
    }
  };

  render() {
    return (
      <div className="TwoStepAutentification">
        Two step autentification
        <form>
          <input
            type="number"
            placeholder="Autentification Code"
            name="autentificationCode"
            value={this.state.autentificationCode}
            onChange={this.updateAttributes}
          ></input>
          <button type="submit" onClick={this.handleInsert}>
            Submit
          </button>
        </form>
      </div>
    );
  }
}
export default TwoStepAutentification;
