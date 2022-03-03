import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {Link} from "react-router-dom";

export const SeasonTable = ({userProfile}) => {

    const columnDefs = [
        {
            Header: 'Id',
            accessor: "id"
        },
        {
            Header: 'Serie',
            accessor: "serie.title"
        },
        {
            Header: 'Season Number',
            accessor: "seasonNumber"
        }
    ];

    const [rowData, setRowData] = useState([]);

    function uniqByKeepOne(data, key) {
        return [
            ...new Map(
                data.map(x => [key(x), x.season])
            ).values()]
    }

    useEffect(() => {
        axios.get("http://localhost:8080/season/read").then(
            res => {
                setRowData(res.data);
            });

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

    const deleteSeason = (event, userProfile) => {
        axios.delete("http://localhost:8080/season/delete", {params: {seasonId: userProfile.id}}).then(
            res => {
                window.alert("Succesfull :)");
                window.open("/seasons", "_self");
            })
    }

    return (<div>
            <Link to={"/createSeason"}><Button id={"create"} text={"Create Season"} buttonType={"btn-success"}/></Link>
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
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => {
                                    return (<td {...cell.getCellProps()}>{cell.render('Cell')}</td>)
                                })}
                                <td><Link to={"/editSeason/" + row.values.id}><Button id={row.values.id} text={"Edit"}
                                                                                      buttonType={"btn-primary"}/></Link>
                                </td>
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}
                                               onClick={(event) => deleteSeason(event, row.values)}/></a></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}