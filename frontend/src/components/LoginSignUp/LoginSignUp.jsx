import React, {useState} from 'react'
import './LoginSignUp.css'
import user_icon from '../Assets/person.png'
import email_icon from '../Assets/email.png'
import password_icon from '../Assets/password.png'
import {useNavigate} from 'react-router-dom';

const LoginSignUp = () => {
    const [action, setAction] = useState("Sign Up");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async () => {
        const payload = {
            username: email,
            email: email,
            password: password
        };
        if (action === "Sign Up") {
            payload.name = name;
        }

        const endpoint = `http://localhost:8080${action === "Login" ? "/api/login" : "/api/register"}`;

        try {
            const response = await fetch(endpoint, {
                mode: "no-cors",
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || "Request failed");
            }

            const data = await response.text();
            console.log("Success:", data);


            if (response.ok) {
                setMessage("Login was a Success!");
                if (action === "Login") {
                    setTimeout(() => navigate("/MainPage"), 1000);
                }
            } else {
                setMessage(`Error: ${data.message || "Something went wrong"}`);
            }
        } catch (error) {
            setMessage("Network error");
            console.error("Error:", error);
        }
    };

    return (
        <div className="login-wrapper">
            <div className='container'>
                <div className="header">
                    <div className="text">{action}</div>
                    <div className="underline"></div>
                </div>
                <div className="inputs">
                    {action === "Login" ? null : (
                        <div className="input">
                            <img src={user_icon} alt=""/>
                            <input
                                type="text"
                                placeholder="Enter Name"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                    )}
                    <div className="input">
                        <img src={email_icon} alt=""/>
                        <input type="email" placeholder="Enter E-mail" value={email}
                               onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="input">
                        <img src={password_icon} alt=""/>
                        <input type="password" placeholder="Enter Password" value={password}
                               onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                </div>

                {message && <div className="message">{message}</div>}

                <div className="submit-container">
                    <div
                        className={action === "Login" ? "submit gray" : "submit"}
                        onClick={() => {
                            if (action === "Sign Up") handleSubmit();
                            else setAction("Sign Up");
                        }}
                    >
                        Sign Up
                    </div>
                    <div
                        className={action === "Sign Up" ? "submit gray" : "submit"}
                        onClick={() => {
                            if (action === "Login") handleSubmit();
                            else setAction("Login");
                        }}
                    >
                        Login
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginSignUp;
