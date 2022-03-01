import {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";

export const EpisodeTable = ({userProfile, onClick}) => {
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

    function getIds(data, key) {
        return [
            ...new Map(
                data.map(x => [key(x), x])
            ).keys()]
    }

    useEffect(() => {
        if (userProfile == null) {
        axios.get("http://localhost:8080/episode/read").then(
            res => {
                setRowData(res.data);
            })
        } else {
            axios.get("http://localhost:8080/episode/readNotByUserId", {params: {userId: userProfile.id}}).then(
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

    return (<div>
            <Button id={"create"} text={"Create Episode"} buttonType={"btn-success"}/>
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
                                {userProfile == null?<td></td>:<td><Button id={userProfile.id} text={"Watch"} buttonType={"btn-dark"} onClick={(event)=>onClick(event, row.values)}/></td>};
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}