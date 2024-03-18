import {
    retrieveAllTodosForUsernameApi,
    deleteTodoApi,
    retrieveTodosByPriorityApi,
    retrieveTodosSortedByPriorityApi
} from './api/TodoApiService';
import { useEffect, useState } from "react";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from 'react-router-dom';
import moment from 'moment';  // Assuming you've installed moment.js

function FilterComponent() {
    const authContext = useAuth();
    const username = authContext.username;
    const navigate = useNavigate();

    const [todos, setTodos] = useState([]);
    const [message, setMessage] = useState(null);
    const [selectedPriority, setSelectedPriority] = useState(null);

    const priorities = ["Priority 1", "Priority 2", "Priority 3", "Priority 4"];

    useEffect(() => {
        refreshTodos();
    }, [selectedPriority]);

    function refreshTodos() {
        if (selectedPriority) {
            retrieveTodosByPriorityApi(username, selectedPriority)
                .then(response => setTodos(response.data))
                .catch(error => {
                    console.log(error);
                    setMessage("Error occurred while fetching todos. Please try again.");
                });
        } else {
            retrieveTodosSortedByPriorityApi(username)
                .then(response => setTodos(response.data))
                .catch(error => {
                    console.log(error);
                    setMessage("Error occurred while fetching todos. Please try again.");
                });
        }
    }

    function handlePriorityClick(priority) {
        setSelectedPriority(priority === selectedPriority ? null : priority); // Toggle
    }

    function deleteTodo(id) {
        deleteTodoApi(username, id)
            .then(() => {
                setMessage(`Delete of todo with id = ${id} successful`);
                refreshTodos();
            })
            .catch(error => console.log(error));
    }

    function updateTodo(id) {
        navigate(`/todo/${id}`);
    }

    function addNewTodo() {
        navigate(`/todo/-1`);
    }
    
   

    return (
        <div className="container my-5">
            <h1 className="mb-4">Filter todos by PRIORITY</h1>
            {message && <div className="alert alert-warning">{message}</div>}
            <div className="tag-container mb-4">
                <span 
                    className={`tag-chip ${!selectedPriority ? 'selected' : ''}`}
                    onClick={() => handlePriorityClick(null)}
                >
                    All Todos (sorted by priority)
                </span>


                {priorities.map(priority => (
                    
                    <span 
                        key={priority}
                        className={`tag-chip ${selectedPriority === priority ? 'selected' : ''}`}
                        onClick={() => handlePriorityClick(priority)}
                    >
                        {priority}
                    </span>
                ))}
            </div>

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
                                {todo.attachmentData && <img src={`data:${todo.attachmentType};base64,${todo.attachmentData}`} alt="attachment" width="50" height="50" />}
                                <div className="button-group mt-3">
                                    <button className="delete-button" onClick={() => deleteTodo(todo.id)}>Delete</button>
                                    <button className="update-button" onClick={() => updateTodo(todo.id)}>Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            <button className="btn-funky m-5" onClick={addNewTodo}>Add New Todo</button>
        </div>
    );
}

export default FilterComponent;
