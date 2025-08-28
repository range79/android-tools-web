import React, { useEffect, useState } from 'react';
import { deleteFirmware, getAllFirmwares } from '../../api/firmware';
import Sidebar from '../Sidebar';
import { useNavigate } from 'react-router-dom';
import { getDeviceList } from '../../api/adb';
import { flashFastbootDevice, getFastbootDeviceList, getPartitionOptions } from '../../api/fastboot';
import axios from 'axios';


const FirmwareList = () => {

  const [adbDeviceId, setAdbDeviceId] = useState('');
  const [fastbootDeviceId, setFastbootDeviceId] = useState('');
  const [firmwareList, setFirmwareList] = useState([]);
  const [adbDeviceList, setAdbDeviceList] = useState([]);
  const [fastbootDeviceList, setFastbootDeviceList] = useState([]);
  const [flashType, setFlashType] = useState('');

  const [selectedDeviceId, setSelectedDeviceId] = useState('');
  const [partitionOptions, setPartitionOptions] = useState([]);
  
  const navigate = useNavigate();

  // Fetch firmware list on initial component mount
  useEffect(() => {
    const fetchFirmwares = async () => {
      try {
        const data = await getAllFirmwares();
        if (data && data.content) {
          setFirmwareList(data.content);
        } else {
          setFirmwareList([]);
        }
      } catch (error) {
        console.error("Failed to fetch firmwares:", error);
        setFirmwareList([]);
      }
    };
    fetchFirmwares();
  }, []);

  // Fetch ADB device list on initial component mount
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await getDeviceList();
        setAdbDeviceList(res || []);
      } catch (error) {
        console.error("Failed to fetch ADB devices:", error);
        setAdbDeviceList([]);
      }
    };
    fetchData();
  }, []);

  // Fetch Fastboot device list on initial component mount
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await getFastbootDeviceList();
        setFastbootDeviceList(res || []);
      } catch (error) {
        console.error("Failed to fetch Fastboot devices:", error);
        setFastbootDeviceList([]);
      }
    };
    fetchData();
  }, []);

  // Fetch partition options on initial component mount
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await getPartitionOptions();
        // API'den gelen verinin dizi olduğundan emin olun
        setPartitionOptions(Array.isArray(res) ? res : []);
      } catch (error) {
        console.error("Failed to fetch partition options:", error);
        setPartitionOptions([]);
      }
    }
    fetchData();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteFirmware(id);
      setFirmwareList(firmwareList.filter(f => f.id !== id));
    } catch (error) {
      console.error("An error occured while deleting firmware.", error);
    }
  };

  const handleDetails = (id) => {
    try {
      navigate(`/firmware/${id}`);
    } catch (error) {
      console.error("An error occured while navigating to details.", error);
    }
  };

  const handleFlash = async (firmwareId) => {
    let deviceIdToFlash;
    if (flashType === 'ADB') {
      deviceIdToFlash = adbDeviceId;
    } else if (flashType === 'FASTBOOT') {
      deviceIdToFlash = fastbootDeviceId;
      await flashFastbootDevice(deviceIdToFlash,firmwareId,partitionOptions);
    };

    if (!deviceIdToFlash || flashType === '') {
      alert("Please dont leave something empty.");
      return;
    }

    
    
    // This is where you would call your API to perform the flash operation.
    console.log(`Flashing firmware ${firmwareId} to device ${deviceIdToFlash} via ${flashType}`);
    // Example API call (uncomment when your API function is ready):
    // try {
    //   const result = await flashFirmware(deviceIdToFlash, firmwareId, flashType);
    //   console.log("Flash result:", result);
    //   alert("Firmware flash successful!");
    // } catch (error) {
    //   console.error("Flash error:", error);
    //   alert("Firmware flash failed. Check console for details.");
    // }
  };

  // flashType ve selectedDeviceId'nin senkronize kalmasını sağlar
  useEffect(() => {
    if (flashType === 'ADB') {
      setSelectedDeviceId(adbDeviceId);
    } else if (flashType === 'FASTBOOT') {
      setSelectedDeviceId(fastbootDeviceId);
    } else {
      setSelectedDeviceId('');
    }
  }, [flashType, adbDeviceId, fastbootDeviceId]);
  
  return (
    <div className="flex flex-col md:flex-row min-h-screen">
      <Sidebar />
      <div className="flex-1 py-16 px-4 md:px-16 bg-zinc-900 text-slate-100 font-sans">
        <h1 className="text-4xl sm:text-5xl md:text-6xl text-center mb-12 font-extrabold tracking-tight bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text">
          Firmware List
        </h1>
  
        {firmwareList.length === 0 ? (
          <div className="text-center text-slate-400 text-lg">
            <p>No firmware's found.</p>
          </div>
        ) : (
          <div className="overflow-x-auto rounded-xl shadow-2xl backdrop-blur-sm bg-zinc-800/50 border border-zinc-700 transition-all duration-300">
            <table className="min-w-full table-auto border-collapse text-sm">
              <thead>
                <tr className="bg-zinc-700/70 text-zinc-300 uppercase tracking-wider">
                  <th className="px-4 py-3 text-left font-medium">ID</th>
                  <th className="px-4 py-3 text-left font-medium">Name</th>
                  <th className="px-4 py-3 text-left font-medium">File Path</th>
                  <th className="px-4 py-3 text-left font-medium">Firmware Type</th>
                  <th className="px-4 py-3 text-center font-medium">Actions</th>
                </tr>
              </thead>
              <tbody>
                {firmwareList.map((firmware, index) => (
                  <tr key={index} className="border-t border-zinc-700 hover:bg-zinc-700/50 transition-colors duration-200">
                    <td className="px-4 py-3">{firmware.id}</td>
                    <td className="px-4 py-3">{firmware.name}</td>
                    <td className="px-4 py-3 break-all">{firmware.filepath}</td>
                    <td className="px-4 py-3">{firmware.firmwareType}</td>
                    <td className="px-4 py-3 text-center">
                      <div className="actions flex flex-col gap-2">
                        <button
                          onClick={() => handleDelete(firmware.id)}
                          className="bg-rose-600 text-white rounded-md px-4 py-2 hover:bg-rose-700 transition-colors duration-200 shadow-md text-xs"
                        >
                          Delete
                        </button>
                        <button
                          onClick={() => handleDetails(firmware.id)}
                          className="bg-blue-600 text-white rounded-md px-4 py-2 hover:bg-blue-700 transition-colors duration-200 shadow-md text-xs"
                        >
                          Details
                        </button>
                      </div>
                      <div className="flash flex flex-col my-4 gap-2 items-stretch">
                        <select 
                          className='border border-zinc-700 rounded-lg p-1 text-zinc-300 bg-zinc-800 focus:outline-none focus:ring-2 focus:ring-purple-400' 
                          value={flashType} 
                          onChange={(e) => {
                            setFlashType(e.target.value);
                            setAdbDeviceId('');
                            setFastbootDeviceId('');
                          }}
                        >
                          <option value="" disabled>Select flash type.</option>
                          <option value="ADB">ADB</option>
                          <option value="FASTBOOT">FASTBOOT</option>
                        </select>
                        
                        {/* Conditionally render ADB or Fastboot device list */}
                        {flashType === 'ADB' && (
                          <select 
                            className='border border-zinc-700 rounded-lg p-1 text-zinc-300 bg-zinc-800 focus:outline-none focus:ring-2 focus:ring-purple-400' 
                            value={adbDeviceId}
                            onChange={(e) => setAdbDeviceId(e.target.value)}
                          >
                            <option value="" disabled>Select ADB device.</option>
                            {/* Check if adbDeviceList is an array before mapping */}
                            {Array.isArray(adbDeviceList) && adbDeviceList.map((device) => (
                              <option key={device.id} value={device.id}>
                                {device.id}
                              </option>
                            ))}
                          </select>
                        )}

                        {flashType === 'FASTBOOT' && (
                          <>
                            <select 
                              className='border border-zinc-700 rounded-lg p-1 text-zinc-300 bg-zinc-800 focus:outline-none focus:ring-2 focus:ring-purple-400' 
                              value={fastbootDeviceId}
                              onChange={(e) => setFastbootDeviceId(e.target.value)}
                            >
                              <option value="" disabled>Select FASTBOOT device.</option>
                              {/* Check if fastbootDeviceList is an array before mapping */}
                              {Array.isArray(fastbootDeviceList) && fastbootDeviceList.map((device) => (
                                <option key={device.id} value={device.id}>
                                  {device.id}
                                </option>
                              ))}
                            </select>
                            
                            <select 
                              className='border border-zinc-700 rounded-lg p-1 text-zinc-300 bg-zinc-800 focus:outline-none focus:ring-2 focus:ring-purple-400' 
                            >
                              <option value="" disabled>Select Partition</option>
                              {Array.isArray(partitionOptions) && partitionOptions.map((partition, index) => (
                                <option key={index} value={partition}>{partition}</option>
                              ))}
                            </select>
                          </>
                        )}
                        
                        <button
                          onClick={() => handleFlash(firmware.id)}
                          className="bg-green-600 text-white rounded-md px-4 py-2 hover:bg-green-700 transition-colors duration-200 shadow-md text-xs md:mt-2 "
                        >
                          Flash
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default FirmwareList;