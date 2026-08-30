import { Navigate, Route } from 'react-router-dom'
import { Routes } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import './App.css'
import Dashboard from './pages/Dashboard'
import CreateApplication from './pages/CreateApplication'

// Handles all the full user journey between pages through the Route tags below.
function App() {

  return (
    <>
     <Routes>
      <Route path = "/login" element = {<Login/> } />
      <Route path = "/register" element = {<Register/> } /> 
      <Route path = "/" element = {<Navigate to= "/login" />} />
      <Route path = "/dashboard" element = {<Dashboard />} />
      <Route path = "/create-application" element = {<CreateApplication />} />
     </Routes>
    </>
  )
}

export default App
