import axios from "axios";

const api = axios.create({
    baseURL: '/api',
});

export const getExampleData = async () => {
    try {
        // Fetch data from the API and change the endpoint to match your API
        const response = await api.get('/data');
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
}