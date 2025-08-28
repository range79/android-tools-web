
import React, { useState } from 'react'
import { flashFirmware } from '../../api/adb';

const AdbFlashForm = () => {

    const [firmwareId, setFirmwareId] = useState('');
    const [deviceId, setDeviceId] = useState(0);

    const handleFirmwareIdChange = (e) => {
        setFirmwareId(e.target.value);
    };

    const handleDeviceIdChange = (e) => {
        setDeviceId(e.target.value);
    };

    const handleFlash = async () => {
        await flashFirmware(deviceId,firmwareId)
    };

  return (
    <div>
        <h1>Adb firmware flasher</h1>
        <label>Firmware ID : </label><input type="text" value={firmwareId} onChange={(e) => handleFirmwareIdChange(e)}/>
        <label>Device ID : </label><input type="number" value={deviceId} onChange={(e) => handleDeviceIdChange(e)}/>

        <button onClick={handleFlash}>Flash Firmware</button>
    </div>
  )
}

export default AdbFlashForm