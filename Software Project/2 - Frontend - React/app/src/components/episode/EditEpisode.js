import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

export const EditEpisode = () => {
    const params = useParams();
    const navigate = useNavigate()
    const [id, setId] = useState(null);
    const [title, setTitle] = useState("");
    const [episodeNumber, setEpisodeNumber] = useState("");
    const [releaseDate, setReleaseDate] = useState("");
    const [seasonId, setSeasonId] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/episode/readById", {params: {episodeId: params.episodeId}}).then(
            res => {
                setId(res.data.id);
                setTitle(res.data.title);
                setEpisodeNumber(res.data.episodeNumber);
                setReleaseDate(res.data.releaseDate);
                setSeasonId(res.data.season.id);
            })
    }, []);

    const handleChange = (event) => {
        setTitle(event.target.value);
    }

    const handleChangeLastName = (event) => {
        setEpisodeNumber(event.target.value);
    }

    const handleChangeReleaseYear = (event) => {
        setReleaseDate(event.target.value);
    }

    const handleChangeGenre = (event) => {
        setSeasonId(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.put("http://localhost:8080/episode/update", {
            "id": id,
            "title": title,
            "episodeNumber": episodeNumber,
            "releaseDate": releaseDate,
            "season": {"id": seasonId}
        }).then(res => {
            if (res.status === 200) {
                navigate('/allEpisodes');
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New Episode</h1>
        <form>
            <label>
                Title <br/>
                <input type="text" value={title} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Epsiode Number <br/>
                <input type="text" value={episodeNumber} onChange={handleChangeLastName}/>
            </label>
            <br/>
            <label>
                Release Date <br/>
                <input type="text" value={releaseDate} onChange={handleChangeReleaseYear}/>
            </label>
            <br/>
            <label>
                Season Id <br/>
                <input type="text" value={seasonId} onChange={handleChangeGenre}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}