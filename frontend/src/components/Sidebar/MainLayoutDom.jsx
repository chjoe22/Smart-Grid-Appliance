import { useState } from "react";
import { Button, Layout, theme } from "antd";
import { MenuUnfoldOutlined, MenuFoldOutlined } from "@ant-design/icons";
import Logo from "../Logo.jsx";
import MenuList from "./MenuList.jsx";
import ToggleThemeButton from "./ToggleThemeButton.jsx";
import { Outlet } from "react-router-dom";
import { useAuth } from "../authContext/AuthContext.jsx";

const { Header, Sider, Content } = Layout;

export default function MainLayoutDom() {
    const [darkTheme, setDarkTheme] = useState(true);
    const [collapsed, setCollapsed] = useState(false);

    const { user } = useAuth();

    const toggleTheme = () => {
        setDarkTheme((prev) => !prev);
    };

    const {
        token: { colorBgContainer },
    } = theme.useToken();

    return (
        <Layout style={{ minHeight: "100vh", display: "flex", flexDirection: "row" }}>
            <Sider
                collapsed={collapsed}
                collapsible
                trigger={null}
                theme={darkTheme ? "dark" : "light"}
                className="sidebar"
            >
                <Logo/>

                <div style={{
                    padding: "12px 16px",
                    textAlign: "center",
                    color: darkTheme ? "#fff" : "#000",
                    fontSize: 12,
                    lineHeight: 1.4,
                }}>
                    You are logged in as:<br />
                    <strong> {user?.username || "no one"}</strong>
                </div>


                <MenuList darkTheme={darkTheme}/>
                <ToggleThemeButton darkTheme={darkTheme} ToggleTheme={toggleTheme}/>
            </Sider>

            <Layout style={{flex: 1, display: "flex", flexDirection: "column"}}>
                <Header style={{padding: 0, background: colorBgContainer}}>
                    <Button
                        type="text"
                        className="toggle"
                        onClick={() => setCollapsed((prev) => !prev)}
                        icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
                    />
                </Header>

                <Content style={{
                    padding: 16,
                    width: '100%',
                    maxWidth: '100vw',
                    flex: 1,
                    overflowX: 'auto',
                    boxSizing: 'border-box'
                }}>
                    <Outlet />
                </Content>
            </Layout>
        </Layout>
    );
}
