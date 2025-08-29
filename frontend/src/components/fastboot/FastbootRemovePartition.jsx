import React, { useEffect, useState } from 'react'
import Sidebar from '../Sidebar'
import { getFastbootDeviceList, getPartitionOptions, removePartition } from '../../api/fastboot';

const FastbootRemovePartition = () => {

  const [deviceList, setDeviceList] = useState([]);
  const [partitionList, setPartitionList] = useState([]);
  const [selectedDevice, setSelectedDevice] = useState(0);
  const [selectedPartition, setSelectedPartition] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      const res = await getFastbootDeviceList();
      if (res) {
        setDeviceList(res);
      } else {
        console.error("can't get device list.");
      };
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const res = await getPartitionOptions();
      if (res) {
        setPartitionList(res);
      } else {
        console.error("can't get partition list.");
      }
    };
    fetchData();
  }, []);


  const handleSelectedDeviceChange = (e) => {
    setSelectedDevice(e.target.value);
  };

  const handleSelectedPartitionChange = (e) => {
    setSelectedPartition(e.target.value);
  };

  const handleRemove = async (device,partition) => {
    if(selectedDevice === 0 || selectedPartition === '') {
      alert("Please do not leave anything empty.");
    };
    try {
      await removePartition(device,partition);
    } catch (error) {
      console.error("failed to remove partition.");
    };
  };

  return (
    <div className="flex flex-col md:flex-row min-h-screen">
      <Sidebar />
      <div className='flex-1 py-16 px-4 md:px-16 min-h-screen w-full bg-zinc-900 text-slate-100 font-sans'>
        <h1 className="text-4xl sm:text-5xl md:text-6xl text-center mb-12 font-extrabold tracking-tight bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text">
          Fastboot Remove Partition
        </h1>
        <div className="flex mx-auto max-w-fit items-center gap-8 flex-wrap">
          <div className="flex flex-col">
            <div className="flex">
              <div className="flex flex-col items-center">
                <label>Select Device :</label>
                <select className='border border-zinc-700 py-1 px-2 rounded-lg my-2 text-slate-300' defaultValue={"x"} value={selectedDevice} onChange={(e) => handleSelectedDeviceChange(e)}>
                  <option value="x" disabled>Select device.</option>
                  {Array.isArray(deviceList) && deviceList.map((device) => (
                    <option value={device} key={device}>{device}</option>
                  ))}
                </select>
              </div>
              <div className="flex flex-col items-center ml-2">
                <label>Select Partition :</label>
                <select className='border border-zinc-700 py-1 px-2 rounded-lg my-2 text-slate-300' defaultValue={"x"} value={selectedPartition} onChange={(e) => handleSelectedPartitionChange(e)}>
                  <option value="x" disabled>Select partition.</option>
                  {Array.isArray(partitionList) && partitionList.map((partition) => (
                    <option value={partition} key={partition}>{partition}</option>
                  ))}
                </select>
              </div>
            </div>

            <button className='bg-rose-600 rounded-full py-1 px-2 w-fit mx-auto' onClick={() => handleRemove(selectedDevice,selectedPartition)}>Remove</button>

          </div>
        </div>
      </div>
    </div>
  )
}

export default FastbootRemovePartition