import {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";

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

    useEffect(() => {if (userProfile == null) {
        axios.get("http://localhost:8080/season/read").then(
            res => {
                setRowData(res.data);
            });
    } else {
        axios.get("http://localhost:8080/episode/readByUserId", {params: {userId: userProfile.id}}).then(
            res => {
                setRowData(uniqByKeepOne(res.data, it=>it.season.id));
            })
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

    const create = (event, serieId) => {

    }

    return (<div>
            <Button id={"create"} text={"Create Season"} buttonType={"btn-success"}/>
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
                                <td><Button id={row.values.id} text={"Edit"} buttonType={"btn-primary"}/></td>
                                <td><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger"}/></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}