import React, { useState } from 'react';
import '../Css/index.css';

function AddIssue({admin}) {

    const [title, setTitle] = useState();
    const [link, setLink] = useState();
    const [price, setPrice] = useState();


    const handleAdd = async e => {
        e.preventDefault();
        let credentials = {
            title,
            link,
            price,
            admin
        }
        fetch('http://localhost:8082/issue/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                if (data.severity === "FAILURE") {
                    alert(data.message)
                } else {
                    alert("Success!")
                    window.location.reload()
                }
            })

    }

    if (admin !== null && admin !== undefined) return(
        <div className="init">
            <form onSubmit={handleAdd}>
                <h1>Add Issue</h1>
                <label>
                    <p>Title</p>
                    <input className="form-input" type="text" onChange={e => setTitle(e.target.value)}/>
                </label>
                <label>
                    <p>Link Preview</p>
                    <input className="form-input" type="text" onChange={e => setLink(e.target.value)}/>
                </label>
                <label>
                    <p>Price</p>
                    <input className="form-input" type="number" onChange={e => setPrice(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit">Add</button>
                </div>
            </form>
        </div>
    )
}

export default AddIssue;