import React, {useState} from 'react'
import './LoginSignUp.css'
import {useNavigate} from 'react-router-dom';
import './LoginSignUp.css';
import user_icon from '../Assets/person.png';
import email_icon from '../Assets/email.png';
import password_icon from '../Assets/password.png';

const LoginSignUp = () => {
    const [isLogin, setIsLogin] = useState(false);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const handleRegister = async () => {
        const payload = {
            username: email,
            email,
            password
        };

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            const data = await response.text();

            if (response.ok) {
                setMessage("Registration successful!");
            } else {
                setMessage(`Error: ${data}`);
            }
        } catch (error) {
            setMessage("Network error during registration.");
            console.error("Register Error:", error);
        }
    };

    const handleLogin = async () => {
        const payload = {
            email,
            password
        };

        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            const data = await response.text();

            if (response.ok) {
                setMessage("Login successful!");
                setTimeout(() => navigate("/"), 1000);
            } else {
                setMessage(`Error: ${data}`);
            }
        } catch (error) {
            setMessage("Network error during login.");
            console.error("Login Error:", error);
        }
    };

    return (
        <div className="login-wrapper">
            <div className='container'>
                <div className="header">
                    <div className="text">{isLogin ? "Login" : "Sign Up"}</div>
                    <div className="underline"></div>
                </div>

                <div className="inputs">
                    {!isLogin && (
                        <div className="input">
                            <img src={user_icon} alt="user" />
                            <input
                                type="text"
                                placeholder="Enter Name"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                    )}

                    <div className="input">
                        <img src={email_icon} alt="email" />
                        <input
                            type="email"
                            placeholder="Enter Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>

                    <div className="input">
                        <img src={password_icon} alt="password" />
                        <input
                            type="password"
                            placeholder="Enter Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                </div>

                {message && <div className="message">{message}</div>}

                <div className="submit-container">
                    <div className="submit" onClick={handleRegister}>
                        Register
                    </div>
                    <div className="submit" onClick={handleLogin}>
                        Login
                    </div>
                </div>

                <div className="toggle-mode">
                    <span onClick={() => setIsLogin(!isLogin)}>
                        {isLogin ? "Don't have an account? Sign up here." : "Already have an account? Log in here."}
                    </span>
                </div>
            </div>
        </div>
    );
};

export default LoginSignUp;
