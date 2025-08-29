import './App.css'
import { Route, Routes } from 'react-router-dom'
import FirmwareList from './components/firmware/FirmwareList'
import FirmwareDetails from './components/firmware/FirmwareDetails'
import FirmwareUpload from './components/firmware/FirmwareUpload'
import PageNotFound from './pages/PageNotFound'
import FastbootDeviceList from './components/fastboot/FastbootDeviceList'
import AdbDeviceList from './components/adb/AdbDeviceList'
import FastbootDeviceDetails from './components/fastboot/FastbootDeviceDetails'
import FastbootAllDevices from './components/fastboot/FastbootAllDevices'
import AdbAllDevices from './components/adb/AdbAllDevices'
import "./App.css"
import FastbootRemovePartition from './components/fastboot/FastbootRemovePartition'
import { Toaster } from 'react-hot-toast'

function App() {

  return (
    <>
    <Toaster position='top-right'/>
        <Routes>
          <Route path='/' element={<FirmwareList />} />
          <Route path='/firmware/all' element={<FirmwareList />} />
          <Route path='/firmware/:id' element={<FirmwareDetails />} />
          <Route path='/firmware/upload' element={<FirmwareUpload />} />
          <Route path='/fastboot/scanDevices' element={<FastbootDeviceList />} />
          <Route path='/fastboot/device/:id' element={<FastbootDeviceDetails />} />
          <Route path='/fastboot/device/all' element={<FastbootAllDevices />} />
          <Route path='/fastboot/removePartition' element={<FastbootRemovePartition />} />
          <Route path='/adb/scanDevices' element={<AdbDeviceList />} />
          <Route path='/adb/devices/all' element={<AdbAllDevices />} />
          <Route path='*' element={<PageNotFound />} />
        </Routes>
    </>
  )
}

export default App
