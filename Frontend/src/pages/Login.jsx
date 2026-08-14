import {useState} from 'react'

function Login() {
    const [identifier, setIdentifier] = useState('')
    const [password, setPassword] = useState('')

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

            <button onClick = {() => console.log(identifier, password)}>
                Login
            </button>
        </div>
    )
}
export default Login;