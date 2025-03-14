import axios from "axios";

const api = axios.create({
    baseURL: '/api/v1',
});

export const getExampleData = async () => {
    try {
        // Fetch data from the API and change the endpoint to match your API
        const response = await api.get('/users');
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
}