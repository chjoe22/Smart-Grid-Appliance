import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Box, Select, MenuItem, Typography, Button, Paper, FormControlLabel, Checkbox } from '@mui/material';
import ChartCard from './chartCard.jsx';
import { fetchChartData } from './chartDataManager.js';
import chartStorageManager from './chartStorage.js'

export default function EditChartCard({ chart, availableSources, onUpdate }){
    const [selectedSources, setSelectedSources] = useState(chart.selectedSources);
    const [showPredictions, setShowPredictions] = useState(chart.showPredictions || false);

    useEffect(() => {
        setSelectedSources(chart.selectedSources);
    }, [chart.selectedSources]);

    const handleSave = async () => {
        const newChartData = await fetchChartData(selectedSources, showPredictions);
        const updated = chartStorageManager.updateChartData(chart.id, selectedSources, newChartData, showPredictions);
        onUpdate(updated);
    };

    return (
        <Paper sx={{ display: 'flex', p: 2, mb: 3 }}>
            <Box sx={{ flex: 2, pr: 2 }}>
                <ChartCard
                    id={chart.id}
                    title={chart.title}
                    selectedSources={chart.selectedSources}
                    chartData={chart.chartData}
                    size={2}
                    editMode={false}
                    showPredictions={showPredictions}
                />
            </Box>
            <Box sx={{ flex: 1, pl: 2, display: 'flex', flexDirection: 'column' }}>
                <Typography variant="subtitle1">Edit Data Sources</Typography>
                <Select
                    multiple
                    fullWidth
                    value={selectedSources}
                    onChange={(e) => {
                        const unique = [...new Set(e.target.value)];
                        setSelectedSources(unique);
                    }}
                    renderValue={(selected) => selected.join(', ')}
                    sx={{ mt: 1 }}
                >
                    {availableSources.map(source => (
                        <MenuItem key={source} value={source}>
                            {source}
                        </MenuItem>
                    ))}
                </Select>
                <FormControlLabel
                    control={
                        <Checkbox
                            checked={showPredictions}
                            onChange={(e) => setShowPredictions(e.target.checked)}
                        />
                    }
                    label="Show Predictions"
                />

                <Button
                    variant="contained"
                    sx={{ mt: 2, alignSelf: 'flex-start' }}
                    onClick={handleSave}
                >
                    Save Changes
                </Button>
            </Box>
        </Paper>
    );
};

EditChartCard.propTypes = {
    chart: PropTypes.shape({
        id: PropTypes.string.isRequired,
        title: PropTypes.string.isRequired,
        selectedSources: PropTypes.arrayOf(PropTypes.string).isRequired,
        chartData: PropTypes.object,
    }).isRequired,
    availableSources: PropTypes.arrayOf(PropTypes.string).isRequired,
    onUpdate: PropTypes.func.isRequired,
};
