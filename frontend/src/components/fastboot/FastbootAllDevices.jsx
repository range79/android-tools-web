import React, { useEffect, useState } from 'react'
import { useQueryParam, NumberParam, StringParam } from 'use-query-params'
import { fetchAllDevices } from '../../api/fastboot';
import { useSearchParams } from 'react-router-dom';

const FastbootAllDevices = () => {
    
    const [devices, setDevices] = useState([]);

    const [searchParams, setSearchParams] = useSearchParams();
    const page = parseInt(searchParams.get('page') || '0');

    
    useEffect(() => {
        const fetchData = async () => {
            const res = await fetchAllDevices(page);
            if(!res) {
                console.error("ya donmedi amk");
            }
            setDevices(prev => [...prev, ...(res.content || [])]);
        };
        fetchData();
    },[page]);

  return (
    <div>
        <h1>FastbootAllDevice</h1>
        <table border={1}>
            <thead>
                <tr>
                    <th>id</th>
                    <th>serial</th>
                    <th>codename</th>
                    <th>unlocked</th>
                    <th>isAbDevice</th>
                    <th>fastbootDeviceStatus</th>
                </tr>
            </thead>
            <tbody>
                {devices.map((device) => {
                    return (
                        <tr key={device.id}>
                            <td>{device.id}</td>
                            <td>{device.serial}</td>
                            <td>{device.codename}</td>
                            <td>{device.unlocked.toString()}</td>
                            <td>{device.isAbDevice.toString()}</td>
                            <td>{device.fastbootDeviceStatus}</td>
                        </tr>
                    )
                })}
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

export default FastbootAllDevices