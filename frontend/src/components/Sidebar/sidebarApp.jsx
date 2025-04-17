import {Button, Layout, theme} from "antd";
import Logo from "../Logo.jsx";
import MenuList from "./MenuList.jsx";
import {useState} from "react";
import ToggleThemeButton from "./ToggleThemeButton.jsx";
import {MenuUnfoldOutlined, MenuFoldOutlined} from '@ant-design/icons'

const {Header, Sider} = Layout;
function App() {
    const [darkTheme, setDarkTheme] = useState(true);
    const [collapsed, setCollapsed] = useState(false)

    const ToggleTheme = ()=>{
        setDarkTheme(!darkTheme);
    };

    const { token: {colorBgContainer}, } = theme.useToken();

    return (
        <Layout>
            <Sider collapsed={collapsed} collapsible trigger={null} theme={darkTheme? 'dark' : 'light'} className="sidebar">
                <Logo/>
                <MenuList darkTheme={darkTheme}/>
                <ToggleThemeButton darkTheme={darkTheme}
                                   ToggleTheme={ToggleTheme}/>
            </Sider>
            <Layout>
                <Header style={{padding: 0, background: colorBgContainer }}>
                    <Button
                        type={"text"}
                        className={"toggle"}
                        onClick={()=> setCollapsed(!collapsed)}
                        icon={collapsed ? <MenuUnfoldOutlined />
                            : <MenuFoldOutlined />}
                    />
                </Header>
            </Layout>
        </Layout>
    )
}

export default App
