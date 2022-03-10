import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link} from "react-router-dom";

export const RatingTable = ({userProfile}) => {
    const [rowData, setRowData] = useState([]);

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
        }
    ];

    useEffect(() => {
        if (userProfile == null) {
            axios.get("http://localhost:8080/rating/read").then(
                res => {
                    setRowData(res.data);
                })
        } else {
            axios.get("http://localhost:8080/rating/readsByUserId", {params: {userId: userProfile.id}}).then(
                (res) => {
                    setRowData(res.data);
                }
            )
        }
    }, [userProfile]);

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

    return (<div className="container">
            <Link to={"/createRating"}><Button id={"create"} text={"Create Rating"} buttonType={"btn-success"}/></Link>
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
                <tbody {...getTableBodyProps()}>
                {
                    rows.map(row => {
                        prepareRow(row)
                        return (
                            <tr id={"tr"} {...row.getRowProps()}>
                                {row.cells.map(cell => {
                                    return (<td {...cell.getCellProps()}>{cell.render('Cell')}</td>)
                                })}
                                <td><Link to={"/editRating/" + row.values.episode.id}><Button id={row.values.id}
                                                                                              text={(row.values.rating == null ? "Rate" : row.values.rating)}
                                                                                              buttonType={"btn-dark"}/></Link>
                                </td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}