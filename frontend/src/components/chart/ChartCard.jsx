import React, {useCallback, useEffect, useState} from 'react';
import { Card, CardHeader, CardContent, IconButton, Typography, Box, Button } from '@mui/material';
import Collapse from '@mui/material/Collapse';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CloseIcon from '@mui/icons-material/Close';
import { LineChart } from '@mui/x-charts/LineChart';
import { getSpecificAPIData } from '../api/specificAPI.js';
import chartStorageManager from './chartStorage.js';

export default function ChartCard({ id, title, selectedSources, chartData, onClose }) {
    const [rawData, setRawData] = useState( chartData || {});
    const [lastUpdated, setLastUpdated] = useState(null);
    const [expanded, setExpanded] = useState(true);
    const [chartWidth, setChartWidth] = useState(800);

    const fetchChartData = useCallback(async () => {
        console.log("Fetching data for Chart ID:", id);
        const newData = {};
        for (const source of selectedSources) {
            try {
                const sourceFix = source.replace(/ /g, '');
                console.log(sourceFix)
                const result = await getSpecificAPIData(sourceFix.toLowerCase());
                console.log(source + " fetched " + result.length + " records");
                newData[source] = result;
            } catch (error) {
                console.error(`Failed to fetch ${source}`, error);
            }
        }
        setRawData(newData);
        setLastUpdated(new Date().toLocaleTimeString());
        chartStorageManager.updateChart(id, newData)
    }, [selectedSources, id]);

    useEffect(() => {
        if (chartData && Object.keys(chartData).length > 0) {
            setRawData(chartData);
            setLastUpdated(new Date().toLocaleTimeString());
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

    const handleExpand = () => {
        setExpanded((prev) => !prev);
    }

    const handleResize = (newSize) => {
        setChartWidth(newSize)
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

    const series = Object.keys(rawData).map((category, index) => ({
        label: category,
        data: unifiedData.map(d => d[category] ?? null),
        yAxisKey: index < 2 ? 'left' : 'right',
        showMark: false,
        grid: { vertical: true, horizontal: true}
    }));

    return (
        <Card sx={{ width: chartWidth, borderRadius: 4, p: 2, backgroundColor: '#f0f4f8' }}>
            <CardHeader
                title={title}
                action={
                    <>{onClose ? (
                    <IconButton onClick={onClose}>
                        <CloseIcon />
                    </IconButton>
                ) : null}
                        <IconButton
                            onClick={handleExpand}
                            aria-expanded={expanded}
                            aria-label="show more"
                            > <ExpandMoreIcon/>
                        </IconButton>
            </>}
                subheader={lastUpdated ? `Last updated: ${lastUpdated}` : null}
                titleTypographyProps={{ align: 'center', variant: 'h6' }}
            />
            <CardContent>
                <Collapse in={expanded} timeout="auto" unmountOnExit>
                <LineChart
                    height={300}
                    xAxis={[{ scaleType: 'point', data: xLabels }]}
                    yAxis={[{ id: 'left' }, { id: 'right' }]}
                    series={series}
                    width={chartWidth-40}
                />
                </Collapse>
            </CardContent>
            <Box sx={{ display: 'flex', justifyContent: 'center', mb: 2 }}>
                <Button onClick={() => handleResize(600)}>Small</Button>
                <Button onClick={() => handleResize(800)}>Medium</Button>
                <Button onClick={() => handleResize(1000)}>Large</Button>
            </Box>
        </Card>
    );
}
