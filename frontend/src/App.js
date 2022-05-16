import logo from './Resources/pngwing.com.png';
import './Css/App.css';
import React, {Component, useState} from "react";
import {NavLink, useNavigate} from "react-router-dom";

class App extends Component {

    render() {
            return (
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <h1>
                            Comics Store
                        </h1>
                        <a href="http://localhost:8082/login"> <button className="form-button" type="button"> LogIn </button> </a>
                        <NavLink to={"/signup"}><button className="form-button"> SignUp </button></NavLink>
                    </header>
                </div>
            );
    }
}

export default App;
