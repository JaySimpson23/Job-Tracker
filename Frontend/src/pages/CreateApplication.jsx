import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import "../App.css"

function CreateApplication() {
    const [companyName, setCompanyName] = useState('')
    const [position, setPosition] = useState('')
    const [status, setStatus] = useState('')
    const [applied, setApplied] = useState('')
    const [notes, setNotes] = useState('')
    const navigate = useNavigate()
    const [error, setError] = useState('')

     useEffect(() => {
        const token = localStorage.getItem('token')

        if(!token) {
            navigate('/login')
            return
        }
    },[])

    const handleCreate = async () => {
        const token = localStorage.getItem('token')
        const response = await fetch ('http://localhost:8080/api/applications', {
            method: 'POST',
            headers: {'Content-Type': 'application/json','Authorization': `Bearer ${token}` },
            body: JSON.stringify({companyName, position, status, applied, notes})
        })

        /* Grabbing all errors and making them one displayable string, 
        including field names */
        if(!response.ok) {
            const errorData = await response.json()
            const messages = Object.entries(errorData).map(([field, message]) => `${field}: ${message}`)
            .join(',')
            setError(messages)
            return
        }
        navigate('/dashboard')
    }

    return (
        <div className = "form-container">
            <h1>Create Application</h1>

            <div className = "form-card">
                <input
                    type = 'text'
                    value = {companyName} 
                    placeholder = 'Company Name'
                    onChange = {(e) => setCompanyName(e.target.value)}
                />

                <input
                    type = 'text'
                    value = {position}
                    placeholder = 'Position'
                    onChange = {(e) => setPosition(e.target.value)}
                />

                <input 
                    type = 'text'
                    value = {status}
                    placeholder = 'status'
                    onChange = {(e) => setStatus(e.target.value)}
                />

                <input
                    type = 'datetime-local'
                    value = {applied}
                    placeholder = 'Date Applied'
                    onChange = {(e) => setApplied(e.target.value)}
                />

                <textarea
                    value = {notes}
                    placeholder = 'notes'
                    onChange = {(e) => setNotes(e.target.value)}
                />

            {error && <p className = "error-message">{error}</p>}
                <button onClick = {handleCreate }>
                    Create
                </button>
           </div>
        </div>
    )
}
export default CreateApplication;