import React, {useEffect, useState} from 'react';
import '../Css/index.css';
import {Link, useLocation, useNavigate} from "react-router-dom";
import Series from "./Series";
import Issues from "./Issues";


function CustomerView() {

    const navigate = useNavigate()

    const [issues, setIssues] = useState()

    return (
        <div>
            <div className="split left">
                <Series setIssues={setIssues}/>
            </div>

            <div className="split right">
                <button className="form-button" onClick={() => navigate('/cart')}> Cart </button>
                <button className="form-button" onClick={() => navigate('/orders')}> Orders </button>
                <a href="http://localhost:8082/logout">
                    <button className="form-button"> LogOut </button>
                </a>
                <Issues issues={issues}/>
            </div>
        </div>)
}

export default CustomerView;