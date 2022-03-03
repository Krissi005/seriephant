import Button from "../Button";
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export const CreateSeason = () => {
    const navigate = useNavigate()
    const [seasonNumber, setSeasonNumber] = useState("");
    const [serie, setSerie] = useState("");

    const handleChange = (event) => {
        setSeasonNumber(event.target.value);
    }

    const handleChangeSerie = (event) => {
        setSerie(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.post("http://localhost:8080/season/create", {"seasonNumber": seasonNumber, "serieId": serie}).then(res => {
            //document.getElementById("userNav").children.item(0).click()
            if (res.status === 200) {
                navigate("/seasons");
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
                Season Number <br/>
                <input type="text" value={seasonNumber} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Serie Id <br/>
                <input type="text" value={serie} onChange={handleChangeSerie}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}