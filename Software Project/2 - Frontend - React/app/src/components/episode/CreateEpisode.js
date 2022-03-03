import Button from "../Button";
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export const CreateEpisode = () => {
    const [title, setTitle] = useState("");
    const [episodeNumber, setEpisodeNumber] = useState("");
    const [releaseDate, setReleaseDate] = useState("yyyy-MM-dd");
    const [season, setSeason] = useState("");
    const navigate = useNavigate()

    const handleChange = (event) => {
        setTitle(event.target.value);
    }

    const handleChangeEpisodeNumber = (event) => {
        setEpisodeNumber(event.target.value);
    }

    const handleChangeReleaseDate = (event) => {
        setReleaseDate(event.target.value);
    }

    const handleChangeSeason = (event) => {
        setSeason(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.post("http://localhost:8080/episode/create", {
            "title": title,
            "releaseDate": releaseDate.equals("yyyy-MM-dd") ? null : releaseDate,
            "episodeNumber": episodeNumber,
            "season": {"id": season}
        }).then(res => {
            if (res.status === 200) {
                navigate('/allEpisodes')
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New User</h1>
        <form>
            <label>
                Title <br/>
                <input type="text" value={title} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Episode Number <br/>
                <input type="text" value={episodeNumber} onChange={handleChangeEpisodeNumber}/>
            </label>
            <br/>
            <label>
                Release Year <br/>
                <input type="text" value={releaseDate} onChange={handleChangeReleaseDate}/>
            </label>
            <br/>
            <label>
                Season Id <br/>
                <input type="text" value={season} onChange={handleChangeSeason}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}