import React, {Component, useEffect, useState} from "react";
import '../Css/index.css';
import {useNavigate} from "react-router-dom";
import Card from "@material-ui/core/Card";
import {CardContent} from "@material-ui/core";
import CardMedia from "@material-ui/core/CardMedia";


function Series({setIssues, pId, setSId}) {
    const [series, setSeries] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        const getSeries = async () => {
            fetch('http://localhost:8082/series/publisher/' + pId, {
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

    const getIssues = async (id) => {
        fetch('http://localhost:8082/issue/' + id.toString(), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include"
        })
            .then(data => data.json())
            .then(data => {
                setIssues(data)
                setSId(id)
            })
    }

    return (
        <div>


            <h1> Series </h1>

            <div style={{display: "grid",
                gridGap: 50,
                gridTemplateColumns: "repeat(auto-fill, minmax(320px, 1fr) )"}}> {
                series?.map((r, i) => (
                    r.rating !== null && r.rating !== undefined && <div key={i} style={{display: "flex"}}>
                        <Card style={{backgroundColor: "moccasin"}} onClick={() => getIssues(r.id)}>
                            {r.cover !== undefined && r.cover !== null && <CardMedia component="img"
                                       style={{height: "auto",
                                           maxHeight: "250px",
                                           width: "auto",
                                           maxWidth: "250px"}}
                                       image={require("../Resources/" + r.cover)}/>}
                            <CardContent>
                                <h1> {r.title} </h1>
                                <h3> Written by: {r.writer} </h3>
                                <h4> Rating: {r.rating} </h4>
                                <img width='100' src={require("../Resources/" + r.publisherLogo)} alt=""/>
                            </CardContent>
                        </Card>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Series;