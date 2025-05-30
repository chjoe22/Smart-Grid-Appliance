import { Menu } from "antd";
import {
    HomeOutlined,
    AppstoreOutlined,
    AreaChartOutlined,
    PayCircleOutlined,
    SettingOutlined,
    BarsOutlined,
    LogoutOutlined
} from '@ant-design/icons';
import { Link, useNavigate } from "react-router-dom";
import {useAuth} from "../authContext/AuthContext.jsx";

const MenuList = ({ darkTheme }) => {
    const navigate = useNavigate();
    const { logout, user } = useAuth();

    return (
        <Menu
            theme={darkTheme ? 'dark' : 'light'}
            mode="inline"
            className="menu-bar"
        >
            <Menu.Item key="home" icon={<HomeOutlined />}>
                <Link to="/">Home</Link>
            </Menu.Item>
            {!user && (
            <Menu.Item key="login" icon={<AppstoreOutlined />}>
                <Link to="/login">Login</Link>
            </Menu.Item>
            )}
            <Menu.Item key="data" icon={<AreaChartOutlined />}>
                <Link to="/data">Data</Link>
            </Menu.Item>
            <Menu.Item key="edit" icon={<PayCircleOutlined />}>
                <Link to="/edit">Edit</Link>
            </Menu.Item>
            {user && (
                <Menu.Item key="users" icon={<SettingOutlined />}>
                    <Link to="/users">Users</Link>
                </Menu.Item>
            )}

            {user && (
                <Menu.Item key="logout" icon={<LogoutOutlined />} onClick={() => {
                    logout();
                    navigate("/");
                }}>
                    Logout
                </Menu.Item>
                )}

        </Menu>
    );
};

export default MenuList;
