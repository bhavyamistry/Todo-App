package com.bhavyamistry.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

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
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap modelMap) {
		String username = (String)modelMap.get("name");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		modelMap.put("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		todoService.addTodo((String)modelMap.get("name"), todo.getDescription(), LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}
}

