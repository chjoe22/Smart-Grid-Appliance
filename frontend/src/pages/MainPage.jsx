import React, { useEffect, useState } from 'react';
import { Container, Typography, Box } from '@mui/material';
import ChartCard from '../components/chart/ChartCard';
import chartStorage from '../components/chart/chartStorage.js';


const MainPage = () => {
    const [charts, setCharts] = useState([]);

    useEffect(() => {
        setCharts(chartStorage.loadCharts());
    }, []);

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>Saved Charts</Typography>

            {charts.length === 0 ? (
                <Typography>No charts found. Go to the Edit page to add some!</Typography>
            ) : (
                charts.map((chart) => (
                    <Box key={chart.id} sx={{ mb: 4 }}>
                        <ChartCard
                            title={chart.title}
                            selectedSources={chart.selectedSources}
                            chartData={chart.chartData}
                            onClose={null}
                        />
                    </Box>
                ))
            )}
        </Container>
    );
};

export default MainPage;
