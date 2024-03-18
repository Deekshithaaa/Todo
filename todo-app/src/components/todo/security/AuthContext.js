import { createContext, useContext, useState } from "react";
import { checkUserApi } from "../api/TodoApiService";
//1: Create a Context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //Put some state in the context
   
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [username, setUsername] = useState(null)

    
    function login(username, password) {

        const userRegistration = {
            username,
            password,
            
          };
        
          return checkUserApi(userRegistration)
          .then(response => {
              if(response.data === true){
                  console.log("response data");
                  setAuthenticated(true);
                  setUsername(username);
                  return true; // Resolve with true
              } else if(response.data === false) {
                  console.log("Enter correct details");
                  console.log(response);
                  setAuthenticated(false);
                  setUsername(null);
                  return false; // Resolve with false
              }
          })
          .catch(error => {
              console.log(error);
              setAuthenticated(false);
              setUsername(null);
              return Promise.reject(error); // Propagate the error
          });
          
    }
    function logout() {
        setAuthenticated(false)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout,username}  }>
            {children}
        </AuthContext.Provider>
    )
} 
