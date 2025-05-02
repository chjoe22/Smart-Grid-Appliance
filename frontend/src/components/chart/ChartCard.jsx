import React, {useCallback, useEffect, useState} from 'react';
import { Card, CardHeader, CardContent, IconButton, Typography } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { LineChart } from '@mui/x-charts/LineChart';
import { getSpecificAPIData } from '../api/specificAPI.js';

export default function ChartCard({ title, selectedSources, chartData, onClose }) {
    const [rawData, setRawData] = useState( chartData || {});
    const [lastUpdated, setLastUpdated] = useState(null);

    const fetchChartData = useCallback(async () => {

        const newData = {};
        for (const source of selectedSources) {
            try {
                let String = source.replace(/ /g, '');
                String.toString().toLowerCase();
                console.log(String)
                const result = await getSpecificAPIData(String);
                console.log(source + " fetched " + result.length + " records");
                newData[source] = result;
            } catch (error) {
                console.error(`Failed to fetch ${source}`, error);
            }
        }
        setRawData(newData);
        setLastUpdated(new Date().toLocaleTimeString());
    }, [selectedSources, chartData]);

    useEffect(() => {
        if (chartData && Object.keys(chartData).length > 0) {
            setRawData(chartData);
            setLastUpdated(new Date().toLocaleTimeString());
            return;
        }
        fetchChartData();
        const intervalId = setInterval(() => {
            fetchChartData();
        }, 10000);

        return () => clearInterval(intervalId);
    }, [chartData, fetchChartData]);

    if (Object.keys(rawData).length === 0) {
        return (
            <Card sx={{ borderRadius: 4, p: 2, backgroundColor: '#f0f4f8' }}>
                <CardContent>
                    <Typography>Loading chart data...</Typography>
                </CardContent>
            </Card>
        );
    }

    const allTimestamps = new Set();
    Object.values(rawData).forEach(arr => {
        arr.forEach(entry => allTimestamps.add(entry.timestamp));
    });

    const sortedTimestamps = Array.from(allTimestamps).sort();

    const xLabels = sortedTimestamps.map(timestamp =>
        new Date(timestamp).toLocaleString()
    );

    const unifiedData = sortedTimestamps.map(timestamp => {
        const point = { timestamp };

        for (const [sensorName, records] of Object.entries(rawData)) {
            const record = records.find(r => r.timestamp === timestamp);

            if (record) {
                const valueField = Object.keys(record).find(
                    key => !['id', 'context_id', 'timestamp'].includes(key) && typeof record[key] === 'number'
                );
                if (valueField) {
                    point[sensorName] = record[valueField];
                }
            }
        }

        return point;
    });

    const series = Object.keys(rawData).map((sensorName, index) => ({
        label: sensorName,
        data: unifiedData.map(d => d[sensorName] ?? null),
        yAxisKey: index < 2 ? 'left' : 'right',
        showMark: false,
        grid: { vertical: true, horizontal: true}
    }));

    return (
        <Card sx={{ borderRadius: 4, p: 2, backgroundColor: '#f0f4f8' }}>
            <CardHeader
                title={title}
                action={
                onClose ? (
                    <IconButton onClick={onClose}>
                        <CloseIcon />
                    </IconButton>
                ) : null
            }
                subheader={lastUpdated ? `Last updated: ${lastUpdated}` : null}
                titleTypographyProps={{ align: 'center', variant: 'h6' }}
            />
            <CardContent>
                <LineChart
                    height={300}
                    xAxis={[{ scaleType: 'point', data: xLabels }]}
                    yAxis={[{ id: 'left' }, { id: 'right' }]}
                    series={series}
                    width={800}
                />
            </CardContent>
        </Card>
    );
}
