import React, { useEffect, useState } from 'react';
import { getExampleData } from '../../api';
import {Container, Card, CardContent, Typography, FormControl, InputLabel, Select, MenuItem,} from '@mui/material';
import { LineChart } from '@mui/x-charts/LineChart';

const Dashboard = () => {
    const [data, setData] = useState(null);
    const [selectedKey, setSelectedKey] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await getExampleData();
                setData(result);

                // Only set default key ONCE (on first load)
                if (!selectedKey && Object.keys(result).length > 0) {
                    setSelectedKey(Object.keys(result)[0]);
                }

            } catch (err) {
                console.error('Error loading data:', err);
            }
        };

        fetchData();
        const interval = setInterval(fetchData, 1000);
        return () => clearInterval(interval);
    }, [selectedKey]);


    const extractChartData = (categoryData) => {
        if (!categoryData || categoryData.length === 0) return { x: [], y: [] };

        const sample = categoryData[0];
        const valueKey = Object.keys(sample).find(
            (k) => typeof sample[k] === 'number' && !['id', 'context_id'].includes(k)
        );

        return {
            x: categoryData.map((item) => new Date(item.timestamp).getTime()),
            y: categoryData.map((item) => item[valueKey]),
            valueKey,
        };
    };


    const { x, y, valueKey } = extractChartData(data?.[selectedKey]);

    return (
        <Container maxWidth="lg" sx={{ mt: 4 }}>
            <FormControl fullWidth sx={{ mb: 4 }}>
                <InputLabel id="select-label">Select Metric</InputLabel>
                <Select
                    labelId="select-label"
                    value={selectedKey}
                    onChange={(e) => setSelectedKey(e.target.value)}
                    label="Select Metric"
                >
                    {data &&
                        Object.keys(data).map((key) => (
                            <MenuItem key={key} value={key}>
                                {key}
                            </MenuItem>
                        ))}
                </Select>
            </FormControl>

            {x.length > 0 && y.length > 0 && (
                <Card>
                    <CardContent>
                        <Typography variant="h6" gutterBottom>
                            {selectedKey} - {valueKey}
                        </Typography>
                        <LineChart
                            xAxis={[{
                                data: x,
                                scaleType: 'time',
                                valueFormatter: (value) => new Date(value).toLocaleTimeString(), // now safe to format for display
                                label: 'Time'
                            }]}
                            series={[{ data: y, label: valueKey }]}
                            width={800}
                            height={400}
                        />

                    </CardContent>
                </Card>
            )}
        </Container>
    );
};

export default Dashboard;
