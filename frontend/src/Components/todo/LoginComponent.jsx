import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/AuthContext";
export default function LoginComponent() {
  const [username, updateUserName] = useState("bhavya");

  const [password, updatePassword] = useState("");

  const [showFailureMessage, setFailureMessage] = useState(false);

  const navigate = useNavigate();

  const authContext = useAuth();

  function handleUsernameChange(event) {
    // console.log(event.target.value);
    updateUserName(event.target.value);
  }

  function handlePasswordChange(event) {
    // console.log(event.target.value);
    updatePassword(event.target.value);
  }

  async function handleSubmit() {
    console.log(username);
    console.log(password);
    if (await authContext.login(username, password)) {
      navigate(`/welcome/${username}`);
    } else {
      setFailureMessage(true);
    }
  }

  return (
    <div className="Login">
      <h1>Time to Login!</h1>
      {showFailureMessage && (
        <div className="errorMessage">
          Authenticated Failed. Please check your credentials!
        </div>
      )}
      <div className="LoginForm">
        <div>
          <label>User Name</label>
          <input
            type="text"
            name="username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            Login
          </button>
        </div>
      </div>
    </div>
  );
}
