import React, { useEffect, useState } from 'react';
import { fetchAllDevices } from '../../api/fastboot.js';
import { useSearchParams } from 'react-router-dom';
import Sidebar from '../Sidebar.jsx';

const AdbAllDevices = () => {
    const [devices, setDevices] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    const [searchParams, setSearchParams] = useSearchParams();
    const page = parseInt(searchParams.get('page') || '0');

    useEffect(() => {
        const fetchData = async () => {
            const res = await fetchAllDevices(page);
            if (!res) {
                console.error("Error fetching all devices at FastbootAllDevices.");
            } else if (res && res.content) {
                setDevices(res.content);
                if (res.page) {
                    setTotalPages(res.page.totalPages);
                }
            }
        };
        fetchData();
    }, [page]);

    const handlePrev = () => {
        if (page > 0) {
            setSearchParams({ page: page - 1 });
        }
    };

    const handleNext = () => {
        if (page < totalPages - 1) {
            setSearchParams({ page: page + 1 });
        }
    };

    return (
        <div className="flex flex-col md:flex-row min-h-screen">
            <Sidebar />
            <div className="flex-1 py-16 px-4 md:px-16 bg-zinc-900 text-slate-100 font-sans">
                <h1 className="text-4xl sm:text-5xl md:text-6xl text-center mb-12 font-extrabold tracking-tight bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text">
                    Fastboot All Devices
                </h1>
                <div className="rounded-lg shadow-2xl backdrop-blur-sm bg-zinc-800/50 border border-zinc-700 overflow-auto transition-all duration-300 mx-auto">
                    <table className="min-w-full table-auto border-collapse text-sm">
                        <thead>
                            <tr className="bg-zinc-700/70 text-zinc-300 uppercase tracking-wider">
                                <th className="px-4 py-3 text-start font-medium">id</th>
                                <th className="px-4 py-3 text-start font-medium">serial</th>
                                <th className="px-4 py-3 text-start font-medium">codename</th>
                                <th className="px-4 py-3 text-start font-medium">unlocked</th>
                                <th className="px-4 py-3 text-start font-medium">androidVersion</th>
                                <th className="px-4 py-3 text-start font-medium">status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {devices.length > 0 ? (
                                devices.map((device) => (
                                    <tr
                                        key={device.id}
                                        className="border-t border-zinc-700 hover:bg-zinc-700/50 transition-colors duration-200"
                                    >
                                        <td className="px-4 py-3 text-zinc-300">{device.id}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device.serial}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device.codename}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device.unlocked ? 'true' : 'false'}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device.androidVersion}</td>
                                        <td className="px-4 py-3 text-zinc-300">{device.status}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="6" className="text-center py-4 text-zinc-400">
                                        No devices found.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>

                {/* Pagination */}
                <div className="flex justify-center items-center gap-4 mt-6">
                    <button
                        onClick={handlePrev}
                        disabled={page === 0}
                        className="bg-gray-700 px-4 py-2 rounded-lg disabled:opacity-50"
                    >
                        Back
                    </button>
                    <span className="text-lg font-semibold">
                        Page: {page + 1} / {totalPages}
                    </span>
                    <button
                        onClick={handleNext}
                        disabled={page >= totalPages - 1}
                        className="bg-gray-700 px-4 py-2 rounded-lg disabled:opacity-50"
                    >
                        Next
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AdbAllDevices;
