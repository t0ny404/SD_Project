import React, {useEffect, useState} from 'react';
import '../Css/index.css';


function AddSeries({publisher}) {
    const [title, setTitle] = useState()
    const [writer, setWriter] = useState()


    const handleAdd = async e => {
        e.preventDefault();
        let credentials = {
            title,
            writer,
            publisher
        }

        fetch('http://localhost:8082/series/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
            credentials: "include"
        }).then(() => window.location.reload())
    }

    return(
        <div className="init">
            <form onSubmit={handleAdd}>
                <h1>Add Series</h1>
                <label>
                    <p>Title</p>
                    <input className="form-input" type="text" onChange={e => setTitle(e.target.value)}/>
                </label>
                <label>
                    <p>Writer</p>
                    <input className="form-input" type="text" onChange={e => setWriter(e.target.value)}/>
                </label>
                <div>
                    <button className="form-button" type="submit">Add</button>
                </div>
            </form>
        </div>
    )
}

export default AddSeries;