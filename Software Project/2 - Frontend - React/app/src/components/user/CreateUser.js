import Button from "../Button";
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export const CreateUser = () => {
    const navigate = useNavigate()
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    const handleChange = (event) => {
        setFirstName(event.target.value);
    }

    const handleChangeLastName = (event) => {
        setLastName(event.target.value);
    }

    const handleSubmit = (event) => {
        axios.post("http://localhost:8080/user/create", {"firstName": firstName, "lastName": lastName}).then(res => {
            //document.getElementById("userNav").children.item(0).click()
            if (res.status === 200) {
                navigate("/users");
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
            <Button id={"create"} text={"Submit"} buttonType="btn-success"
                    onClick={handleSubmit}>Submit</Button>
        </form>
    </div>)
}