import React, { useEffect, useState } from 'react';
import { Typography, Box } from '@mui/material';
import chartStorage from '../components/chart/chartStorage.js';
import ChartGrid from "../components/chart/ChartGrid.jsx";


const MainPage = () => {
    const [charts, setCharts] = useState([]);

    useEffect(() => {
        setCharts(chartStorage.loadCharts());
    }, []);

    const handleResizeChart = (id, newSize) => {
        const updated = chartStorage.updateSize(id, newSize);
        setCharts(updated);
    };

    const handleMoveChartUp = (id) => {
        const updated = chartStorage.moveChartUp(id);
        setCharts(updated);
    };

    const handleMoveChartDown = (id) => {
        const updated = chartStorage.moveChartDown(id);
        setCharts(updated);
    };

    return (
        <Box sx={{ flexGrow: 1, width: '100%', overflowX: 'hidden' }}>
            <Box sx={{ width: '100%', maxWidth: '100%', boxSizing: 'border-box', mt: 4 }}>
                <Typography variant="h4" gutterBottom>Charts</Typography>

                <ChartGrid
                    charts={charts}
                    onResize={handleResizeChart}
                    onMoveUp={handleMoveChartUp}
                    onMoveDown={handleMoveChartDown}
                />
            </Box>
        </Box>
    );
};

export default MainPage;
