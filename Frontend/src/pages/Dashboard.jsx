import { Link, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useState } from "react";
import "../App.css"

function Dashboard() {
    const navigate = useNavigate()
    const [applications, setApplications] = useState([])
    const [editingId, setEditingId] = useState(null)
    const [editForm, setEditForm] = useState({companyName: '', position: '', status: ''})
    const [loading, setLoading] = useState(true)
    const [applicationCount, setApplicationCount] = useState(0)

    /* Getting the user that was stored to localstorage and navigating to their 
    dashboard page and if not redirects to the login page */
    useEffect(() => {
        const token = localStorage.getItem('token')

        if(!token) {
            navigate('/login')
            return
        }
        /* Gets the applications from the users account and needs the bearer token 
        for authorization because the backend endpoints are not set to permitAll()
        and retrives the applications data that is stored. */
        const fetchApplication = async () => {
            const response = await fetch('http://localhost:8080/api/applications', {
                headers: {'Authorization': `Bearer ${token}` }
            })
            /* If the response is not a 200ok log the user out and go back to
            login which is most likely a expired token */
            if(!response.ok) {
                navigate('/login')
                return
            }
            const data = await response.json()
            setApplications(data)
            setLoading(false)
        }

        fetchApplication()

        const fetchApplicationCount = async () => {
            const response = await fetch('http://localhost:8080/api/users/me', {
                headers: {'Authorization': `Bearer ${token}` }
            })
            if(!response.ok) {
                return
            }

            const data = await response.json()
            setApplicationCount(data.applicationCount)
        }

        fetchApplicationCount()
    }, []) 

    /*Delete an application and filter the new array to bring the remaining
    applications to the screen*/
    const handleDelete = async (id) => {
        const confirmed = window.confirm("Are you sure you want to delete this application")
        if(!confirmed) {
            return
        }
        
        const token = localStorage.getItem('token')
        await fetch(`http://localhost:8080/api/applications/${id}`, {
            method: 'DELETE',
            headers: {'Authorization': `Bearer ${token}`}
        })
        setApplications(applications.filter((app) => app.id !== id))
    }

    const handleLogout = () => {
        localStorage.removeItem('token')
        navigate('/login')
    }

    //Saving Logic for the applications that were edited
    const handleSave = async (id) => {
        const token = localStorage.getItem('token')
        const response = await fetch(`http://localhost:8080/api/applications/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(editForm)
        })
        //updating React's state for the application after editing it
        const updated = await response.json()
        setApplications(applications.map((app) => (app.id === id ? updated : app)))
        setEditingId(null)
    }

    /* Displayes the applications values (Company name, job position and status.
    Also has a cause for if there are no application for the user it tells them
    they have none and prompts for them to create one*/
    return (
        <div className = "dashboard-container">
            <h1>Dashboard</h1>
            <p> You've applied to {applicationCount} jobs so far!</p>
             <Link to = "/create-application"> Create Application </Link>
             <button onClick = {handleLogout}>
                Logout
             </button>
        {loading ? (
            <p>Loading your applications...</p>
        ) : applications.length ===0 ? (
            <p>You haven't added any applications yet- create your first one!</p>
        ) : (
            <ul className = "application-list">
                {applications.map((app) => (
                    <li key = {app.id} className = "application-item">
                        {app.id === editingId ? (
                            <div>
                                <input 
                                value = {editForm.companyName}
                                onChange = {(e) => setEditForm({ ...editForm, companyName: e.target.value })}
                                />

                                 <input 
                                value = {editForm.position}
                                onChange = {(e) => setEditForm({ ...editForm, position: e.target.value })}
                                />

                                 <input 
                                value = {editForm.status}
                                onChange = {(e) => setEditForm({...editForm, status: e.target.value })}
                                />

                            <button onClick = {() => handleSave(app.id)} className = "btn-save">
                                Save
                            </button>
                            </div>
                        ) : (
                           <div>
                            {app.companyName} - {app.position} - {app.status}
                           </div> 
                        )}
                       <button onClick = {() => handleDelete(app.id)} className = "btn-delete">
                        Delete
                       </button>
                       <button onClick = {() => {
                        setEditingId(app.id)
                        setEditForm({companyName: app.companyName, position: app.position, status: app.status})
                    }} className = "btn-edit">
                        Edit
                       </button>
                    </li>
                ))}
            </ul>
        )}
        </div>
    )
}

export default Dashboard;
