import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

function CreateApplication() {
    const [companyName, setCompanyName] = useState('')
    const [position, setPosition] = useState('')
    const [status, setStatus] = useState('')
    const [applied, setApplied] = useState('')
    const [notes, setNotes] = useState('')
    const navigate = useNavigate()

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
        navigate('/dashboard')
    }

    return (
        <div>
            <h1>Create Application</h1>

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

           <button onClick = {handleCreate }>
            Create
           </button>
        </div>
    )
}
export default CreateApplication;