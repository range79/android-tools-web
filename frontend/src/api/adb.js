import axios from "axios";

const scanDevices = async () => {
    try {
        const res = await axios.get("http://localhost:8080/adb/scanDevices");
        return res.data;
    } catch (error) {
        if(error.response && error.response.status === 500) {
            console.error("Cannot run program \"adb\": error=2, No such file or directory");
        } else {
            console.error("Something went wrong on scanDevices func at adb.");
            return;
        }
    };
};

const saveDevice = async (id) => {
    try {
        await axios.post(`http://localhost:8080/adb/saveDevice/${id}`);
    } catch (error) {
        console.error("Something went wrong on saveDevice func at adb.");
        return;
    };
};

const flashFirmware = async (deviceId,firmwareId) => {
    try {
        await axios.post(`http://localhost:8080/adb/flash/${deviceId}/${firmwareId}`);
    } catch (error) {
        console.error("Something went wrong on flashFirmware func at adb.");
        return;
    }
}

const fetchAllDevices = async (page) => {
    try {
        const pageToFetch = page ?? 0;
        const res = await axios.get(`http://localhost:8080/adb/devices/all?page=${pageToFetch}&size=10&sort=id,asc`);
        return res.data;
    } catch (error) {
        console.error("Something went wrong on fetchDevice func.");
        return;
    };
};

const getDeviceList = async () => {
    try {
        const res = await axios.get("http://localhost:8080/adb/devices/list");
        return res.data;
    } catch (error) {
        console.error("Something went wrong on getDeviceList func.");
        return;
    }
}
export {scanDevices,saveDevice,flashFirmware,fetchAllDevices,getDeviceList}