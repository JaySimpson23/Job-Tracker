import { Navigate, Route } from 'react-router-dom'
import { Routes } from 'react-router-dom'
import Login from './pages/Login'
import './App.css'


function App() {

  return (
    <>
     <Routes>
      <Route path = "/login" element = {<Login/> } />
      <Route path = "/register" element = {<h1>Register Page</h1>} /> 
      <Route path = "/" element = {<Navigate to= "/login" />} />
     </Routes>
    </>
  )
}

export default App
