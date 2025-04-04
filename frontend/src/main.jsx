import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Example from "./example.jsx";
import SignUp from "./pages/SignUp.jsx";
import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <Example />
    <App/>
  </StrictMode>,
)
