import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { deleteFirmware, getFirmwareDetails } from '../../api/firmware';
import axios from 'axios';
import Sidebar from '../Sidebar';
import toast from 'react-hot-toast';

const FirmwareDetails = () => {
  const { id } = useParams();
  const [firmware, setFirmware] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchFirmwareDetails = async () => {
      setLoading(true);
      try {
        const data = await getFirmwareDetails(id);
        if (!data) {
          setError("Firmware not found");
        } else {
          setFirmware(data);
        }
      } catch (err) {
        setError("Error fetching firmware details.");
      }
      setLoading(false);
    };

    if (id) fetchFirmwareDetails();
  }, [id]);

  const handleDelete = async () => {
    try {
      const res = await deleteFirmware(id);
      if (!res) {
        setError("Firmware not found");
      } else {
        toast.success("Firmware deleted successfully!");
      }
    } catch (err) {
      setError("Error deleting firmware.");
    } finally {
      navigate("/firmware/all");
    };
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div style={{ color: 'red' }}>{error}</div>;
  if (!firmware) return <div>No firmware data available.</div>;

  return (
    <div className='flex flex-col md:flex-row min-h-screen'>
      <Sidebar />
      <div className='flex-1 p-4 sm:p-6 md:p-8 bg-zinc-900 text-white'>
        <h2 className='text-3xl sm:text-4xl md:text-5xl text-center font-bold mb-8 text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-rose-500'>
          Firmware Details
        </h2>
        <div className="details flex flex-col text-left border rounded-xl border-zinc-700 p-6 sm:p-8 md:p-10 gap-4 w-full md:w-fit mx-auto justify-center">
          <p>Firmware ID: <span className='text-zinc-300'>{firmware.id}</span></p>
          <p>Name: <span className='text-zinc-300'>{firmware.name}</span></p>
          <p>File path: <span className='text-zinc-300'>{firmware.filepath}</span></p>
          <p>Firmware Type: <span className='text-zinc-300'>{firmware.firmwareType}</span></p>
          <button onClick={handleDelete} className='bg-rose-600 w-fit rounded-full px-4 py-2 mt-4 hover:bg-rose-700 transition-colors duration-200 mx-auto'>
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};

export default FirmwareDetails;
