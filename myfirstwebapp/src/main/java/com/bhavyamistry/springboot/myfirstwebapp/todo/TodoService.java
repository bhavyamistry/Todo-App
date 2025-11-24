package com.bhavyamistry.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "Bhavya", "Learn AWS", LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2, "Bhavya", "Learn DevOps", LocalDate.now().plusYears(2),false));
		todos.add(new Todo(3, "Bhavya", "Learn Full Stack", LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
<<<<<<< Updated upstream
=======
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
		todos.add(new Todo(todos.size()+1, username, description,targetDate,isDone));
	}
	
	public void deleteTodo(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteTodo(todo.getId());
		todos.add(todo);
	}
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
