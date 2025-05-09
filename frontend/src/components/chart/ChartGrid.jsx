import React from 'react';
import { Box } from '@mui/material';
import ChartCard from './ChartCard';

const ChartGrid = ({ charts, onRemove, onResize, onMoveUp, onMoveDown }) => {
    return (
        <Box
            sx={{
                display: 'grid',
                gridTemplateColumns: 'repeat(2, 1fr)',
                gap: 2,
                width: '100%',
                maxWidth: '100%',
                overflow: 'hidden',
                boxSizing: 'border-box',
            }}
        >
            {charts.map(chart => (
                <Box
                    key={chart.id}
                    sx={{
                        gridColumn: chart.size === 3 ? '1 / -1' : 'auto',
                        boxSizing: 'border-box',
                        width: '100%',
                        minWidth: 0,
                    }}
                >
                    <ChartCard
                        id={chart.id}
                        title={chart.title}
                        selectedSources={chart.selectedSources}
                        chartData={chart.chartData}
                        size={chart.size}
                        onClose={onRemove ? () => onRemove(chart.id) : null}
                        onResize={onResize}
                        onMoveUp={onMoveUp}
                        onMoveDown={onMoveDown}
                    />
                </Box>
            ))}
        </Box>

    );
};

export default ChartGrid;
