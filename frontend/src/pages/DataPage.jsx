import React, { useEffect, useState } from 'react';
import { Container, Typography, Button, Select, MenuItem, FormControl, InputLabel, Box } from '@mui/material';
import { getAPIData } from '../components/api/api.js';
import ChartCard from '../components/chart/ChartCard.jsx';
import chartStorage from '../components/chart/chartStorage.js';


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
        setCharts(updateChartSize)
    }

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
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

            <Box
                sx={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    flexDirection: 'row',
                    alignContent: 'space-between',
                    p: 2,
                    m: 1,
                }}>
                {charts.map((chart) => (
                    <Box key={chart.id} sx={{ mb: 4 }}>
                        <ChartCard
                            id={chart.id}
                            title={chart.title}
                            selectedSources={chart.selectedSources}
                            chartData={chart.chartData}
                            size={chart.size || 2}
                            onClose={() => handleRemoveChart(chart.id)}
                            onResize={handleResizeChart}
                        />
                    </Box>
                ))}
            </Box>

        </Container>
    );
};

export default DataPage;
