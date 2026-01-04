import { useNavigate, useParams } from "react-router-dom";
import {
  createTodoApi,
  retrieveTodoApi,
  updateTodoApi,
} from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

export default function TodoComponent() {
  const { id } = useParams();
  const authContext = useAuth();
  const username = authContext.user_name;
  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [done, setDone] = useState(false);
  const navigate = useNavigate();
  function retrieveTodo() {
    if (id !== -1) {
      retrieveTodoApi(username, id)
        .then((response) => {
          // console.log(response.data);
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
          setDone(response.data.done);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
  function onSubmit(values) {
    console.log(values);
    const todo = {
      id: id,
      username: username,
      description: values.description,
      targetDate: values.targetDate,
      done: false,
    };
    console.log(todo);
    // console.log("ID:" + id);
    if (id !== "-1") {
      // console.log("Put Todo");
      updateTodoApi(username, id, todo)
        .then((response) => {
          console.log(response);
          navigate(`/todos`);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      // console.log("Post Todo");
      createTodoApi(username, todo)
        .then((response) => {
          console.log(response);
          navigate(`/todos`);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
  function validate(values) {
    let errors = {
      // description: "Enter a valid Description",
      // targetDate: "Enter a valid Target Date"
    };
    if (values.description.length < 5) {
      errors.description = "Enter atleast 5 characters";
    }
    if (values.targetDate === "" || values.targetDate === null) {
      errors.targetDate = "Enter a valid Target Date";
    }

    console.log("validate");
    return errors;
  }
  useEffect(() => retrieveTodo(), [id]);
  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="targetDate"
                component="div"
                className="alert alert-warning"
              />
              <fieldset className="form-group">
                <label>Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                ></Field>
              </fieldset>
              <fieldset className="form-group">
                <label>Target Date</label>
                <Field
                  type="date"
                  className="form-control"
                  name="targetDate"
                ></Field>
              </fieldset>
              <div>
                <button className="btn btn-success m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
