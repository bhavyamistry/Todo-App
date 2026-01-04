import { useParams, Link } from "react-router-dom";
import { useState } from "react";
import { retrieveHelloWorldPathVariable } from "./api/HelloWorldApiService";
import { useAuth } from "./security/AuthContext";

export default function WelcomeComponent() {
  const { username } = useParams();
  const [message, setMessage] = useState(null);
  const authContext = useAuth();
  function callHelloWorldRestApi() {
    retrieveHelloWorldPathVariable(username, authContext.token)
      .then((response) => succesfulResponse(response))
      .catch((error) => errorResponse(error))
      .finally(() => console.log("cleanup"));
  }

  function succesfulResponse(response) {
    console.log(response);
    setMessage(response.data.message);
  }

  function errorResponse(error) {
    console.log(error);
  }
  return (
    <div className="Welcome">
      <h1>Welcome {username}</h1>
      <div>
        Manage Your Todos <Link to="/todos">Todos</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
          Call Hello World
        </button>
      </div>
      <div className="text-infor">{message}</div>
    </div>
  );
}
