import Button from "../Button";
import React, {useState} from "react";
import axios from "axios";

export const CreateUser = ({userProfile, choose, reset}) => {
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