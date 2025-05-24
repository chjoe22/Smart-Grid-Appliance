import {BrowserRouter, Routes, Route} from "react-router-dom";
import MainLayoutDom from "./components/Sidebar/MainLayoutDom.jsx";
import LoginSignupPage from "./pages/login-signup-page.jsx";
import DataPage from "./pages/data-Page.jsx";
import MainPage from "./pages/main-Page.jsx";
import EditPage from "./pages/edit-Page.jsx";
import UsersPage from "./pages/users-page.jsx";
import PrivateRouting from "./components/authContext/PrivateRouting.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayoutDom/>}>
                    <Route index element={
                        <PrivateRouting>
                        <MainPage/>
                        </PrivateRouting>}/>

                    <Route path="data" element={
                        <PrivateRouting>
                        <DataPage/>
                    </PrivateRouting>}/>

                    <Route path="login" element={<LoginSignupPage/>}/>

                    <Route path="edit" element={
                        <PrivateRouting>
                        <EditPage/>
                        </PrivateRouting>}/>

                    <Route path="users" element={
                        <PrivateRouting>
                        <UsersPage/>
                        </PrivateRouting>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
