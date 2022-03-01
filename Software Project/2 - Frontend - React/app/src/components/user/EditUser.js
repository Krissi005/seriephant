import Button from "../Button";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {wait} from "@testing-library/user-event/dist/utils";

export const EditUser = () => {
    const params = useParams();
    const [id, setId] = useState(null);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    useEffect(() => {
            axios.get("http://localhost:8080/user/readById", {params: {userId: params.userId}}).then(
                res => {
                    setId(res.data.id);
                    setFirstName(res.data.firstName);
                    setLastName(res.data.lastName);
                })
    }, []);

    const handleChange = (event) => {
        setFirstName(event.target.value);
    }

    const handleChangeLastName = (event) => {
        setLastName(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.put("http://localhost:8080/user/update", {
            "id": id,
            "firstName": firstName,
            "lastName": lastName
        }).then(res => {
            if (res.status === 200) {
                window.open("/users", "_self");
                window.alert("Succesfull :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div className="container">
        <h1>Create New User</h1>
        <form>
            <label>
                First Name <br/>
                <input type="text" value={firstName} onChange={handleChange}/>
            </label>
            <br/>
            <label>
                Last Name <br/>
                <input type="text" value={lastName} onChange={handleChangeLastName}/>
            </label>
            <br/>
            <Button id={"createUser"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}