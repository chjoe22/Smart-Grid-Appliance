import {Menu} from "antd";
import {HomeOutlined, AppstoreOutlined, AreaChartOutlined, PayCircleOutlined, SettingOutlined, BarsOutlined} from '@ant-design/icons'
import { Link } from "react-router-dom";

const MenuList = ({ darkTheme }) => {
    return (
        <Menu
            theme={darkTheme ? 'dark' : 'light'}
            mode="inline"
            className="menu-bar"
        >
            <Menu.Item key="home" icon={<HomeOutlined />}>
                <Link to="/">Home</Link>
            </Menu.Item>
            <Menu.Item key="login" icon={<AppstoreOutlined />}>
                <Link to="/login">Login</Link>
            </Menu.Item>
            <Menu.SubMenu key="subtasks" icon={<BarsOutlined />} title="Tasks">
                <Menu.Item key="task-1">
                    <Link to="/task-1">Task 1</Link>
                </Menu.Item>
                <Menu.Item key="task-2">
                    <Link to="/task-2">Task 2</Link>
                </Menu.Item>
                <Menu.SubMenu key="subtaskMenu" title="SubtaskMenu">
                    <Menu.Item key="SubtaskMenu-1">
                        <Link to="/subtask-1">SubtaskMenu 1</Link>
                    </Menu.Item>
                    <Menu.Item key="SubtaskMenu-2">
                        <Link to="/subtask-2">SubtaskMenu 2</Link>
                    </Menu.Item>
                </Menu.SubMenu>
            </Menu.SubMenu>
            <Menu.Item key="progress" icon={<AreaChartOutlined />}>
                <Link to="/progress">Progress</Link>
            </Menu.Item>
            <Menu.Item key="payment" icon={<PayCircleOutlined />}>
                <Link to="/payment">Payment</Link>
            </Menu.Item>
            <Menu.Item key="setting" icon={<SettingOutlined />}>
                <Link to="/setting">Setting</Link>
            </Menu.Item>
        </Menu>
    );
};

export default MenuList;
