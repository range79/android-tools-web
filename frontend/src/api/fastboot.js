import axios from "axios";

//fastboot devices

const scanDevices = async () => {
    try {
        const res = await axios.get("http://localhost:8080/fastboot/scanDevices");
        return res.data;
    } catch (error) {
        if(error.response && error.response.status === 500) {
            console.error("Cannot run program \"fastboot\": error=2, No such file or directory");
        } else {
            console.error("Something went wrong on scanDevices func.");
            return;
        }
    };
};

const saveDevice = async (id) => {
    try {
        await axios.post(`http://localhost:8080/fastboot/saveDevice/${id}`);
    } catch (error) {
        console.error("Something went wrong on saveDevice func.");
        return;
    };
};


// To-do : POST /fastboot/{id}/reboot/{option}



//fastboot device fetcher

const fetchDevice = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8080/fastboot/device/${id}`);
        return res.data;
    } catch (error) {
        console.error("Something went wrong on fetchDevice func.");
        return;
    }
}


//fastboot devices with details 

const fetchAllDevices = async (page) => {
    try {
        const pageToFetch = page ?? 0;
        const res = await axios.get(`http://localhost:8080/fastboot/device/all?page=${pageToFetch}&size=10&sort=id,asc`);
        return res.data;
    } catch (error) {
        console.error("Something went wrong on fetchDevice func.");
        return;
    }
}

const getFastbootDeviceList = async () => {
    try {
        const res = await axios.get("http://localhost:8080/fastboot/device/list");
        return res.data;
    } catch (error) {
        console.error("Something went wrong on getFastbootDeviceList func.");
        return;
    }
}

const flashFastbootDevice = async (deviceId,firmwareId,partitionOptions) => {
    try {
        await axios.delete(`http://localhost:8080/fastboot/flash/${deviceId}/${firmwareId}/${partitionOptions}`);
    } catch (error) {
        console.error("Something went wrong on flashFastbootDevice func.");
        return;
    };
};

const getPartitionOptions = async () => {
    try {
        const res = await axios.get("http://localhost:8080/partition-options/all");
        return res.data;
    } catch (error) {
        console.error("Something went wrong on getPartitionOptions func.");
        return;
    }
};

export {scanDevices,fetchDevice,fetchAllDevices,getFastbootDeviceList,flashFastbootDevice,getPartitionOptions}