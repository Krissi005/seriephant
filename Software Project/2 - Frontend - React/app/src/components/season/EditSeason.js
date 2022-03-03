import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

export const EditSeason = () => {
    const params = useParams();
    const navigate = useNavigate()
    const [id, setId] = useState(null);
    const [seasonNumber, setSeasonNumber] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/season/readById", {params: {seasonId: params.seasonId}}).then(
            res => {
                setId(res.data.id);
                setSeasonNumber(res.data.seasonNumber);
            })
    }, []);

    const handleChange = (event) => {
        setSeasonNumber(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.put("http://localhost:8080/episode/update", {
            "id": id,
            "seasonNumber": seasonNumber
        }).then(res => {
            if (res.status === 200) {
                navigate("/seasons");
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New Season</h1>
        <form>
            <label>
                Season Number <br/>
                <input type="text" value={seasonNumber} onChange={handleChange}/>
            </label>
            <br/>
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}