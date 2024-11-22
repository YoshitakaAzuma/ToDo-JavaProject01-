package com.example.remakeTodo.service;

import java.util.List;

import com.example.remakeTodo.entity.Todo;
import com.example.remakeTodo.form.TodoForm;

public interface TodoService {
	List<Todo> findAllTodo(int user_id);
	
	List<Todo> findInCompleteTodo(int user_id);
	
	List<Todo> findCompleteTodo(int user_id);
	
	void addTodo(Todo todo);
	
	//void updateTodo(Todo todo);
	
	void updateAllTodo(TodoForm form);
	
	void deleteTodo(int user_id);
}
