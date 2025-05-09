// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayout from "./MainLayoutDom";
import HomePage from "./pages/HomePage";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import MainPage from "./pages/MainPage.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayout />}>
                    <Route index element={<HomePage />} />
                    <Route path={"login"} element={<LoginSignUp/> }/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
