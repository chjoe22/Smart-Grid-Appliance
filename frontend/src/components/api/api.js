import axios from "axios";

const api = axios.create({
    baseURL: '/api',
});

export const getAPIData = async (endpoint = "/data") => {
    try {
        // Fetch data from the API
        const response = await api.get(endpoint);
        return response.data;
    } catch (error) {
        console.error('Error fetching data from: ' + endpoint, error);
        throw error;
    }
}