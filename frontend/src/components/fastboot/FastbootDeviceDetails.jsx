import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { fetchDevice } from '../../api/fastboot';

const FastbootDeviceDetails = () => {

    const [deviceDetails, setDeviceDetails] = useState({});

    const {id} = useParams();

    useEffect(() => {
        const fetchData = async () => {
            const res = await fetchDevice(id);
            if(!res) {
                console.error("can not fetch device details.");
            } else {
                setDeviceDetails(res);
            };
        };
        fetchData();
    },[deviceDetails]); 

  return (
    <div>
        <h1>Fastboot Device Details</h1>
        <p>Id : {deviceDetails.id}</p>
        <p>Serial : {deviceDetails.serial}</p>
        <p>Codename : {deviceDetails.codename}</p>
        <p>Unlocked : {deviceDetails.unlocked}</p>
        <p>IsAbDevice : {deviceDetails.isAbDevice}</p>
        <p>Fastboot Device Status : {deviceDetails.fastbootDeviceStatus}</p>
    </div>
  )
}

export default FastbootDeviceDetails