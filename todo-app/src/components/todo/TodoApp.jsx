import LogoutComponent from './LogoutComponent'
import FooterComponent from './FooterComponent'
import HeaderComponent from './HeaderComponent'
import ListTodosComponent from './LisiTodosComponent'
import WelcomeComponent from './WelcomeComponent';
import ErrorComponent from './ErrorComponent'
import LoginComponent from './LoginComponent';
import TodoComponent from './TodoComponent';
import RegistrationComponent from './RegistrationComponent';
import AuthProvider, { useAuth } from './security/AuthContext'
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import TodayComponent from './TodayComponent';
import UpcomingComponent from './UpcomingComponent';
import FilterComponent from './FilterComponent';
import TagsComponent from './TagsComponent';
import CompleteComponent from './CompletedComponent';
import IncompleteComponent from './IncompleteComponent';
import PendingComponent from './PendingComponent';


function AuthenticatedRoute({children}) {
    const authContext = useAuth()
    if (!authContext.isAuthenticated) {
        return <Navigate to="/" />;
    }
    
        return children

}


export default function TodoApp() {
    
    return (
        <div className="TodoApp">
            <AuthProvider>
            <BrowserRouter>
            <HeaderComponent />
            <div className="content-wrapper">
                <Routes>
                    <Route path='/' element={ <LoginComponent/> }></Route>
                    <Route path='/logout' element={<LogoutComponent /> } />
                    <Route path='/login' element={ <AuthenticatedRoute><LoginComponent /></AuthenticatedRoute> }></Route>
                    <Route path='/registration' element={ <RegistrationComponent/>}></Route>
                    <Route path='/todos' element={ <AuthenticatedRoute><ListTodosComponent /> </AuthenticatedRoute> }></Route>
                    <Route path='/todo/:id' element={ <AuthenticatedRoute><TodoComponent /> </AuthenticatedRoute> }></Route>
                    <Route path='/welcome' element={<AuthenticatedRoute><WelcomeComponent /></AuthenticatedRoute> }></Route>
                    <Route path='/today' element={<AuthenticatedRoute><TodayComponent /></AuthenticatedRoute> }></Route>
                    <Route path='/upcoming' element={<AuthenticatedRoute><UpcomingComponent/></AuthenticatedRoute> }></Route>
                    <Route path='/filters' element={<AuthenticatedRoute><FilterComponent/></AuthenticatedRoute> }></Route>
                    <Route path='/tags' element={<AuthenticatedRoute><TagsComponent/></AuthenticatedRoute> }></Route>
                    <Route path='/completed' element={<AuthenticatedRoute><CompleteComponent/></AuthenticatedRoute> }></Route>
                    <Route path='/incomplete' element={<AuthenticatedRoute><IncompleteComponent/></AuthenticatedRoute> }></Route>
                    <Route path='/pending' element={<AuthenticatedRoute><PendingComponent/></AuthenticatedRoute> }></Route>
                    <Route path='*' element={<ErrorComponent /> }></Route>
                </Routes>
                </div>
                <FooterComponent/>
            </BrowserRouter>
            </AuthProvider>
            
        </div>
    )
}







