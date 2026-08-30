import {useState} from 'react'
import { Link, useNavigate } from 'react-router-dom'


function Login() {
    const [identifier, setIdentifier] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()

    /* The logic handling the login process for the user which is connecting to
    the backend Login api. */   
    const handleLogin = async () => {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body:  JSON.stringify({identifier, password}),
        })

        // Saving the token to local storage.
        const token = await response.text()
        localStorage.setItem('token', token)
        navigate('/dashboard')

    }


    return (
        <div>
            <h1>Login</h1>

            <input 
            type = "text"
            value = {identifier}
            onChange = {(e) => setIdentifier(e.target.value)}
            />

            <input 
            type = "password"
            value = {password}
            onChange = {(e) => setPassword(e.currentTarget.value)}
            />

            <Link to = "/register"> Don't have an account? Register here </Link>

            <button onClick = {handleLogin}>
                Login
            </button>
        </div>
    )
}
export default Login;