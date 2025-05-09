import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayoutDom from "./components/Sidebar/MainLayoutDom.jsx";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import DataPage from "./pages/DataPage.jsx";
import MainPage from "./pages/MainPage.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom />}>
                    <Route index element={<MainPage />} />
                    <Route path="data" element={<DataPage />} />
                    <Route path="/login" element={<LoginSignUp />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
