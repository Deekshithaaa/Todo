import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from './security/AuthContext';
import './css/LoginComponent.css';

function LoginComponent() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const navigate = useNavigate();
    const authContext = useAuth();

    function handleUsernameChange(event) {
        setUsername(event.target.value);
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value);
    }

    function handleSubmit() {
        console.log('hello');
        setShowErrorMessage(false);
        authContext.login(username, password)
            .then(isLoggedIn => { 
                if(isLoggedIn){
                    console.log("I am in");
                    navigate(`/welcome`);
                } else {
                    setShowErrorMessage(true);
                }
            })
            .catch(error => {
                console.log(error);
                setShowErrorMessage(true);
            });
    }

    return (
        <div className="Login">
            <h1>Time to Login!</h1>
            {showErrorMessage && <div className="errorMessage">Authentication Failed. Please check your credentials.</div>}
            <div className="LoginForm">
                <div>
                    <label>User Name:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange} />
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>login</button>
                </div>
                <div>
                    <p>Don't have an account? <Link to="/registration">Register</Link></p>
                </div>
            </div>
        </div>
    );
}

export default LoginComponent;
