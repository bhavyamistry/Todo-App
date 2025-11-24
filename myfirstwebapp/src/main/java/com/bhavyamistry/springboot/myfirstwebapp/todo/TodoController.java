package com.bhavyamistry.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TodoController {
	
	private TodoService todoService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}



	@RequestMapping(value="list-todos", method=RequestMethod.GET)
	public String listAllTodos(ModelMap modelMap) {
		List<Todo> todos = todoService.findByUsername("Bhavya");
		modelMap.addAttribute("todos",todos);
		return "listTodos";
	}
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showingUpdateTodoPage(ModelMap modelMap, @RequestParam int id) {
		Todo todo = todoService.findById(id);
		modelMap.addAttribute("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updatingTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap modelMap) {
		String username = (String)modelMap.get("name");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		modelMap.put("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
//		logger.error("Errors are:"+result.toString());
		if(result.hasErrors()) {
			return "addTodos";
		}
		
		todoService.addTodo((String)modelMap.get("name"), todo.getDescription(), LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}
}
