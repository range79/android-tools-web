
import axios from "axios";
export const backupToLocal = async (file) => {
    try {
        const formData = new FormData();
        formData.append("file", file);

        const response = await axios.post(
            `http://localhost:8080/backup/local`,
            formData
        );

        return response.data;
    } catch (error) {
        console.error("Something went wrong on backup to local.", error);
        throw error;
    }
};

