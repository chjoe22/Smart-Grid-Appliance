import LoginSignUp from "./components/LoginSignUp/LoginSignUp.jsx";

export default App
import React, { useState } from 'react'
import reactLogo from './components/assets/react.svg'
import './App.css'
import Example from "./example.jsx";
import SidebarApp from "./components/Sidebar/SidebarApp.jsx";

function App() {
    const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
        <div>
            <LoginSignUp />
            <SidebarApp />
        </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
        <Example/>
    </>
  )
}