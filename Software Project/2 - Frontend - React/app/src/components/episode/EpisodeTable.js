import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link} from "react-router-dom";
import {MyEpisodeTable} from "./MyEpisodeTable";
import {UnwatchedEpisodeTable} from "./UnwatchedEpisodeTable";
import {AllEpisodeTable} from "./AllEpisodeTable";

export const EpisodeTable = ({userProfile, onClick, my}) => {
    const [rowData, setRowData] = useState([]);

    const columnDefs = [
        {
            Header: 'Id',
            accessor: "id"
        },
        {
            Header: 'Serie',
            accessor: "season.serie.title"
        },
        {
            Header: 'Season',
            accessor: "season.seasonNumber"
        },
        {
            Header: 'Episode Number',
            accessor: "episodeNumber"
        },
        {
            Header: 'Title',
            accessor: "title"
        },
        {
            Header: 'Release Date',
            accessor: "releaseDate"
        },
        {
            Header: 'Rating',
            accessor: "rating"
        }
    ];

    useEffect(() => {
        if (userProfile == null) {//
            axios.get("http://localhost:8080/episode/read").then(
                res => {
                    setRowData(res.data);
                })
        } else {
            axios.get("http://localhost:8080/rating/readRatingsNotByUser", {params: {userId: userProfile.id}}).then(
                (res) => {
                    setRowData(res.data);
                }
            )

        }
    }, [userProfile, my]);

    const columns = useMemo(() => columnDefs, []);

    const tableInstance = useTable({
        columns,
        data: rowData
    })

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow
    } = tableInstance

    const deleteEpisode = (event, episode) => {
        axios.delete("http://localhost:8080/episode/delete", {params: {"episodeId": episode.id}}).then(
            res => {
                window.alert("Succesfull :)");
                window.open("/users", "_self");
            })
    }

    const watchEpisode = (event, userProfile, episode) => {
        axios.put("http://localhost:8080/user/updateEpisodes", {
                "id": userProfile.id,
                "watchedEpisodes": [
                    {
                        "id": episode.id
                    }
                ]
            }
        ).then(res => {
            if (res.status === 200) {
                window.open("/users", "_self");
                window.alert("Successful :)");
                onClick(event, userProfile)
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<div>
            <Link to={"/createEpisode"}><Button id={"create"} text={"Create Episode"}
                                                buttonType={"btn-success"}/></Link>
            <table className={"table table-striped text-center"} {...getTableProps()}>
                <thead>
                {headerGroups.map((headerGroup) => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map((column) => (
                            <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                        ))}
                    </tr>
                ))}
                </thead>
                {userProfile == null ? <AllEpisodeTable/>:<React.Fragment>
                    <MyEpisodeTable userProfile={userProfile}/>
                    <UnwatchedEpisodeTable userProfile={userProfile}/></React.Fragment>}
                </table>
        </div>
    )
}

EpisodeTable.defaultProps = {
    my: false
}