import { retrieveAllTodosForUsernameApi, deleteTodoApi, retrieveTodosByTagApi, tagApi } from './api/TodoApiService';
import { useEffect, useState } from "react";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from 'react-router-dom';
import "./css/ListTodosComponent.css"; // Keeping the CSS import for similar look

function TagsComponent() {
    const authContext = useAuth();
    const username = authContext.username;
    const navigate = useNavigate();
    const [todos, setTodos] = useState([]);
    const [message, setMessage] = useState(null);
    const [selectedTag, setSelectedTag] = useState(null);
    const [tags, setTags] = useState([]);

    

    useEffect(() => {
        refreshTodos();
    }, [selectedTag]);

    function refreshTodos() {
        if (selectedTag) {
            retrieveTodosByTagApi(username, selectedTag)
                .then(response => {
                    setTodos(response.data);
                })
                .catch(error => console.log(error));
        } else {
            retrieveAllTodosForUsernameApi(username)
                .then(response => {
                    setTodos(response.data);
                })
                .catch(error => console.log(error));
        }
    }

    useEffect(() => {
        fetchTags();
    }, []);
    
    function fetchTags() {
        tagApi(username)
            .then(response => {
                setTags(response.data);
            })
            .catch(error => console.log(error));
    }
    
    function handleTagClick(tag) {
        setSelectedTag(tag === selectedTag ? null : tag); // Toggle
    }

    function deleteTodo(id) {
            console.log('clicked ' + id)
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
            console.log('clicked ' + id)
            navigate(`/todo/${id}`)
        }
    function addNewTodo() {
        navigate(`/todo/-1`)
        }
    
        function handleTagChange(event) {
            setSelectedTag(event.target.value);
        }
    
        return (
            <div className="container my-5">
                <h1 className="mb-4">Filter todos by TAGS</h1>
                {message && <div className="alert alert-warning">{message}</div>}
    
                <div className="tag-container mb-4">
    <span 
        className={`tag-chip ${!selectedTag ? 'selected' : ''}`}
        onClick={() => handleTagClick(null)}
    >
        All Todos
    </span>

    {tags
        .filter(tag => tag !== '')  // Filtering out the "no" tag
        .map(tag => (
            <span 
                key={tag} 
                className={`tag-chip ${selectedTag === tag ? 'selected' : ''}`}
                onClick={() => handleTagClick(tag)}
            >
                {tag}
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
    
    export default TagsComponent;