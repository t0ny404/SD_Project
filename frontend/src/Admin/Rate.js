import React, {useEffect, useState} from "react";
import Card from "@material-ui/core/Card";
import CardMedia from "@material-ui/core/CardMedia";
import {CardContent} from "@material-ui/core";
import {useNavigate} from "react-router-dom";

function Rate() {

    const [series, setSeries] = useState()
    const [category, setCategory] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        const getSeries = async () => {
            fetch('http://localhost:8082/series/notRated', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: "include"
            })
                .then(response => {
                    if (response.status !== 200) {
                        alert(response.status + " unauthorized")
                        navigate('/')
                    }
                    else return response.json()
                })
                .then(data => {
                    setSeries(data)
                })
        }
        getSeries();
    }, [])

    const handleRate = async (id) => {
        fetch('http://localhost:8082/series/rate/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(category),
            credentials: "include"
        })
            .then(() => {
                window.location.reload()
            })
    }

    return (<div>
        <h1> Series </h1>
        <div style={{display: "grid",
            gridGap: 50,
            gridTemplateColumns: "repeat(auto-fill, minmax(320px, 1fr) )"}}> {

            series?.map((r, i) => (
                <div key={i} style={{display: "flex"}}>
                    <Card style={{backgroundColor: "moccasin"}}>
                        {r.cover !== undefined && r.cover !== null && <CardMedia component="img"
                                   style={{height: "auto",
                                       maxHeight: "250px",
                                       width: "auto",
                                       maxWidth: "250px"}}
                                   image={require("../Resources/" + r.cover)}/>}

                        <CardContent>
                            <h1> {r.title} </h1>
                            <h3> Written by: {r.writer} </h3>
                            <img width='100' src={require("../Resources/" + r.publisherLogo)} alt=""/>
                            <form onSubmit={() => handleRate(r.id)}>
                                <select className="form-select" defaultValue="EVERYONE" onChange={e => setCategory(e.target.value)}>
                                    <option value="EVERYONE"> Everyone </option>
                                    <option value="TEEN"> Teen </option>
                                    <option value="MATURE"> Mature </option>
                                </select>
                                <button className="form-button" type="submit">Rate</button>
                            </form>
                        </CardContent>
                    </Card>
                </div>))}
        </div>
    </div>)
}

export default Rate;