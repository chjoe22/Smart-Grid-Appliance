import { getSpecificAPIData } from "../api/specificAPI";
import {getPredictionData} from "../api/getPredictionAPI.js";

export async function fetchChartData(sources, includePrediction = false) {
    const data = {};

    for (const source of sources) {
        try {
            const cleaned = encodeURIComponent(source.replace(/\s+/g, ''));
            const result = await getSpecificAPIData(cleaned.toLowerCase());
            data[source] = result;
        } catch (error) {
            console.error(`Failed to fetch data for ${source}`, error);
        }
    }

    if (includePrediction) {
        try {
            const prediction = await getPredictionData("el/next")
            data["Energy Price Prediction"] = prediction.map((item) => ({
                timestamp: item.timestamp,
                predictionPrice: item.predictionPrice,
            }));
            console.log("Prediction data fetched successfully", prediction);
        } catch (error) {
            console.error("Failed to fetch prediction data", error);
        }
    }

    return data;
}