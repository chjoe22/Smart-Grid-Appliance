import { getSpecificAPIData } from "../api/specificAPI";
import {getPredictionData} from "../api/getPredictionAPI.js";

export async function fetchChartData(sources, includePrediction = false) {
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

    if (includePrediction) {
        try {
            const prediction = await getPredictionData("el/next")
            data["Prediction"] = prediction;
        } catch (error) {
            console.error("Failed to fetch prediction data", error);
        }
    }

    return data;
}