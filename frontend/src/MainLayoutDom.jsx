// src/MainLayoutDom.jsx
import { useState } from "react";
import { Button, Layout, theme } from "antd";
import { MenuUnfoldOutlined, MenuFoldOutlined } from "@ant-design/icons";
import Logo from "./components/Logo";
import MenuList from "./components/Sidebar/MenuList";
import ToggleThemeButton from "./components/Sidebar/ToggleThemeButton";
import { Outlet } from "react-router-dom";

const { Header, Sider, Content } = Layout;

export default function MainLayoutDom() {
    const [darkTheme, setDarkTheme] = useState(true);
    const [collapsed, setCollapsed] = useState(false);

    const toggleTheme = () => {
        setDarkTheme((prev) => !prev);
    };

    const {
        token: { colorBgContainer },
    } = theme.useToken();

    return (
        <Layout style={{ minHeight: "100vh" }}>
            <Sider
                collapsed={collapsed}
                collapsible
                trigger={null}
                theme={darkTheme ? "dark" : "light"}
                className="sidebar"
            >
                <Logo />
                <MenuList darkTheme={darkTheme} />
                <ToggleThemeButton darkTheme={darkTheme} ToggleTheme={toggleTheme} />
            </Sider>

            <Layout>
                <Header style={{ padding: 0, background: colorBgContainer }}>
                    <Button
                        type="text"
                        className="toggle"
                        onClick={() => setCollapsed((prev) => !prev)}
                        icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
                    />
                </Header>

                <Content style={{ margin: "16px" }}>
                    <Outlet />
                </Content>
            </Layout>
        </Layout>
    );
}
