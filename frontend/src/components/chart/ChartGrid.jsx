import React from 'react';
import PropTypes from 'prop-types';
import { Box } from '@mui/material';
import ChartCard from './ChartCard';

export default function ChartGrid ({ charts, onRemove, onResize, onMoveUp, onMoveDown, editMode, availableSources }) {
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
                        editMode={editMode}
                        availableSources={availableSources}
                    />
                </Box>
            ))}
        </Box>

    );
};

ChartGrid.propTypes = {
    charts: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            title: PropTypes.string.isRequired,
            selectedSources: PropTypes.arrayOf(PropTypes.string).isRequired,
            chartData: PropTypes.object,
            size: PropTypes.number.isRequired,
        })
    ).isRequired,
    onRemove: PropTypes.func,
    onResize: PropTypes.func,
    onMoveUp: PropTypes.func,
    onMoveDown: PropTypes.func,
    editMode: PropTypes.bool,
    availableSources: PropTypes.arrayOf(PropTypes.string),
};