import axios from "axios";
import React, {useState, useEffect, useMemo} from "react";
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';
import {useTable} from "react-table";
import Button from "../Button";
import {Link} from "react-router-dom";

export const SerieTable = ({userProfile}) => {

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
        },
        {
            Header: 'Release Year',
            accessor: "releaseYear"
        },
        {
            Header: 'Genre',
            accessor: "genre.title"
        }
    ];

    const [rowData, setRowData] = useState([]);

    function uniqByKeepOne(data, key) {
        return [
            ...new Map(
                data.map(x => [key(x), x.season.serie])
            ).values()]
    }

    useEffect(() => {
        axios.get("http://localhost:8080/serie/read").then(
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

    const deleteSerie = (event, userProfile) => {
        axios.delete("http://localhost:8080/serie/delete", {params: {sereiId: userProfile.id}}).then(
            res => {
                window.alert("Succesfull :)");
                window.open("/series", "_self");
            })
    }

    return (<div>
            <Link to={"/createSerie"}><Button id={"create"} text={"Create Serie"} buttonType={"btn-success"}/></Link>
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
                                <td><Link to={"/editSerie/" + row.values.id}><Button id={row.values.id} text={"Edit"}
                                                                                     buttonType={"btn-primary"}/></Link>
                                </td>
                                <td><a><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger delete"}
                                               onClick={(event) => deleteSerie(event, row.values)}/></a></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}