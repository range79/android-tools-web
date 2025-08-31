import axios from "axios";

const getErrorList = async () => {
    try {
        const res = await axios.get("http://localhost:8080/error/all");
        return res.data;
    } catch (error) {
        console.log("Something went wrong on getErrorList func.");
        return;
    };
};

const clearErrorList = async () => {
    try {
        const res = await axios.delete("http://localhost:8080/error/all");
        return res.data;
    } catch (error) {
        console.log("Something went wrong on clearErrorList func.");
        return;
    };
}

export {getErrorList,clearErrorList};