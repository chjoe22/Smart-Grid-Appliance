import { useState, useEffect } from "react";

const sKey = "chartData";

export function useChartStorage() {
    const [chartData, setChartData] = useState(() => {
        const saved = localStorage.getItem(sKey);
        return saved ? JSON.parse(saved) : [];
    });

    useEffect(() => {
        localStorage.setItem(sKey, JSON.stringify(chartData));
    }, [chartData]);

    const addChart = (chart) => {
        setChartData((prev) => [...prev, chart]);
    };

    const removeChart = (id) => {
        setChartData((prev) => prev.filter((chart) => chart.id !== id));
    };

    const updateChart = (newChart) => {
        setChartData(newChart)
    }

    return {chartData, addChart, removeChart, updateChart}
}