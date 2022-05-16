import React, {Component, useEffect, useState} from 'react';
import './Css/index.css';
import AdminView from "./Admin/AdminView";
import CustomerView from "./Customer/CustomerView";
import {useNavigate} from "react-router-dom";
import GetCurrent from "./GetCurrent";
import PublisherView from "./Publisher/PublisherView";

function View() {

    const navigate = useNavigate()

    const [isAuth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})

    useEffect(() => {
        GetCurrent(setAuth, setUser, navigate)
    }, []);

    console.log(user)
    if (isAuth) {
        if (user.type === 'Admin')
            return (<AdminView/>)
        else if (user.type === 'Publisher')
            return (<PublisherView/>)
        else return (<CustomerView/>)
    }
}

export default View;