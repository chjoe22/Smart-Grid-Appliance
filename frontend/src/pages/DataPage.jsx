import React, { useEffect, useState } from 'react';
import { Container, Typography, Button, Select, MenuItem, FormControl, InputLabel, Box } from '@mui/material';
import { getAPIData } from '../components/api/api.js';
import ChartCard from '../components/chart/ChartCard.jsx';

const sKey = "chartData";

const DataPage = () => {
    const [rawData, setRawData] = useState({});
    const [selectedSources, setSelectedSources] = useState([]);
    const [charts, setCharts] = useState([]);

    useEffect(() => {
        const saved = localStorage.getItem(sKey);
        if (saved) {
            setCharts(JSON.parse(saved));
        }
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

    useEffect(() => {
        localStorage.setItem(sKey, JSON.stringify(charts));
    }, [charts]);

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
        };


        setCharts(prev => [...prev, newChart]);
        setSelectedSources([]);
    };

    const handleRemoveChart = (id) => {
        setCharts(prev => prev.filter(chart => chart.id !== id));
    };

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>Manage Charts</Typography>

            {Object.keys(rawData).length > 0 && (
                <Box sx={{ mb: 4 }}>
                    <FormControl fullWidth>
                        <InputLabel id="data-source-label">Select Data Sources</InputLabel>
                        <Select
                            labelId="data-source-label"
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

            {charts.map((chart) => (
                <Box key={chart.id} sx={{ mb: 4 }}>
                    <ChartCard
                        title={chart.title}
                        selectedSources={chart.selectedSources}
                        chartData={chart.chartData}
                        onClose={() => handleRemoveChart(chart.id)}
                    />
                </Box>
            ))}
        </Container>
    );
};

export default DataPage;
