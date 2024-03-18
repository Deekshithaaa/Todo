import { Link } from 'react-router-dom';
import { useAuth } from './security/AuthContext';
import './css/HeaderComponent.css'

function HeaderComponent() {
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated;

    function logout() {
        authContext.logout();
    }

    return (
        <header className="header bg-green py-3">
            <div className="container">
                <div className="d-flex justify-content-between align-items-center">
                    <div>
                        {isAuthenticated && <Link className="link-text" to="/welcome">🏠 Home</Link>}
                        {isAuthenticated && <Link className="link-text ml-3" to="/todos">Todos</Link>}
                    </div>
                    <div>
                        {!isAuthenticated && <Link className="link-text" to="/registration">Registration</Link>}
                        {!isAuthenticated && <Link className="link-text ml-3" to="/login">Login</Link>}
                        {isAuthenticated && <Link className="link-text ml-3" to="/logout" onClick={logout}>Logout</Link>}
                    </div>
                </div>
            </div>
        </header>
    )
}

export default HeaderComponent;
