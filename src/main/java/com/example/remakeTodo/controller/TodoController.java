package com.example.remakeTodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.remakeTodo.entity.Todo;
import com.example.remakeTodo.form.TodoForm;
import com.example.remakeTodo.service.TodoService;
import com.example.remakeTodo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	private final UserService userService;
	
	@GetMapping
	public String index(Model model) {
		int userId = userService.getCurrentUserId();
		TodoForm todoForm = new TodoForm();
		todoForm.setTodos(todoService.findInCompleteTodo(userId));
		todoForm.setDoneTodos(todoService.findCompleteTodo(userId));
		model.addAttribute("userId", userId);
        model.addAttribute("todoForm", todoForm);
		return "index";
	}
	
	@PostMapping("/add")
	public String add(@Validated Todo todo,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("addError", "Todoは必須です");
			return "redirect:/todos";
		}
		todoService.addTodo(todo);
		return "redirect:/todos";
	}
	
	/*@PostMapping("/updateAll")
	public String updateAll(TodoForm todoForm) {
		for (Todo todo : todoForm.getTodos()) {
	        todoService.updateTodo(todo);
	    }
	    return "redirect:/";
	}*/
	@PostMapping("/updateAll")
	public String updateAll(@Validated  TodoForm todoForm,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("updateError", "Todoは必須です");
			return "redirect:/todos";
		}
		todoService.updateAllTodo(todoForm);
		return "redirect:/todos";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam(value="user_id") int userId) {
		todoService.deleteTodo(userId);
		return "redirect:/todos";
	}
}
