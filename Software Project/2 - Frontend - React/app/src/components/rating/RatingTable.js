import {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";

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
            axios.get("http://localhost:8080/rating/readByUserId", {params: {userId: userProfile.id}}).then(
                (res) => {
                    setRowData(res.data);
                }
            )
        }
    }, [userProfile]);

    console.log(rowData)

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
                            <tr id={"tr"} {...row.getRowProps()}>
                                {row.cells.map(cell => {
                                    return (<td {...cell.getCellProps()}>{cell.render('Cell')}</td>)
                                })}
                                <td><Button id={row.values.id} text={"Edit"} buttonType={"btn-primary"}/></td>
                                <td><Button id={row.values.id} text={"Delete"} buttonType={"btn-danger"}/></td>
                                <UserButton condition={userProfile != null} userProfile={row.values} text={"Watch"}
                                            buttonType={"btn-dark"}/>
                                <UserButton condition={row.values.watched}
                                            userProfile={row.values} text={"Rate"}
                                            buttonType={"btn-dark"}/>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}

function UserButton({condition, userProfile, buttonType, text}) {
    console.log(text)
    if (condition) {
        return null;
    }
    return (<td><Button id={userProfile.id} text={text} buttonType={buttonType}/></td>);
}