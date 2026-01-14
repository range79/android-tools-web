import React, { useState } from 'react';
import {href} from "react-router-dom";


const menuItems = {
  'Firmware': [
    { name: 'Firmware List', href: '/firmware/all' },
    { name: 'Upload Firmware', href: '/firmware/upload' },
  ],
  'ADB': [
    { name: 'Scan ADB Devices', href: '/adb/scanDevices' },
    { name: 'List ADB Devices', href: '/adb/devices/all' },
  ],
  'Fastboot': [
    { name: 'Scan Fastboot Devices', href: '/fastboot/scanDevices' },
    { name: 'List Fastboot Devices', href: '/fastboot/device/all' },
    { name: 'Remove Partition', href: '/fastboot/removePartition' }
  ],
  'Errors': [
    { name: 'Error List', href: '/error/list' }
  ],
  'Backup':[
    {name: 'Backup to pc', href: '/backup/local'},
    {name: 'Backup to dropbox', href:'/backup/dropbox'}
  ]

};

const Sidebar = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const [openDropdown, setOpenDropdown] = useState(null); 

  const handleDropdownToggle = (key) => {
    setOpenDropdown(openDropdown === key ? null : key);
  };

  return (
    <>
      {}
      <button
        onClick={() => setIsSidebarOpen(true)}
        className="fixed top-4 left-4 z-50 p-2 rounded-full md:hidden bg-zinc-800 text-white shadow-lg"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M4 6h16M4 12h16m-7 6h7"
          />
        </svg>
      </button>

      
      <div
        onClick={() => setIsSidebarOpen(false)}
        className={`fixed inset-0 z-40 bg-black/50 md:hidden transition-opacity duration-300 ${
          isSidebarOpen ? 'opacity-100' : 'opacity-0 pointer-events-none'
        }`}
      />

      
      <div
        className={`fixed inset-y-0 left-0 z-50 w-64 transform transition-transform duration-300 ease-in-out md:translate-x-0 md:static ${
          isSidebarOpen ? 'translate-x-0' : '-translate-x-full'
        } bg-zinc-900 p-6 shadow-xl flex flex-col border-r`}
      >
        <div className="flex flex-col">
          
          <h2 className="text-2xl font-bold mb-8 text-white text-center">
            Android Tools Web
          </h2>

          
          <nav>
            <ul className="space-y-4">
              {Object.keys(menuItems).map((key) => (
                <li key={key}>
                  <button
                    onClick={() => handleDropdownToggle(key)}
                    className="w-full flex justify-between items-center p-3 rounded-lg text-lg font-medium text-zinc-100 hover:bg-gray-700 transition-colors duration-200"
                  >
                    <span>{key}</span>
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      className={`h-5 w-5 transform transition-transform duration-200 ${
                        openDropdown === key ? 'rotate-180' : ''
                      }`}
                      viewBox="0 0 20 20"
                      fill="currentColor"
                    >
                      <path
                        fillRule="evenodd"
                        d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                        clipRule="evenodd"
                      />
                    </svg>
                  </button>

                  
                  <ul
                    className={`pl-4 mt-2 space-y-1 overflow-hidden transition-all duration-300 ${
                      openDropdown === key ? 'max-h-96 opacity-100' : 'max-h-0 opacity-0'
                    }`}
                  >
                    {menuItems[key].map((item) => (
                      <li key={item.name}>
                        <a
                          href={item.href}
                          onClick={() => setIsSidebarOpen(false)}
                          className="flex items-center p-2 rounded-lg text-sm font-medium text-zinc-400 hover:bg-zinc-800 transition-colors duration-200 border-b-2 border-zinc-800"
                        >
                          {item.name}
                        </a>
                      </li>
                    ))}
                  </ul>
                </li>
              ))}
            </ul>
          </nav>
        </div>
      </div>
    </>
  );
};

export default Sidebar;