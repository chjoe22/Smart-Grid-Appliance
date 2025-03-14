import React, {useEffect, useState} from "react";
import {getExampleData} from "./api";


    const exampleData = () => {
        const [data, setData] = useState(null);

        useEffect(() => {
            const fetchData = async () => {
                try {
                    const data = await getExampleData();
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
                        <pre>{JSON.stringify(data, null, 2)}</pre>
                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        )
    }

export default exampleData();