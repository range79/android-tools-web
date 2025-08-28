import React, { useEffect, useState } from 'react'
import {scanDevices} from "../../api/adb.js";

const AdbDeviceList = () => {

    const [AdbDeviceList, setAdbDeviceList] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await scanDevices();
                setAdbDeviceList(res.data);
            } catch (err) {
                console.error("Fastboot fetch error:", err);
            }
        };
        fetchData();
    }, []);

    return (
        <div>
            <h1>Device list : </h1>
            <table border={1}>
                <tbody>
                    <tr>
                        <th>id</th>
                        <th>serial id</th>
                    </tr>
                <tr>
                    {AdbDeviceList.map((device,index) => {
                        return (
                            <div className="list">
                                <td>{index}</td>
                                <td>{device}</td>
                            </div>
                        )
                    })}
                </tr>
                </tbody>
            </table>
            
        </div>
    )
}

export default AdbDeviceList