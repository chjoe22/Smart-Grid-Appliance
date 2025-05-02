// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayoutDom from "./components/Sidebar/MainLayoutDom.jsx";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import MainPage from "./pages/MainPage.jsx";
import HomePage from "./pages/HomePage.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom />}>
                    <Route index element={<HomePage />} />
                    <Route path="main" element={<MainPage />} />
                </Route>
                <Route path="/login" element={<LoginSignUp />} />
                <Route path="/main" element={<MainPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
