import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import GetCurrent from "../GetCurrent";
import Series from "./Series"
import Issues from "./Issues";
import AddIssue from "./AddIssue";
import AddSeries from "./AddSeries";

function PublisherView() {

    const [auth, setAuth] = useState(false)
    const [user, setUser] = useState({id: -1, type: ''})
    const [issues, setIssues] = useState()
    const [sId, setSId] = useState()

    const navigate = useNavigate()

    useEffect(() => {
        GetCurrent(setAuth, setUser, navigate)
    }, [])

    if (auth && user.type === 'Publisher') return (
        <div>
            <div className="split left">
                {user.picture !== undefined && user.picture !== null && <img width="500" src={require("../Resources/" + user.picture)} alt=""/>}
                <Series setIssues={setIssues} pId={user.id} setSId={setSId}/>
                <AddSeries publisher={user.id}/>
            </div>

            <div className="split right">
                <a href="http://localhost:8082/logout">
                    <button className="form-button"> LogOut </button>
                </a>
                <AddIssue admin={sId}/>
                <Issues issues={issues}/>
            </div>
        </div>)
}

export default PublisherView;