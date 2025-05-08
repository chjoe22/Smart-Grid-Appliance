import React, { useEffect, useState } from 'react';
import { Container, Typography, Box } from '@mui/material';
import ChartCard from '../components/chart/ChartCard';
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
        <Container maxWidth={false} sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>Charts</Typography>

            <ChartGrid
                charts={charts}
                onRemove={null}
                onResize={handleResizeChart}
                onMoveUp={handleMoveChartUp}
                onMoveDown={handleMoveChartDown}
            />
        </Container>
    );
};

export default MainPage;
