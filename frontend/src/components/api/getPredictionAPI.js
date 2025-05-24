import axios from "axios";

const api = axios.create({
    baseURL: '/api',
});

export const getPredictionData = async (name) => {
    try {
        const response = await api.get('/prediction' + `/${name}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
};
