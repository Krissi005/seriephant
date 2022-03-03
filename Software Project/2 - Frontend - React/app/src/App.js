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
import {SerieTable} from "./components/serie/SerieTable";
import {SeasonTable} from "./components/season/SeasonTable";
import {Users} from "./components/user/Users";
import {GenreTable} from "./components/genre/GenreTable";
import {ActorTable} from "./components/actor/ActorTable";
import {CreateUser} from "./components/user/CreateUser";
import {EditUser} from "./components/user/EditUser";
import {EpisodeTable} from "./components/episode/EpisodeTable";
import {RatingTable} from "./components/rating/RatingTable";
import {CreateSerie} from "./components/serie/CreateSerie";
import {CreateSeason} from "./components/season/CreateSeason";
import {CreateEpisode} from "./components/episode/CreateEpisode";
import {CreateRating} from "./components/rating/CreateRating";
import {EditSerie} from "./components/serie/EditSerie";
import {EditSeason} from "./components/season/EditSeason";
import {EditEpisode} from "./components/episode/EditEpisode";
import {WatchEpisode} from "./components/episode/WatchEpisode";
import {MyEpisodeTable} from "./components/episode/MyEpisodeTable";

class App extends React.Component {
    state = {
        userProfiles: [],
        chosenUserProfile: null
    }

    constructor() {
        super();
        this.chooseUser= this.chooseUser.bind(this);
    }

    watch = (event, userProfile, episode) =>{
        axios.put("http://localhost:8080/user/updateEpisodes", { params: {"userId": userProfile.id, "episodeId": episode.id} }).then(res => {
            //document.getElementById("userNav").children.item(0).click()
            if (res.status === 200) {
                window.open("/episodes", "_self");
                window.alert("Succesfull :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    chooseUser = (event, userProfile, directory) => {
        this.setState({chosenUserProfile: userProfile});
    }

    edit = (event, userProfile, directory) => {
        window.open("/"+directory+":"+userProfile, "_self");
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
                    <Header chosenUser={this.state.chosenUserProfile} reset={this.chooseUser}/>
                    <Routes>
                        <Route path="/users" element={<Users userProfile={this.state.chosenUserProfile} reset={this.chooseUser}
                                                             choose={this.chooseUser} edit={this.edit}/>}/>
                        <Route path="/createUser" element={<CreateUser/>}/>
                        <Route path="/editUser/:userId" element ={<EditUser/>}/>
                        <Route path="/series" element={<SerieTable userProfile={this.state.chosenUserProfile}
                                                                   onClick={this.chooseUser}/>}/>
                        <Route path="/createSerie" element={<CreateSerie/>}/>
                        <Route path="/editSerie/:serieId" element ={<EditSerie/>}/>
                        <Route path="/seasons" element={<SeasonTable userProfile={this.state.chosenUserProfile}
                                                                     onClick={this.chooseUser}/>}/>
                        <Route path="/createSeason" element={<CreateSeason/>}/>
                        <Route path="/editSeason/:seasonId" element ={<EditSeason/>}/>
                        <Route path="/allEpisodes" element={<EpisodeTable userProfile={this.state.chosenUserProfile}
                                                                          onClick={this.chooseUser}/>}/>
                        <Route path="/createEpisode" element={<CreateEpisode/>}/>
                        <Route path="/watchEpisode/:episodeId" element={<WatchEpisode/>}/>
                        <Route path="/editEpisode/:episodeId" element ={<EditEpisode/>}/>
                        <Route path="/episodes" element={<MyEpisodeTable userProfile={this.state.chosenUserProfile}
                                                                          onClick={this.chooseUser}/>}/>
                        <Route path="/rating" element={<RatingTable userProfile={this.state.chosenUserProfile}
                                                                    onClick={this.chooseUser}/>}/>
                        <Route path="/editRating/:episodeId" element={<CreateRating userProfile={this.state.chosenUserProfile}/>}/>
                        <Route path="/createRating" element={<CreateRating/>}/>
                        <Route path="/genres" element={<GenreTable userProfile={this.state.chosenUserProfile}
                                                                   onClick={this.chooseUser}/>}/>
                        <Route path="/actors" element={<ActorTable userProfile={this.state.chosenUserProfile}
                                                                   onClick={this.chooseUser}/>}/>
                    </Routes>
                </Router>
            </div>
        )
    }
}

export default App;
