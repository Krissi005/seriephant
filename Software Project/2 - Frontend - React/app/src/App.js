import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import React from "react";
import Header from "./components/Header";
import {
    BrowserRouter as Router,
    Route,
    Routes
} from "react-router-dom";
import {Users} from "./components/user/Users";
import {CreateUser} from "./components/user/CreateUser";
import {EditUser} from "./components/user/EditUser";

class App extends React.Component {
    state = {
        userProfiles: [],
        choosedUserProfile: null
    }

    setRowData = (rowData) => {
        this.setState({rowData: rowData});
    }

    watch = (event, userProfile, episode) =>{
        axios.put("http://localhost:8080/user/updateEpisodes", { params: {userId: userProfile.id, episodeId: episode.id} }).then(res => {
            //document.getElementById("userNav").children.item(0).click()
            if (res.status === 200) {
                window.open("/episodes", "_self");
                window.alert("Succesfull :)");
                window.alert("Succesfull :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    chooseUser = (event, userProfile, directory) => {
        this.setState({choosedUserProfile: userProfile});
    }

    edit = (event, userProfile, directory) => {
        window.open("/"+directory+":"+userProfile, "_self");
    }

    reset = (event) => {
        this.setState({choosedUserProfile: null});
    }

    componentDidMount() {
        axios.get("http://localhost:8080/user/read").then(res => {
            this.setState({
                userProfiles: res.data
            });
        });
    }

    render() {
        return (
            <div className="App">
                <Router>
                    <Header choosedUser={this.state.choosedUserProfile} reset={this.reset}/>
                    <Routes>
                        <Route path="/users" element={<Users userProfile={this.state.choosedUserProfile} reset={this.reset}
                                                             choose={this.chooseUser} edit={this.edit}/>}/>
                        <Route path="/createUser" element={<CreateUser/>}/>
                        <Route path="/editUser/:userId" element ={<EditUser/>}/>
                        </Routes>
                </Router>
            </div>
        )
    }
}

export default App;
