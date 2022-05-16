import React, {Component, useEffect, useState} from "react";
import '../Css/index.css';
import {useLocation, useNavigate} from "react-router-dom";
import GetCurrent from "../GetCurrent";
import Card from "@material-ui/core/Card";
import {CardContent} from "@material-ui/core";


function Cart() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})

    const [issues, setIssues] = useState()
    const [total, setTotal] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        const getIssues = async () => {
            fetch('http://localhost:8082/cart/all', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
            })
                .then(data => data.json())
                .then(data => {
                    setIssues(data)
                })
        }
        const getTotal = async () => {
            fetch('http://localhost:8082/cart/total', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
            })
                .then(data => data.json())
                .then(data => {
                    setTotal(data)
                })
        }

        GetCurrent(setAuth, setUser, navigate);
        getIssues();
        getTotal();
    }, [])

    const order = async () => {
        const address = prompt("Enter address")

        let id = user.id
        let o = {
            id,
            address
        }
        fetch('http://localhost:8082/order/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(id),
                credentials: "include"
            })
                .then(() => {
                    alert("Order of: " + total.toString() + " placed!")
                    setIssues(null)
                    navigate('/orders')
                })
    }

    const cartAction = async (f, action) => {
        fetch('http://localhost:8082/cart/' + action, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(f),
            credentials: "include"
        })
        window.location.reload()
    }

    if (auth && user.type === 'Customer') return (<div>
        <div style={{display: "grid",
                    gridGap: 50,
                    gridTemplateColumns: "repeat(auto-fill, minmax(320px, 1fr) )"}}>
            {issues?.map((f, i) => (
                 <div key={i} style={{display: "flex"}}>
                     <Card style={{backgroundColor: "moccasin"}}>
                         <CardContent>
                             <h1> {f.series}: {f.title} </h1>
                             {f.link !== null && <a href={f.link}>Preview</a>}
                             <h3> Price: {f.price}, Quantity: {f.quantity} </h3>
                             {f.publisher !== undefined && <img width='100' src={require("../Resources/" + f.publisher)} alt=""/>}

                             <button className="form-button" onClick={() => cartAction(f, 'add')}> Add </button>
                             <button className="form-button" onClick={() => cartAction(f, 'remove')}> Remove </button>
                         </CardContent>
                     </Card>
                 </div>
             ))}
        </div>

        <div>
            <h1> TOTAL:  {total} </h1>
            <button className="form-button" onClick={order}> Order </button>
        </div>
    </div>)
}

export default Cart;