import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";
import {useNavigate} from "react-router-dom";

export const GenreTable = ({userProfile}) => {

    const columnDefs = [
        {
            Header: 'Id',
            accessor: "id"
        },
        {
            Header: 'Title',
            accessor: "title"
        },
        {
            Header: 'Description',
            accessor: "description"
        }
    ];

    const [rowData, setRowData] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        axios.get("http://localhost:8080/genre/read").then(
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

    const deleteGenre = (event, userProfile) => {
        axios.delete("http://localhost:8080/genre/delete", {params: {genreId: userProfile.id}}).then(
            res => {
                window.alert("Succesfull :)");
                navigate("/series");
            })
    }

    return (<div className="container">
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
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}
                                               onClick={(event) => deleteGenre(event, row.values)}/></a></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}