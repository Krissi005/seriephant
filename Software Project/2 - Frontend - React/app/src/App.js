import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from "react";
import Header from "./components/Header";
import axios from "axios";

class App extends React.Component {
    state = {
        userProfiles: [],
        chosenUserProfile: null
    }

    chooseUser = (event, userProfile, directory) => {
        this.setState({chosenUserProfile: userProfile});
    }

    reset = (event) => {
        this.setState({chosenUserProfile: null});
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
                    <Header choosenUser={this.state.chosenUserProfile} reset={this.reset}/>
                </div>
            )
        }
}

export default App;
