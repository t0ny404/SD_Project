import React, {useEffect, useState} from 'react';
import '../Css/index.css';
import {useLocation, useNavigate} from "react-router-dom";
import Orders from "./Orders";
import GetCurrent from "../GetCurrent";
import Rate from "./Rate";


function AdminView() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})

    const navigate = useNavigate()

    useEffect(() => {
        GetCurrent(setAuth, setUser, navigate)
    }, [])

    if (auth && user.type === 'Admin') return (
        <div>
            <div className="split left">
                <Rate/>
            </div>

            <div className="split right">
                <Orders/>
                <a href="http://localhost:8082/logout">
                    <button className="form-button"> LogOut </button>
                </a>
            </div>
        </div>)
}

export default AdminView;