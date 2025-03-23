import axios from "axios";

const api =  axios.create({
    baseURL: '/api/v1',
});

export const getTempData = async () => {
    try {
        const response = await api.get('/temp');
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
        throw error;
    }
}