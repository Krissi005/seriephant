import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link} from "react-router-dom";
import {MyEpisodeTable} from "./MyEpisodeTable";

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
        }
    ];

    useEffect(() => {
        if (userProfile == null) {//
            axios.get("http://localhost:8080/episode/read").then(
                res => {
                    setRowData(res.data);
                })
        } else {
            if (!my) {
                axios.get("http://localhost:8080/episode/readNotByUserId", {params: {userId: userProfile.id}}).then(
                    (res) => {
                        setRowData(res.data);
                    }
                )
            } else {
                axios.get("http://localhost:8080/episode/readByUserId", {params: {userId: userProfile.id}}).then(
                    (res) => {
                        setRowData(res.data);
                    }
                )
            }

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
                window.open("/allEpisodes", "_self");
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
                <MyEpisodeTable userProfile={userProfile}/>
                <tbody {...getTableBodyProps()}>
                {
                    rows.map(row => {
                        prepareRow(row)
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => {
                                    return (<td {...cell.getCellProps()}>{cell.render('Cell')}</td>)
                                })}
                                <td><Link to={"/editEpisode/" + row.values.id}><Button id={row.values.id}
                                                                                       text={"Edit"}
                                                                                       buttonType={"btn-primary"}/></Link>
                                </td>
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}
                                               onClick={(event) => deleteEpisode(event, row.values)}/></a></td>
                                {(userProfile == null) ? <td></td> :
                                    <td><a><Button id={row.values.id} text={"Watch"} buttonType={"btn-success"}
                                                   onClick={(event) => watchEpisode(event, userProfile, row.values)}/></a>
                                    </td>}
                                <td></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}

EpisodeTable.defaultProps = {
    my: false
}