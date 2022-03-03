import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export const WatchEpisode = ({userProfile}) => {
    const params = useParams();
    const [userId, setUserId] = useState("");
    const [episodeId, setEpisodeId] = useState("");

    useEffect(() => {
        if (userProfile == null) {
            setEpisodeId(params.episodeId)
        }
    }, [userProfile]);

    const handleChange = (event) => {
        setUserId(event.target.value);
    }

    const handleChangeEpisodeNumber = (event) => {
        setEpisodeId(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.put("http://localhost:8080/user/update", {
                "id": userId,
                "watchedEpisodes": [
                    {
                        "id": episodeId
                    }
                ]
            }
        ).then(res => {
            if (res.status === 200) {
                window.open("/episodes", "_self");
                //window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Watch Episode</h1>
        <form>
            <label>
                User Id <br/>
                <input type="text" value={userId} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Episode Id <br/>
                <input type="text" value={episodeId} onChange={handleChangeEpisodeNumber}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}