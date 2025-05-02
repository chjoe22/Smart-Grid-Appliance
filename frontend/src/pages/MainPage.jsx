import React, { useEffect, useState } from 'react';
import { Container, Typography, Box } from '@mui/material';
import ChartCard from '../components/chart/ChartCard';

const STORAGE_KEY = 'chartData';  // same as your storage key

const MainPage = () => {
    const [charts, setCharts] = useState([]);

    useEffect(() => {
        const savedCharts = localStorage.getItem(STORAGE_KEY);
        if (savedCharts) {
            setCharts(JSON.parse(savedCharts));
        }
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
                            chartData={chart.chartData}  // <-- important
                            onClose={null}  // no delete button on view page
                        />
                    </Box>
                ))
            )}
        </Container>
    );
};

export default MainPage;
