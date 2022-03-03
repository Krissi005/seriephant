import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

export const EditSerie = () => {
    const params = useParams();
    const navigate = useNavigate();
    const [id, setId] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [releaseYear, setReleaseYear] = useState("");
    const [genre, setGenre] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/serie/readById", {params: {serieId: params.serieId}}).then(
            res => {
                setId(res.data.id);
                setTitle(res.data.title);
                setDescription(res.data.description);
                setReleaseYear(res.data.releaseYear);
                setGenre(res.data.genre.id);
            })
    }, []);

    const handleChange = (event) => {
        setTitle(event.target.value);
    }

    const handleChangeLastName = (event) => {
        setDescription(event.target.value);
    }

    const handleChangeReleaseYear = (event) => {
        setReleaseYear(event.target.value);
    }

    const handleChangeGenre = (event) => {
        setGenre(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.put("http://localhost:8080/serie/update", {
            "id": id,
            "title": title,
            "description": description,
            "releaseYear": releaseYear,
            "genre": {"id": genre}
        }).then(res => {
            if (res.status === 200) {
                navigate('/series')
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New Serie</h1>
        <form>
            <label>
                Title <br/>
                <input type="text" value={title} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Description <br/>
                <input type="text" value={description} onChange={handleChangeLastName}/>
            </label>
            <br/>
            <label>
                Release Year <br/>
                <input type="text" value={releaseYear} onChange={handleChangeReleaseYear}/>
            </label>
            <br/>
            <label>
                Genre Id <br/>
                <input type="text" value={genre} onChange={handleChangeGenre}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}