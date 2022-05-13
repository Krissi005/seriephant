import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link, useNavigate} from "react-router-dom";

export const WatchedEpisodeTable = ({userProfile, reload, state}) => {
    const [rowData, setRowData] = useState([]);
    const navigate = useNavigate()

    const columnDefs = [
        {
            Header: 'Id',
            accessor: "episode.id"
        },
        {
            Header: 'Serie',
            accessor: "episode.season.serie.title"
        },
        {
            Header: 'Season',
            accessor: "episode.season.seasonNumber"
        },
        {
            Header: 'Episode Number',
            accessor: "episode.episodeNumber"
        },
        {
            Header: 'Title',
            accessor: "episode.title"
        },
        {
            Header: 'Release Date',
            accessor: "episode.releaseDate"
        },
        {
            Header: 'Rating Average',
            accessor: "ratingAverage"
        },
        {
            Header: 'Rated by',
            accessor: "numberOfRatings"
        }
    ];

    useEffect(() => {
        if (userProfile == null) {
            axios.get("http://localhost:8080/rating/read").then(
                res => {
                    setRowData(res.data);
                })
        } else {
            axios.get("http://localhost:8080/rating/readRatingsByUser", {params: {userId: userProfile.id}}).then(
                (res) => {
                    setRowData(res.data);
                }
            )
        }
    }, []);

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

    const deleteEpisode = (event, episodeId) => {
        axios.delete("http://localhost:8080/episode/delete", {params: {"episodeId": episodeId}}).then(
            res => {
                window.alert("Succesfull :)");
                navigate("/series");
            })
    }

    const unwatch = (event, userProfile, episodeId) => {
        axios.put("http://localhost:8080/user/removeEpisodes", {
                "id": userProfile.id,
                "watchedEpisodes": [
                    {
                        "id": episodeId
                    }
                ]
            }
        ).then(res => {
            if (res.status === 200) {
                reload(state);
                navigate("/myEpisodes");
                window.alert("Successful :)");
            } else {
                window.alert("Failed :(");
            }
        })
    }

    return (<tbody {...getTableBodyProps()}>
        {
            rows.map(row => {
                prepareRow(row)
                return (
                    <tr {...row.getRowProps()}>
                        {row.cells.map(cell => {
                            return (<td {...cell.getCellProps() }>{cell.render('Cell')}</td>)
                        })}
                        <td><Link
                            to={"/editEpisode/" + row.cells[0].value}><Button
                            id={row.cells[0].value}
                            text={"Edit"}
                            buttonType={"btn-primary"}/></Link>
                        </td>
                        <td><a><Button id={row.cells[0].value} text={"Delete"} buttonType={"btn-danger delete"}
                                       onClick={(event) => deleteEpisode(event, row.cells[0].value)}/></a></td>
                        <td><a><Button id={row.cells[0].value}
                                       text={"Unwatch"} buttonType={"btn-secondary"}
                                       onClick={(event) => unwatch(event, userProfile, row.cells[0].value)}/></a>
                        </td>
                        <td><Link to={"/editRating/" + row.cells[0].value}><Button
                            id={row.cells[0].value}
                            text={"Rate"}
                            buttonType={"btn-dark"}/></Link>
                        </td>
                    </tr>
                )
            })
        }
        </tbody>
    )
}