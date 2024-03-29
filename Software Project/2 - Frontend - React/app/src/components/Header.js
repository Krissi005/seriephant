import React from "react";
import logo1 from '../seriephant_long.png';
import {Image} from "react-bootstrap";
import {Link} from "react-router-dom";
import Button from "./Button";

const Header = ({chosenUser, reset}) => {
    return (<nav className="navbar navbar-expand-lg navbar-light bg-light mb-3">
            <div className="container-fluid">
                <a className="navbar-brand" href="https://github.com/Krissi005/seriephant">
                    <Image src={logo1} height="80px"/>
                </a>
                {chosenUser == null ?
                    "" : (<Button onClick={(event) => {
                        reset(event, null);
                        window.open("/users", "_self");
                    }}
                                  text={chosenUser.firstName + " " + chosenUser.lastName} buttonType={"btn-danger"}/>)}
                <ul className="navbar-nav">
                    <li id={"userNav"} className="nav-item">
                        <Link to={"/users"}>Users</Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/series"}>Series</Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/seasons"}>Seasons</Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/allEpisodes"}>All Episodes</Link>
                    </li>
                    {chosenUser == null ?
                        "" : (<li className="nav-item">
                            <Link to={"/myEpisodes"}>My Episodes</Link>
                        </li>)}
                    <li className="nav-item">
                        <Link to={"/genres"}>Genres</Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/actors"}>Actors</Link>
                    </li>
                </ul>
            </div>
        </nav>
    )
};

export default Header;