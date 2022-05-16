import React, {Component, useEffect, useState} from "react";
import '../Css/index.css';

function Issues({issues}) {

    const remove = async (f) => {
        fetch('http://localhost:8082/issue/remove', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(f),
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                if (data.severity === "FAILURE")
                    alert(data.message)
                else {
                    alert(data.message)
                    window.location.reload()
                }
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
                    <button className="form-button" onClick={() => remove(f)}>Remove</button>
                </div>))}
        </div>
    </div>)
}

export default Issues;