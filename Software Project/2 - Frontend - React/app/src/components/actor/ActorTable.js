import React, {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";

export const ActorTable = ({userProfile}) => {

    const columnDefs = [
        {
            Header: 'Id',
            accessor: "id"
        },
        {
            Header: 'First Name',
            accessor: "firstName"
        },
        {
            Header: 'Last Name',
            accessor: "lastName"
        }
    ];

    const [rowData, setRowData] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/actor/read").then(
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

    const deleteActor = (event, actorId) => {
        axios.delete("http://localhost:8080/actor/delete", {params: {actorId: actorId}}).then(
            res => {
                window.alert("Succesfull :)");
                window.location.reload("/actors");
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
                                               onClick={(event) => deleteActor(event, row.values.id)}/></a></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}