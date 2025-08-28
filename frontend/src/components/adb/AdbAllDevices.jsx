import React, { useEffect, useState } from 'react'
import { useQueryParam, NumberParam, StringParam } from 'use-query-params'
import { fetchAllDevices } from '../../api/adb.js';
import { useSearchParams } from 'react-router-dom';

const AdbAllDevices = () => {

    const [devices, setDevices] = useState([]);

    const [searchParams, setSearchParams] = useSearchParams();
    const page = parseInt(searchParams.get('page') || '0');


    useEffect(() => {
        const fetchData = async () => {
            const res = await fetchAllDevices(page);
            if (!res) {
                console.error("ya donmedi amk");
            }
            setDevices(prev => [...prev, ...(res.content || [])]);
        };
        fetchData();
    }, [page]);

    return (
        <div>
            <h1 >Adb All Devices</h1>
            <table className="table-auto md:table-fixed border-collapse border border-slate-100">
                <thead>
                    <tr>
                        <th className="border px-4 py-2">id</th>
                        <th className="border px-4 py-2">serial</th>
                        <th className="border px-4 py-2">codename</th>
                        <th className="border px-4 py-2">unlocked</th>
                        <th className="border px-4 py-2">androidVersion</th>
                        <th className="border px-4 py-2">status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td className="border px-4 py-2 text-center">1</td>
                        <td className="border px-4 py-2 text-center">deviceserial</td>
                        <td className="border px-4 py-2 text-center">device codename</td>
                        <td className="border px-4 py-2 text-center">true</td>
                        <td className="border px-4 py-2 text-center">12</td>
                        <td className="border px-4 py-2 text-center">Connected</td>
                    </tr>
                    <tr>
                        <td className="border px-4 py-2 text-center">1</td>
                        <td className="border px-4 py-2 text-center">deviceserial</td>
                        <td className="border px-4 py-2 text-center">device codename</td>
                        <td className="border px-4 py-2 text-center">true</td>
                        <td className="border px-4 py-2 text-center">12</td>
                        <td className="border px-4 py-2 text-center">Connected</td>
                    </tr>
                    <tr>
                        <td className="border px-4 py-2 text-center">1</td>
                        <td className="border px-4 py-2 text-center">deviceserial</td>
                        <td className="border px-4 py-2 text-center">device codename</td>
                        <td className="border px-4 py-2 text-center">true</td>
                        <td className="border px-4 py-2 text-center">12</td>
                        <td className="border px-4 py-2 text-center">Connected</td>
                    </tr>
                    {/* {devices.map((device) => {
                        return (
                            // <tr key={device.id}>
                            //     <td className="border px-4 py-2 text-center">{device.id}</td>
                            //     <td className="border px-4 py-2 text-center">{device.serial}</td>
                            //     <td className="border px-4 py-2 text-center">{device.codename}</td>
                            //     <td className="border px-4 py-2 text-center">{device.androidVersion}</td>
                            //     <td className="border px-4 py-2 text-center">{device.status}</td>
                            // </tr>

                            
                        )
                    })} */}
                </tbody>
            </table>

            {/*Todo : pagination*/}

            <p>buraya pagination gelcek</p>
            <button>geri</button>
            <p>sayfa sayisi</p>
            <button>ileri</button>
        </div>
    )
}

export default AdbAllDevices