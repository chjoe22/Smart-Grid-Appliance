import {getSpecificAPIData} from "../api/specificAPI.js";

export async function fetchChartData(sources) {
    const data = {};

    for (const source of sources) {
        try {
            const cleaned = encodeURIComponent(source.replace(/\s+/g, ''));
            const result = await getSpecificAPIData(cleaned);
            data[source] = result;
        } catch (error) {
            console.error(`Failed to fetch data for ${source}`, error);
        }
    }

    return data;
}