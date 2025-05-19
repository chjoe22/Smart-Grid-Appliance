import {BrowserRouter, Routes, Route} from "react-router-dom";
import MainLayoutDom from "./components/Sidebar/MainLayoutDom.jsx";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";
import DataPage from "./pages/data-Page.jsx";
import MainPage from "./pages/main-Page.jsx";
import EditPage from "./pages/edit-Page.jsx";
import PrivateRouting from "./components/authContext/PrivateRouting.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom/>}>
                    <Route index element={<MainPage/>}/>

                    <Route path="data" element={
                        <PrivateRouting>
                        <DataPage/>
                    </PrivateRouting>}/>

                    <Route path="login" element={<LoginSignUp/>}/>

                    <Route path="edit" element={
                        <PrivateRouting>
                        <EditPage/>
                        </PrivateRouting>}/>

                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
