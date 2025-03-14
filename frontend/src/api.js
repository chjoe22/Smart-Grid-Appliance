import axios from "axios";

const api = axios.create({
    baseURL: '/api',
});

export const getExampleData = async () => {
    try {
        const response = await api.get('/endpoint');
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
}