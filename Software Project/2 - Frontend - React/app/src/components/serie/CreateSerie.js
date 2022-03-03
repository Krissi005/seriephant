import Button from "../Button";
import React, {useState} from "react";
import axios from "axios";

export const CreateSerie = () => {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [releaseYear, setReleaseYear] = useState("");
    const [genre, setGenre] = useState("");

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
        axios.post("http://localhost:8080/serie/create", {"title": title, "description": description, "releaseYear": releaseYear, "genre": {"id":genre}}).then(res => {
            //document.getElementById("userNav").children.item(0).click()
            if (res.status === 200) {
                window.open("/series", "_self");
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