import React, { useState, useRef } from 'react';
import Sidebar from '../Sidebar.jsx';
import toast from 'react-hot-toast';
import { backupToLocal } from "../../api/backup.js";

const BackupToLocal = () => {
    const [file, setFile] = useState(null);
    const [loading, setLoading] = useState(false);
    const fileInputRef = useRef(null);

    const handleUpload = async () => {
        if (!file) {
            toast.error("Please select a file first!");
            return;
        }

        setLoading(true);
        try {
            await backupToLocal(file);
            toast.success("Upload successful! Refresh the page.");

            setFile(null);
            if (fileInputRef.current) {
                fileInputRef.current.value = "";
            }
        } catch (error) {
            console.error(error);
            toast.error("Upload failed!");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="flex flex-col md:flex-row min-h-screen">
            <Sidebar />

            <div className="bg-zinc-900 w-full min-h-screen">
                <div className="flex flex-col p-16 items-center">
                    <h1 className="text-4xl sm:text-5xl md:text-6xl lg:text-7xl
                        text-center font-extrabold tracking-tight
                        bg-linear-to-r from-purple-400 to-rose-500
                        text-transparent bg-clip-text">
                        File Upload
                    </h1>

                    <label className="text-slate-300 my-4">
                        Select File Upload to Your computer:
                    </label>

                    <input
                        ref={fileInputRef}
                        type="file"
                        accept=".zip,.img,.bin"
                        disabled={loading}
                        onChange={(e) => setFile(e.target.files[0])}
                        className="border border-zinc-700 text-slate-300 p-2 rounded-lg bg-zinc-800 max-w-screen disabled:opacity-50"
                    />

                    <button
                        onClick={handleUpload}
                        disabled={loading}
                        className={`my-8 rounded-2xl py-2 px-4 text-slate-200 transition
                            ${loading
                            ? "bg-gray-500 cursor-not-allowed"
                            : "bg-blue-500 hover:bg-blue-600"}
                        `}
                    >
                        {loading ? "Uploading..." : "Upload File"}
                    </button>
                </div>
            </div>
        </div>
    );
};

export default BackupToLocal;
