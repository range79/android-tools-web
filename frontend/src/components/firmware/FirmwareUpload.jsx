import React, { useState } from 'react';
import { uploadFirmware } from "../../api/firmware.js";
import Sidebar from '../Sidebar.jsx';

const FirmwareUpload = () => {
  const [firmwareType, setFirmwareType] = useState('');
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleUpload = async () => {
    if (!firmwareType || !file) {
      alert("Please select firmware type and file before uploading.");
      return;
    }
    setLoading(true);
    try {
      await uploadFirmware(firmwareType, file);
      alert("Upload successful!");
      setFirmwareType('');
      setFile(null);
    } catch (error) {
      alert("Upload failed!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col md:flex-row min-h-screen">
      <Sidebar />
      <div className='bg-zinc-900 w-full min-h-screen'>
        <div className="flex flex-col p-16 items-center">
          <h1 className='text-4xl sm:text-5xl md:text-6xl lg:text-7xl text-center font-extrabold tracking-tight bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text'>Firmware Upload</h1>
          <select
            name="firmwareType"
            id="firmwareType"
            value={firmwareType}
            onChange={(e) => setFirmwareType(e.target.value)}
            className='border border-zinc-700 p-2 rounded-lg text-slate-300 mt-8'
          >
            <option value="" disabled>
              Select firmware type
            </option>
            <option value="ADB_ROM">ADB_ROM</option>
            <option value="UPDATE_ZIP">UPDATE_ZIP</option>
            <option value="FASTBOOT_ROM">FASTBOOT_ROM</option>
            <option value="RECOVERY_IMAGE">RECOVERY_IMAGE</option>
          </select>

            <label className='text-slate-300 my-4'>Select ROM file :</label>
            <input type="file" onChange={(e) => setFile(e.target.files[0])} className='border border-zinc-700 text-slate-300 p-2 rounded-lg bg-zinc-800 max-w-screen '/>


          <button onClick={handleUpload} disabled={loading} className='my-8 bg-blue-500 rounded-2xl py-1 px-2 text-slate-200'>
            {loading ? "Uploading..." : "Upload Firmware"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default FirmwareUpload;
