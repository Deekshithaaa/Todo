import { retrieveAllTodosForUsernameApi, deleteTodoApi } from './api/TodoApiService'
import { useEffect, useState } from "react"
import { useAuth } from "./security/AuthContext"
import { useNavigate } from 'react-router-dom'
import "./css/ListTodosComponent.css"

function ListTodosComponent() {

    const authContext = useAuth()
    const username = authContext.username
    const navigate = useNavigate()
    const [isModalOpen, setModalOpen] = useState(false);
    const [currentImage, setCurrentImage] = useState(null);


    const [todos, setTodos] = useState([])
    const [message, setMessage] = useState(null)
    useEffect(() => refreshTodos(), [])

    function refreshTodos() {
        retrieveAllTodosForUsernameApi(username)
            .then(response => {
                setTodos(response.data)
            })
            .catch(error => console.log(error))
    }

    function deleteTodo(id) {
        deleteTodoApi(username, id)
            .then(
                () => {
                    setMessage(`Delete of todo with id = ${id} successful`)
                    refreshTodos()
                }
            )
            .catch(error => console.log(error))
    }

    function updateTodo(id) {
        navigate(`/todo/${id}`)
    }

    function addNewTodo() {
        navigate(`/todo/-1`)
    }

    return (
        <div className="container my-5">
            <h1 className="mb-4">Things you want to do</h1>
            
            
            <div className="row">
                {todos.map(todo => (
                    <div className="col-md-4 mb-4" key={todo.id}>
                        <div className="flash-card">
                            <div className="card-front">
                                <h5>{todo.description}</h5>
                                <p>Status: {todo.done ? '✅' : '❌'}</p>
                            </div>
                            <div className="card-back">
                                <p>Target Date: {todo.targetDate.toString()}</p>
                                <p>Priority: {todo.priority}</p>
                                <p>Comments: {todo.comments}</p>
                                <p>Tags: {todo.tags}</p>
                                {todo.attachmentData && 
                                    <img 
                                        src={`data:${todo.attachmentType};base64,${todo.attachmentData}`} 
                                        alt="attachment" 
                                        width="50" 
                                        height="50" 
                                        onClick={() => {
                                            setCurrentImage(`data:${todo.attachmentType};base64,${todo.attachmentData}`);
                                            setModalOpen(true);
                                        }}
                                    />
                                }

                                <div className="button-group mt-3">
                                    <button className="delete-button" onClick={() => deleteTodo(todo.id)}>Delete</button>
                                    <button className="update-button" onClick={() => updateTodo(todo.id)}>Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}

                    {isModalOpen && 
                        <div className="modal-overlay" onClick={() => setModalOpen(false)}>
                            <img src={currentImage} alt="Enlarged attachment" className="modal-image"/>
                        </div>
                    }

            </div>
            
            <button className="btn-funky m-5" onClick={addNewTodo}>Add New Todo</button>
        </div>
    );
}

export default ListTodosComponent
