import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../App.css"

function Register() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')
    const navigate = useNavigate()
    const [error, setError] = useState('')

    /* The logic that is handling registering a user and connecting to the
    backend register api and then navigating to the login page after 
    regustration. */
    const handleRegister = async () => {
        const response = await fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({username, email, password}),
        })

        // Grabbing all error messages and making them in to one displayable Sting
        if(!response.ok) {
            const errorData = await response.json()
            const messages = Object.entries(errorData).map(([field, message]) => `${field}: ${message}`)
            .join(',')
            setError(messages)
            return
        }
        // Navigating to the login after registration.
        navigate('/login')
    } 

    return (
        <div className = "auth-container">
           <h1> Register</h1>

            <div className = "auth-card">
                <input
                    type = 'text'
                    value = {username}
                    placeholder = "Username"
                    onChange = {(e) => setUsername(e.target.value)}
                    />

                <input
                    type = 'password'
                    value = {password}
                    placeholder = "Password"
                    onChange = {(e) => setPassword(e.target.value)}
                />

                <input
                    type = 'text'
                    value = {email}
                    placeholder = "Email"
                    onChange = {(e) => setEmail(e.target.value)}
                />

           <Link to = "/login"> Already have an account? Sign in here</Link>
            {error && <p className = "error-message">{error}</p>}
                <button onClick = {handleRegister}>
                    Register
                </button>
            </div>
        </div>
    )
}

export default Register;