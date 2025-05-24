import React, { useEffect, useState } from 'react';
import '../style/userListStyle.css';
import {useAuth} from "../components/authContext/AuthContext.jsx";

const UsersPage = () => {
    const { user: authUser } = useAuth();
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/users").then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((data) => {
            setUsers(data);
        })
        .catch((error) => {
            setError(error);
        });
    },[]);

    console.log(authUser);
    const handleRoleChange = (userId, newRole) => {
        fetch(`http://localhost:8080/api/users/${userId}/role`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ role: newRole }),
        }).then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            setUsers((prevUsers) =>
                prevUsers.map(user => user.id === userId ? { ...user, role: newRole } : user));
        }).catch((error) => {
            setError(error);
        })
    };

    return (
        <div className="user-container">
            <h2 className="user-title">Members Display for {"Smart Grid Appliance"}</h2>
            {error && <div className="error-message">Error: {error.message}</div>}
            <div className="user-header">
                <div>ID</div>
                <div>Name</div>
                <div>Position</div>
                <div>Email</div>
            </div>
            {users.map((user) => (
                <div key={user.id} className="user-row">
                    <div className="user-id">{user.id}</div>
                    <div className="user-name">{user.username}</div>
                    <div>{authUser?.role === 'ADMIN' && user.role !== 'ADMIN' ? (
                            <select value={user.role} onChange={(e) => handleRoleChange(user.id, e.target.value)}>
                                <option value="USER">USER</option>
                                <option value="MANAGER">MANAGER</option>
                            </select>
                        ): (
                            <div className="user-position">
                                {user.role}
                            </div>
                        )}
                    </div>
                    <div className="user-email">{user.email}</div>
                </div>
            ))}
        </div>
    );
}
export default UsersPage;