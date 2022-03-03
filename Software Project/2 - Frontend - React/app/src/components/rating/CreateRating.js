import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export const CreateRating = (userProfile) => {
    const params = useParams();
    const [userId, setUserId] = useState(userProfile.userProfile.id);
    const [episodeId, setEpisodeId] = useState(params.episodeId);
    const [rating, setRating] = useState("");

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

    const handleChangeReleaseDate = (event) => {
        setRating(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.post("http://localhost:8080/rating/create",
            {
                "user": {
                    "id": userId
                },
                "episode": {
                    "id": episodeId
                },
                "rating": rating
            }
        ).then(res => {
            if (res.status === 200) {
                window.open("/rating", "_self");
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New Rating</h1>
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
            <label>
                Rating <br/>
                <input type="text" value={rating} onChange={handleChangeReleaseDate}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}