import axios from "axios";

const api = axios.create({
    baseURL: '/api',
});

export const getSpecificAPIData = async (name) => {
    try {
        const response = await api.get('/data' + `/${name}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
}