import {useState} from 'react'
import { Link, useNavigate } from 'react-router-dom'
import '../App.css'


function Login() {
    const [identifier, setIdentifier] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const [ error, setError] = useState('')

    /* The logic handling the login process for the user which is connecting to
    the backend Login api. */   
    const handleLogin = async () => {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body:  JSON.stringify({identifier, password}),
        })

        // return a error for invalid credentials promt to try again
        if(!response.ok) {
            setError('Invalid credentials. Please try again.')
            return
        }

        // Saving the token to local storage.
        const token = await response.text()
        localStorage.setItem('token', token)
        navigate('/dashboard')

    }


    return (
        <div className = "auth-container">  
            <h1>Login</h1>

            <div className = "auth-card">
                 <input 
                    type = "text"
                    value = {identifier}
                    placeholder = "Email or Username"
                    onChange = {(e) => setIdentifier(e.target.value)}
                />

                <input 
                    type = "password"
                    value = {password}
                    placeholder = "Password"
                    onChange = {(e) => setPassword(e.currentTarget.value)}
                />

            <Link to = "/register"> Don't have an account? Register here </Link>

            {error && <p className = "error-message">{error}</p>}
                <button onClick = {handleLogin}>
                    Login
                </button>
            </div>
        </div>
    )
}
export default Login;