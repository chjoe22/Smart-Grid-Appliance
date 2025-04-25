import React, {useState} from 'react'
import './LoginSignUp.css'
import user_icon from '../Assets/person.png'
import email_icon from '../Assets/email.png'
import password_icon from '../Assets/password.png'


const LoginSignUp = () => {

    const [action, setAction] = useState("Sign Up");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const handleSubmit = async () => {
        const payload = { email, password };
        if (action === "Sign Up") {
            payload.name = name;
        }

        const endpoint = action === "Login" ? "/api/login" : "/api/signup";

        try {
            const response = await fetch(endpoint, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            const data = await response.json();
            console.log(data); // handle success or error
        } catch (error) {
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
                    {action === "Login" ? <div></div> : <div className="input">
                        <img src={user_icon} alt=""/>
                        <input
                            type="text"
                            placeholder="Enter Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />

                    </div>}

                    <div className="input">
                        <img src={email_icon} alt=""/>
                        <input type="email"
                               placeholder="Enter E-mail"
                               value={email}
                               onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="input">
                        <img src={password_icon} alt=""/>
                        <input type="password"
                               placeholder="Enter Password"
                               value={password}
                               onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                </div>
                {action === "Sign Up" ? <div></div> :
                    <div className="forgot-password">Lost Password? <span>Click Here!</span></div>}

                <div className="submit-container">
                    <div className={action === "Login" ? "submit gray" : "submit"} onClick={() => {
                        setAction("Sign Up")
                    }}>Sign Up
                    </div>
                    <div className={action === "Sign Up" ? "submit gray" : "submit"} onClick={() => {
                        setAction("Login")
                    }}>Login
                    </div>
                </div>
                <div className="submitButton" onClick={handleSubmit}>
                    {action === "Login" ? "Login" : "Submit"}
                </div>

            </div>
        </div>
    );
}

export default LoginSignUp

