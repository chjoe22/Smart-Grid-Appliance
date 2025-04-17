import React, {useEffect, useState} from 'react'
import {getExampleData} from "./api"

function Example() {
        const [data, setData] = useState(null)

        useEffect(() => {
            const fetchData = async () => {
                try {
                    // Fetch data from the API
                    const data = await getExampleData();
                    // Example of how to extract only the fields you need

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
                    <h2>Example Data</h2>
                    {Object.keys(data).map((category) => (
                        <div key={category}>
                            <h3>{category}</h3>
                            <pre>{JSON.stringify(data[category], null, 1)}</pre>
                        </div>
                    ))}
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
    }

export default Example;