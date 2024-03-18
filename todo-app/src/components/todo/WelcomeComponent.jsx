import { Link } from 'react-router-dom';
import { useAuth } from './security/AuthContext';
import './css/WelcomeComponent.css';
import backgroundImage from './todobg.avif'; 


function WelcomeComponent() {

    const authContext = useAuth();

    const containerStyle = {
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat'
    };

    return (
        <div className="welcome-container" style={containerStyle}>
            <div className="welcome-message">
                <h1>Welcome, <span>{authContext.username}</span></h1>
            </div>

            <div className="navigation-options">
                <ul>
                    <li><Link to="/todos"><i className="icon-calendar"></i>View all Todos</Link></li>
                    <li><Link to="/today"><i className="icon-calendar"></i>Today</Link></li>
                    <li><Link to="/upcoming"><i className="icon-calendar"></i>Upcoming</Link></li>
                    <li><Link to="/filters"><i className="icon-search"></i>View by Filters</Link></li>
                    <li><Link to="/tags"><i className="icon-tag"></i>View by Tags</Link></li>
                    <li><Link to="/completed"><i className="icon-check"></i>Completed Tasks</Link></li>
                    <li><Link to="/incomplete"><i className="icon-close"></i>InComplete Tasks</Link></li>
                    <li><Link to="/pending"><i className="icon-alert"></i>Overdue tasks</Link></li>
                </ul>
            </div>
        </div>
    );
}

export default WelcomeComponent;
