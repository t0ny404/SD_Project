import React, {Component, useEffect, useState} from "react";
import '../Css/index.css';

function Issues({issues}) {

    const [total, setTotal] = useState(0)

    const addToCart = async (f) => {
        fetch('http://localhost:8082/cart/add', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(f),
            credentials: "include"
        })
            .then(() => {
                setTotal(total + f.price)
                alert("Total: " + (total + f.price).toString())
            })
    }

    return (<div>
            <h1> Issues </h1>
            <div>
                {issues?.map((f, i) => (
                    <div key={i}>
                        <h1> {f.title} </h1>
                        {f.link !== null && <a href={f.link}>Preview</a>}
                        <h3> Price: {f.price} </h3>
                        <button className="form-button" onClick={() => addToCart(f)}>Add to cart</button>
                    </div>))}
            </div>
        </div>)
}

export default Issues;