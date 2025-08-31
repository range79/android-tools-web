import './App.css'
import { Route, Routes } from 'react-router-dom'
import FirmwareList from './components/firmware/FirmwareList'
import FirmwareDetails from './components/firmware/FirmwareDetails'
import FirmwareUpload from './components/firmware/FirmwareUpload'
import FastbootDeviceList from './components/fastboot/FastbootDeviceList'
import AdbDeviceList from './components/adb/AdbDeviceList'
import FastbootDeviceDetails from './components/fastboot/FastbootDeviceDetails'
import FastbootAllDevices from './components/fastboot/FastbootAllDevices'
import AdbAllDevices from './components/adb/AdbAllDevices'
import "./App.css"
import FastbootRemovePartition from './components/fastboot/FastbootRemovePartition'
import { Toaster } from 'react-hot-toast'
import ErrorList from './components/errors/ErrorList'
import PageNotFound from './components/PageNotFound'

function App() {

  return (
    <div className='bg-zinc-900'>
    <Toaster position='top-right'/>
        <Routes>
          <Route path='/' element={<FirmwareList />} />
          <Route path='/firmwares/all' element={<FirmwareList />} />
          <Route path='/firmwares/:id' element={<FirmwareDetails />} />
          <Route path='/firmwares/upload' element={<FirmwareUpload />} />
          <Route path='/fastboots/scanDevices' element={<FastbootDeviceList />} />
          <Route path='/fastboots/device/:id' element={<FastbootDeviceDetails />} />
          <Route path='/fastboots/device/all' element={<FastbootAllDevices />} />
          <Route path='/fastboots/removePartition' element={<FastbootRemovePartition />} />
          <Route path='/adb/scanDevice' element={<AdbDeviceList />} />
          <Route path='/adb/allDevices' element={<AdbAllDevices />} />
          <Route path='/errors/list' element={<ErrorList/>} />
          <Route path='*' element={<PageNotFound />} />
        </Routes>
    </div>
  )
}

export default App
