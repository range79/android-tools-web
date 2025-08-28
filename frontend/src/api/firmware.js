import axios from "axios";

const getAllFirmwares = async () => {
    try {
        const res = await axios.get("http://localhost:8080/firmware/all");
        return res.data;
    } catch (error) {
        console.error("Something went wrong on getAllFirmwares func.", error);
        return { content: [] };
    }
};

const getFirmwareDetails = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8080/firmware/${id}`);
        return res.data;
    } catch (error) {
        if (error.response && error.response.status === 404) {
            console.error(`ROM not found on id ${id}`);
        } else {
            console.error("Something went wrong on getFirmwareDetails func.", error.message);
        }
        return null;
    }
};

const deleteFirmware = async (id) => {
    try {
        const res = await axios.delete(`http://localhost:8080/firmware/${id}`)
        return res.data;
    } catch (error) {
        if (error.response && error.response.status === 404) {
            console.error(`firmware not found on id ${id}`);
        } else {
            console.error("Something went wrong on deleteFirmware func.", error.message);
        }
        return null;
    };
};

const uploadFirmware = async (firmwareType, file) => {
    try {
        const formData = new FormData();
        formData.append("file", file);

        const response = await axios.post(
            `http://localhost:8080/firmware/rom/upload?firmwareType=${encodeURIComponent(firmwareType)}`,
            formData
        );

        return response.data;
    } catch (error) {
        console.error("Something went wrong on uploadFirmware func.", error);
        throw error;
    }
};


export { getAllFirmwares, getFirmwareDetails, deleteFirmware, uploadFirmware }