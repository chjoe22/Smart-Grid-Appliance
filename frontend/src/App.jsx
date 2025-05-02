import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayoutDom from "./MainLayoutDom";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import HomePage from "./pages/HomePage.jsx";
import DataPage from "./pages/DataPage.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom />}>
                    <Route index element={<HomePage />} />

                </Route>
                <Route path="/login" element={<LoginSignUp />} />
                <Route path="page/data" element={<DataPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
