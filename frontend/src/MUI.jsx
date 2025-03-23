import React, {useEffect, useState} from 'react';
import {getTempData} from "./tempAPI.js";
import {LineChart} from "@mui/x-charts";

function MUI(){
    const [data, setData] = useState(null)

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Fetch data from the API
                const data = await getTempData();

                // Set the data to the state, change filteredData to data to see all fields
                setData(data);
            } catch (error) {
                console.error('Error fetching data: ', error);
            }
        };
        fetchData();
    }, []);
    return (
        <div>
            {data ? (
                <div>
                    <h2>Temperature Data</h2>
                    <pre>Temp: {JSON.stringify(data.temp, null, 0)}</pre>
                    <pre>Time: {JSON.stringify(data.time, null, 0)}</pre>
                    <LineChart
                        xAxis={[{ data: data.time}]}
                        series={[
                            {
                                data: data.temp,
                                area: true
                            }
                        ]}
                        width={800}
                        height={400}
                    />
                </div>

            ) : (
                <p>Loading...</p>
            )}
        </div>
    )
}
export default MUI;