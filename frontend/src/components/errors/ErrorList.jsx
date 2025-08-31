import React, { useEffect, useState } from 'react'
import { clearErrorList, getErrorList } from '../../api/errors';
import Sidebar from '../Sidebar';
import toast from "react-hot-toast";

const ErrorList = () => {
    const [errorList, setErrorList] = useState([])

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await getErrorList();
                setErrorList(res);
            } catch (error) {
                console.log("Something went wrong while fetching error list.");
                setErrorList([]);
            };
        };
        fetchData();
    }, []);

    const handleClearErrors = async () => {
        try {
            await clearErrorList();
            setErrorList([]);
            toast.success("All errors cleared successfully!");
        } catch (error) {
            console.log("Something went wrong while clearing error list.");
        };
    };

    return (
        <div className="flex flex-col md:flex-row min-h-screen">
            <Sidebar />
            <div className="flex-1 flex flex-col items-center justify-center py-16 px-4 bg-zinc-900 text-slate-100">
                <h1 className="text-4xl sm:text-5xl mb-12 font-extrabold bg-gradient-to-r from-purple-400 to-rose-500 text-transparent bg-clip-text text-center">
                    Error List
                </h1>

                <div className="w-10/12 md:w-7/12 lg:w-6/12 max-h-2/3 p-4 bg-zinc-700 border-2 border-zinc-500 rounded-lg overflow-auto mb-6">
                    {errorList.length > 0 ?
                        errorList.map((e, i) => (
                            <p key={i} className="mb-4">
                                {e.id} - Code: {e.errorCode} - {e.timeStamp} - {e.errorMessage}
                            </p>
                        ))
                        : <p className="text-center">No errors found.</p>
                    }
                </div>

                <button
                    className='bg-rose-600 px-4 py-2 rounded-lg font-bold'
                    onClick={handleClearErrors}
                >
                    Clear All Errors
                </button>
            </div>
        </div>
    )
}

export default ErrorList;
