import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link} from "react-router-dom";

export const MyEpisodeTable = ({userProfile}) => {
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
        axios.get("http://localhost:8080/episode/readByUserId", {params: {userId: userProfile.id}}).then(
            (res) => {
                setRowData(res.data);
            }
        )

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

    const deleteEpisode = (event, episode) => {
        axios.delete("http://localhost:8080/episode/delete", {params: {"episodeId": episode.id}}).then(
            res => {
                window.alert("Succesfull :)");
                window.open("/allEpisodes", "_self");
            })
    }

    const unwatch = (event, userProfile, episode) => {
        axios.put("http://localhost:8080/user/removeEpisodes", {
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
                                    return (<td {...cell.getCellProps()}>{cell.render('Cell')}</td>)
                                })}
                                <td><Link to={"/editEpisode/" + row.values.id}><Button id={row.values.id}
                                                                                       text={"Edit"}
                                                                                       buttonType={"btn-primary"}/></Link>
                                </td>
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}
                                               onClick={(event) => deleteEpisode(event, row.values)}/></a></td>
                                <td><a><Button id={row.values.id} text={"Unwatch"} buttonType={"btn-secondary"}
                                               onClick={(event) => unwatch(event, userProfile, row.values)}/></a></td>
                                <td><Link to={"/editRating/" + row.values.id}><Button id={row.values.id} text={"Rate"}
                                                                                       buttonType={"btn-dark"}/></Link>
                                </td>
                            </tr>
                        )
                    })
                }
                </tbody>
    )
}