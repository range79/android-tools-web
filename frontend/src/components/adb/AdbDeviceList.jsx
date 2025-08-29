import React, { useEffect, useState } from 'react'
import { saveDevice, scanDevices } from "../../api/adb.js";
import Sidebar from '../Sidebar.jsx';


const AdbDeviceList = () => {

    const [adbDeviceList, setAdbDeviceList] = useState([]);

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

    const handleSave = async (id) => {
        try {
            await saveDevice(id);
        } catch (error) {
            console.error("Save device error :", error);
        };
    };

    return (
        <div className="flex flex-col md:flex-row min-h-screen">
            <Sidebar />
            <div className="flex-1 py-16 px-4 md:px-16 bg-zinc-900 text-slate-100 font-sans">
                <h1 className="text-4xl sm:text-5xl md:text-6xl text-center mb-12 font-extrabold tracking-tight bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text">
                    ADB Scan Devices
                </h1>
                <div className="rounded-lg shadow-2xl backdrop-blur-sm bg-zinc-800/50 border border-zinc-700 overflow-auto transition-all duration-300 max-w-[500px] mx-auto">
                    <table className='min-w-full table-auto border-collapse text-sm'>
                        <thead>
                            <tr className='bg-zinc-700/70 text-zinc-300 uppercase tracking-wider'>
                                <th className="px-4 py-3 text-left font-medium">ID</th>
                                <th className="px-4 py-3 text-left font-medium">Serial Number</th>
                                <th className="px-4 py-3 text-left font-medium">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {adbDeviceList.length > 0 ? (
                                adbDeviceList.map((device, index) => (
                                    <tr key={index} className="border-t border-zinc-700 hover:bg-zinc-700/50 transition-colors duration-200">
                                        <td className="px-4 py-3 text-zinc-300">{index + 1}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device}</td>
                                        <td><button className='bg-green-600 text-slate-300 text-center rounded-lg py-1 px-2 w-20' onClick={() => handleSave(device)}>Save</button></td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="2" className="px-4 py-3 text-center text-zinc-400">
                                        No ADB Devices found.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default AdbDeviceList