import React, {useEffect, useMemo, useState} from "react";
import axios from "axios";
import {useTable} from "react-table";
import Button from "../Button";
import {Link, useNavigate} from "react-router-dom";

export const Users = ({userProfile, choose, reset, edit}) => {

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
    const navigate = useNavigate()

    useEffect(() => {
        axios.get("http://localhost:8080/user/read").then(
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

    const deleteUser = (event, userProfile) => {
        axios.delete("http://localhost:8080/user/delete", {params: {userId: userProfile.id}}).then(
            res => {
                window.alert("Succesfull :)");
               navigate("/users");
            })
    }

    return (<div className="container">
            <Link to={"/createUser"}><Button id={"create"} text={"Create User"} buttonType={"btn-success"}/></Link>
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
                                <td><Link to={"/editUser/"+row.values.id}><Button id={row.values.id} text={"Edit"} buttonType={"btn-primary"}/></Link></td>
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}  onClick={(event) => deleteUser(event, row.values)}/></a></td>
                                {(userProfile!=null && userProfile.id === row.values.id) ? <td><Button id={row.values.id} text={"Unchoose"} buttonType={"btn-danger"} onClick={(event) => choose(event, null)}/></td>
                                    :<td><a><Button id={row.values.id} text={"Choose"} buttonType={"btn-success"} onClick={(event) => choose(event, row.values)}/></a></td>
                                    }
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}