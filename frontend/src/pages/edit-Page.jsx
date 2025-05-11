import React, { useEffect, useState } from 'react';
import chartStorageManager from '../components/chart/chartStorage';
import { Box, Typography } from '@mui/material';
import ChartGrid from "../components/chart/ChartGrid.jsx";

const EditPage = () => {
    const [charts, setCharts] = useState(null);


    useEffect(() => {
        setCharts(chartStorageManager.loadCharts())
    }, []);

    const handleResizeChart = (id, newSize) => {
        const updated = chartStorageManager.updateSize(id, newSize);
        setCharts(updated);
    }
    const handleMoveChartUp = (id) => {
        const updated = chartStorageManager.moveChartUp(id);
        setCharts(updated);
    };
    const handleMoveChartDown = (id) => {
        const updated = chartStorageManager.moveChartDown(id);
        setCharts(updated);
    };

    const handleEditChart = (id, updatedSources, updatedChartData) => {
        const updatedCharts = charts.map(chart => {
            if (chart.id === id) {
                return {
                    ...chart,
                    selectedSources: updatedSources,
                    chartData: updatedChartData
                };
            }
            return chart;
        });

        setCharts(updatedCharts);
        chartStorageManager.saveCharts(updatedCharts); // Persist the full updated state
    };


    if (!charts) return <Typography>Loading...</Typography>;

    return (
        <Box sx={{ p: 2 }}>
            <Typography variant="h5" gutterBottom>Edit Chart: {charts.title}</Typography>

            <ChartGrid
                charts={charts}
                onResize={handleResizeChart}
                onMoveUp={handleMoveChartUp}
                onMoveDown={handleMoveChartDown}
                onEdit={handleEditChart}
            />
        </Box>
    );
};

export default EditPage;
