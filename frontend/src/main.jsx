import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import NameExample from "./example.jsx";
import MUI from "./MUI.jsx";

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <MUI />
      <NameExample />
      <App />
  </StrictMode>,
)
