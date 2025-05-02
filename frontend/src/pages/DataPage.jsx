import React, { useEffect, useState } from 'react';
import { Container, Typography, Button, Select, MenuItem, FormControl, InputLabel, Box } from '@mui/material';
import { getAPIData } from '../components/api/api.js';
import ChartCard from '../components/chart/ChartCard.jsx';

const DataPage = () => {
    const [rawData, setRawData] = useState({});
    const [selectedSources, setSelectedSources] = useState([]);
    const [charts, setCharts] = useState([]);

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
            if (rawData[source]) {
                chartData[source] = rawData[source];
            }
        });

        setCharts(prev => [
            ...prev,
            { id: Date.now(), title: selectedSources.join(', '), selectedSources }
        ]);
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
                        onClose={() => handleRemoveChart(chart.id)}
                    />
                </Box>
            ))}
        </Container>
    );
};

export default DataPage;
