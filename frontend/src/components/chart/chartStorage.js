export default class chartStorageManager {
    static sKey = "chartData";

    static loadCharts() {
        const saved = localStorage.getItem(chartStorageManager.sKey);
        return saved ? JSON.parse(saved) : [];
    }

    static saveCharts(charts) {
        localStorage.setItem(chartStorageManager.sKey, JSON.stringify(charts));
    }

    static addChart(newChart) {
        const charts = chartStorageManager.loadCharts();
        charts.push(newChart);
        chartStorageManager.saveCharts(charts);
        return charts;
    }

    static removeChart(id) {
        const charts = chartStorageManager.loadCharts().filter(chart => chart.id !== id);
        chartStorageManager.saveCharts(charts);
        return charts;
    }
    static updateChart(id, newData) {
        const chart = chartStorageManager.loadCharts();
        const updateChart = chart.map(chart => {
            if (chart.id === id) {
                return { ...chart, chartData: newData};
            }
            return chart;
        });
        console.log("updateChart", updateChart);
        chartStorageManager.saveCharts(updateChart)
    }
    static updateSize(id, newSize) {
        const charts = chartStorageManager.loadCharts();
        const updatedCharts = charts.map(chart => {
            if (chart.id === id) {
                return { ...chart, size: newSize };
            }
            return chart;
        });
        chartStorageManager.saveCharts(updatedCharts);
        return updatedCharts;
    }

    static moveChartUp(id) {
        const charts = chartStorageManager.loadCharts();
        const index = charts.findIndex(c => c.id === id);
        if (index > 0) {
            [charts[index - 1], charts[index]] = [charts[index], charts[index - 1]];
            chartStorageManager.saveCharts(charts);
        }
        return charts;
    }

    static moveChartDown(id) {
        const charts = chartStorageManager.loadCharts();
        const index = charts.findIndex(c => c.id === id);
        if (index < charts.length - 1) {
            [charts[index], charts[index + 1]] = [charts[index + 1], charts[index]];
            chartStorageManager.saveCharts(charts);
        }
        return charts;
    }

    static clearCharts() {
        localStorage.removeItem(chartStorageManager.sKey);
    }

    static updateChartData(id, selectedSources, chartData, showPredictions) {
        const charts = chartStorageManager.loadCharts();
        const updatedCharts = charts.map(chart => {
            if (chart.id === id) {
                return { ...chart, selectedSources, chartData, showPredictions: !!showPredictions };
            }
            return chart;
        });
        chartStorageManager.saveCharts(updatedCharts);
        return updatedCharts;

    }

}