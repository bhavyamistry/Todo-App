import { useEffect, useState } from "react";
import {
  deleteTodoById,
  retrieveAllTodosForUsername,
} from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ListTodosComponent() {
  const today = new Date();
  const targetDate = new Date(
    today.getFullYear() + 12,
    today.getMonth(),
    today.getDate()
  );
  const [todos, setTodo] = useState([]);
  const [message, setMessage] = useState(null);
  const authContext = useAuth();
  const navigate = useNavigate();
  function refreshTodos() {
    // console.log(authContext.user_name);
    retrieveAllTodosForUsername(authContext.user_name)
      .then((response) => setTodo(response.data))
      .catch((error) => console.log(error));
  }

  function deleteTodo(id) {
    // console.log(id);
    deleteTodoById(authContext.username, id)
      .then((response) => {
        console.log(response);
        setMessage(`Delete of todo with ${id} is Successfull!`);
        refreshTodos();
      })
      .catch((error) => console.log(error));
  }

  function updateTodo(id) {
    // console.log(id);
    navigate(`/todo/${id}`);
  }

  function addNewTodo(id) {
    navigate(`/todo/-1`);
  }

  useEffect(() => refreshTodos(), []);
  // setTodo();
  // const todos = [
  // { id: 1, description: "Learn AWS", done: false, targetDate: targetDate },
  // {
  //   id: 2,
  //   description: "Learn Full Stack",
  //   done: false,
  //   targetDate: targetDate,
  // },
  // { id: 3, description: "Learn DevOps", done: false, targetDate: targetDate },
  // {
  //   id: 4,
  //   description: "Learn Kubernetes",
  //   done: false,
  //   targetDate: targetDate,
  // },
  // ];

  return (
    <div className="container">
      <h1>Things You Want to do!</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>description</th>
              <th>is Done?</th>
              <th>Target Date</th>
              <th>Delete</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                <td>{todo.description}</td>
                <td>{todo.done.toString()}</td>
                <td>{todo.targetDate}</td>
                <td>
                  <button
                    className="btn btn-warning"
                    onClick={() => deleteTodo(todo.id)}
                  >
                    Delete
                  </button>
                </td>
                <td>
                  <button
                    className="btn btn-success"
                    onClick={() => updateTodo(todo.id)}
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="btn btn-success m-5" onClick={addNewTodo}>
        Add New Todo
      </div>
    </div>
  );
}
