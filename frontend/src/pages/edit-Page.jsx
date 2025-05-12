import React, { useEffect, useState } from 'react';
import { Box, Typography } from '@mui/material';
import chartStorageManager from '../components/chart/chartStorage';
import { getAPIData } from '../components/api/api';
import EditChartCard from '../components/chart/editChartCard.jsx';

const EditPage = () => {
    const [charts, setCharts] = useState([]);
    const [availableSources, setAvailableSources] = useState([]);

    useEffect(() => {
        const loadData = async () => {
            const storedCharts = chartStorageManager.loadCharts();
            setCharts(storedCharts);
            try {
                const raw = await getAPIData();
                setAvailableSources(Object.keys(raw));
            } catch (err) {
                console.error("failed to fetch available sources", err);
            }
        };
        loadData();
    }, []);

    return (
        <Box sx={{ p: 2 }}>
            <Typography variant="h5" gutterBottom>Edit Charts</Typography>
            {charts.map((chart) => (
                <EditChartCard
                    key={chart.id}
                    chart={chart}
                    availableSources={availableSources}
                    onUpdate={setCharts}
                />
            ))}
        </Box>
    );
};
export default EditPage;
