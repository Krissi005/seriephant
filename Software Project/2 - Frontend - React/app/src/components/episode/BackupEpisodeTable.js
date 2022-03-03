import {useEffect, useMemo, useState} from "react";
import {useTable} from 'react-table'
import axios from "axios";
import Button from "../Button";

export const BackupEpisodeTable = ({userProfile}) => {
    const [isLoading, setIsLoading] = useState(true);
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
        async function runEffect(){
        await axios.get("http://localhost:8080/episode/read").then(
            async res => {
                let newData = res.data
                if (userProfile != null) {
                    await axios.get("http://localhost:8080/episode/readByUserId", {params: {userId: userProfile.id}}).then(
                        (resp) => {
                            if (res.data.length !== 0 && resp.data.length !== 0) {
                                let ids = getIds(resp.data, it => it.id)
                                let test = []
                                newData.forEach(episode => {
                                    episode.watched = ids.includes(episode.id);
                                    test.push(episode);
                                })
                                setRowData(test);
                                setIsLoading(false);
                            }
                        }
                    )
                } else {
                    setRowData(newData);
                    setIsLoading(false);
                }
            })}
    }, []);

    useEffect(() => {
        console.log(rowData); //check the result here
    }, [rowData])

    console.log(rowData);

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

    if (isLoading) {
        return <div>Loading...</div>
    }

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
                                <UserButton test={row.values.watched} condition={userProfile != null && row.values.watched}
                                            userProfile={userProfile} text={"Rate"}
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

function UserButton({condition, userProfile, buttonType, text, test}) {
    console.log(test)
    if (!condition) {
        return null;
    }
    return (<td><Button id={userProfile.id} text={text} buttonType={buttonType}/></td>);
}