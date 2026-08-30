import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function Register() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')
    const navigate = useNavigate()

    /* The logic that is handling registering a user and connecting to the
    backend register api and then navigating to the login page after 
    regustration. */
    const handleRegister = async () => {
        const response = await fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({username, email, password}),
        })
        // Navigating to the login after registration.
        navigate('/login')
    } 

    return (
        <div>
           <h1> Register</h1>

           <input
           type = 'text'
           value = {username}
           onChange = {(e) => setUsername(e.target.value)}
           />

            <input
           type = 'password'
           value = {password}
           onChange = {(e) => setPassword(e.target.value)}
           />

             <input
           type = 'text'
           value = {email}
           onChange = {(e) => setEmail(e.target.value)}
           />

           <Link to = "/login"> Already have an account? Sign in here</Link>

           <button onClick = {handleRegister}>
            Register
           </button>
        </div>
    )
}

export default Register;