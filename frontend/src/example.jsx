import React, {useEffect, useState} from 'react'
import {getExampleData} from "./api"

function NameExample() {
        const [data, setData] = useState(null)

        useEffect(() => {
            const fetchData = async () => {
                try {
                    // Fetch data from the API
                    const data = await getExampleData();
                    // Example of how to extract only the fields you need
                    const filteredData = data.map(item => ({
                        firstname: item.firstname,
                        lastname: item.lastname,
                    }));
                    // Set the data to the state, change filteredData to data to see all fields
                    setData(filteredData);
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
                        <pre>{JSON.stringify(data, null, 1)}</pre>
                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        )
    }

export default NameExample;