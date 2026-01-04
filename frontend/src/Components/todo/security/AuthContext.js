import { createContext, use, useContext, useState} from "react";
import { executeBasicAuthenticationService, executeJwtAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({children}){
  // const [number, setNumber] = useState(10)

  const [isAuthenticated, setAuthenticated] = useState(false)

  const [user_name, setUsername] = useState('')

  const [token, setToken] = useState(null)
  // setInterval(()=> setNumber(number+1),10000)

  // function login(username, password){
  //   if (username === "bhavya" && password === "12345") {
  //     setAuthenticated(true);
  //     setUsername(username);
  //     return true;
  //   } else {
  //     setAuthenticated(false);
  //     return false;
  //   }
  // }

  // async function login(username, password){
    
  //   const baToken = 'Basic ' + window.btoa(username+":"+password)

  //   try{
  //     const response = await executeBasicAuthenticationService(baToken)
  //     if (response.status===200) {
  //       setAuthenticated(true);
  //       setUsername(username);
  //       setToken(baToken)
  //       apiClient.interceptors.request.use(
  //         (config)=>{
  //           // console.log('intercepting and adding a token')
  //           config.headers.Authorization=baToken
  //           return config
  //         }
  //       )
  //       return true;
  //     } else {
  //       logout()
  //       return false;
  //     }
  //   }
  //   catch(error){
  //     logout()
  //     return false;
  //   }
  // }

  async function login(username, password){
    

    try{
      const response = await executeJwtAuthenticationService(username, password)
      if (response.status===200) {
        // console.log(response.data.token)
        const jwtToken = 'Bearer '+response.data.token
        setAuthenticated(true);
        setUsername(username);
        setToken(jwtToken)
        apiClient.interceptors.request.use(
          (config)=>{
            // console.log('intercepting and adding a token')
            config.headers.Authorization=jwtToken
            return config
          }
        )
        return true;
      } else {
        logout()
        return false;
      }
    }
    catch(error){
      logout()
      return false;
    }
  }

  function logout(){
    setAuthenticated(false);
    setToken(null)
    setUsername(null)
  }

  return(
    <AuthContext.Provider value={{isAuthenticated, setAuthenticated, login, user_name, token}}>
      {children}
    </AuthContext.Provider>
  )
}