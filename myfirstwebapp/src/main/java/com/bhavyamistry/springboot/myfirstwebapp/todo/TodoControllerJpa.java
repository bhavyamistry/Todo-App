package com.bhavyamistry.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	
	private TodoRepository todoRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}



	@RequestMapping(value="list-todos", method=RequestMethod.GET)
	public String listAllTodos(ModelMap modelMap) {
		String username = getLoggedInUsername();
		List<Todo> todos = todoRepository.findByUsername(username);
		modelMap.addAttribute("todos",todos);
		return "listTodos";
	}



	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showingUpdateTodoPage(ModelMap modelMap, @RequestParam int id) {
		Todo todo = todoRepository.findById(id).get();
		modelMap.addAttribute("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updatingTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		todo.setUsername(getLoggedInUsername());
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap modelMap) {
		String username = getLoggedInUsername();
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		modelMap.put("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		String username = getLoggedInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);
		
		return "redirect:list-todos";
	}
}
