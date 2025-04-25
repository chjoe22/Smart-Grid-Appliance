// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayoutDom from "./MainLayoutDom";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import MainPage from "./components/MainPage/MainPage.jsx";
import HomePage from "./pages/HomePage.jsx";
import Example from "./example.jsx";
import Dashboard from "./components/Graph/Dashboard.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom />}>
                    <Route index element={<HomePage />} />
                    <Route path="main" element={<MainPage />} />
                </Route>
                <Route path="/login" element={<LoginSignUp />} />
                <Route path="/data" element={<Dashboard />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
