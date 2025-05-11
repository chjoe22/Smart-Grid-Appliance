import React, { useEffect, useState } from 'react';
import { Container, Typography, Button, Select, MenuItem, FormControl, InputLabel, Box } from '@mui/material';
import { getAPIData } from '../components/api/api.js';
import chartStorage from '../components/chart/chartStorage.js';
import ChartGrid from '../components/chart/ChartGrid.jsx';


const DataPage = () => {
    const [rawData, setRawData] = useState({});
    const [selectedSources, setSelectedSources] = useState([]);
    const [charts, setCharts] = useState([]);

    useEffect(() => {
        setCharts(chartStorage.loadCharts());
    }, []);


    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await getAPIData();
                setRawData(result);
            } catch (err) {
                console.error('Failed to load categories', err);
            }
        };

        fetchData();
    }, []);


    const handleAddChart = () => {
        if (selectedSources.length === 0) return;

        const chartData = {};
        selectedSources.forEach(source => {
            const matchingKey = Object.keys(rawData).find(
                key => key.toLowerCase() === source.toLowerCase()
            );

            if (matchingKey) {
                chartData[source] = rawData[matchingKey];
            }
        });

        const newChart = {
            id: crypto.randomUUID(),
            title: selectedSources.join(', '),
            selectedSources: [...selectedSources],
            chartData,
            size: 2,
        };


        const updatedCharts = chartStorage.addChart(newChart);
        setCharts(updatedCharts);
        setSelectedSources([]);
    };

    const handleRemoveChart = (id) => {
        const updateCharts = chartStorage.removeChart(id)
        setCharts(updateCharts)
    };

    const handleResizeChart = (id, newSize) => {
        const updateChartSize = chartStorage.updateSize(id, newSize);
        setCharts(updateChartSize);
    }

    const handleMoveChartUp = (id) => {
        const updateChartMovedUp = chartStorage.moveChartUp(id);
        setCharts(updateChartMovedUp);
    };

    const handleMoveChartDown = (id) => {
        const updateChartMovedDown = chartStorage.moveChartDown(id);
        setCharts(updateChartMovedDown);
    };

    return (
        <Container maxWidth={false} sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>Manage Charts</Typography>

            {Object.keys(rawData).length > 0 && (
                <Box sx={{ mb: 4 }}>
                    <FormControl fullWidth>
                        <InputLabel id="data-source">Select Data Sources</InputLabel>
                        <Select
                            labelId="data-source"
                            multiple
                            value={selectedSources}
                            onChange={(e) => setSelectedSources(e.target.value)}
                            label="Select Data Sources"
                        >
                            {Object.keys(rawData).map((source) => (
                                <MenuItem key={source} value={source.toString().toLowerCase()}>
                                    {source}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <Button variant="contained" color="primary" onClick={handleAddChart} sx={{ mt: 2 }}>
                        Add Chart
                    </Button>
                </Box>
            )}

            <ChartGrid
                charts={charts}
                onRemove={handleRemoveChart}
                onResize={handleResizeChart}
                onMoveUp={handleMoveChartUp}
                onMoveDown={handleMoveChartDown}
            />
        </Container>
    );
};

export default DataPage;
