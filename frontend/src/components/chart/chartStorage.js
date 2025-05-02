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
}