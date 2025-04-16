import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'Frontend/src/index.css'
import App from 'Frontend/src/App.jsx'

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <App />
    </StrictMode>,
)

/** skal slettes og sørge for at Main har styr på sidebarApp **/