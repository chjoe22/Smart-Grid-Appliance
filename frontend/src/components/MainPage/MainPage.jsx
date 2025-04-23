import React from "react";

const MainPage = () => {
    return (
        <div className="w-4/5 h-screen bg-blue-100 p-6">
            <h2 className="text-center text-xl font-semibold italic">Main Page</h2>
            <div className="mt-4 grid grid-cols-2 gap-4">
                <div className="bg-green-300 p-4 rounded-lg h-64">Graph 1</div>
                <div className="bg-green-300 p-4 rounded-lg h-64">Graph 2</div>
                <div className="bg-green-300 p-4 rounded-lg h-64">Graph 3</div>
            </div>
        </div>
    );
};

const App = () => {
    return (
        <div className="flex h-screen border-2 border-black">
            <Sidebar />
            <MainPage />
        </div>
    );
};

export default App;
