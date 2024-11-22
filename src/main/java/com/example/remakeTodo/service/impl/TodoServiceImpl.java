package com.example.remakeTodo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.remakeTodo.entity.Todo;
import com.example.remakeTodo.form.TodoForm;
import com.example.remakeTodo.repository.TodoMapper;
import com.example.remakeTodo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	
	// DI
	private final TodoMapper todoMapper;

	@Override
	public List<Todo> findAllTodo(int user_id) {
		System.out.println("selectAll");
		for (Todo todo: todoMapper.selectAll(user_id)) {
			System.out.println(todo.getTime_limit());
		}
		return todoMapper.selectAll(user_id);
	}

	@Override
	public List<Todo> findInCompleteTodo(int user_id) {
		System.out.println("findInConpleteTodo");
		for (Todo todo: todoMapper.selectIncomplete(user_id)) {
			System.out.println(todo.getTime_limit());
		}
		return todoMapper.selectIncomplete(user_id);
	}

	@Override
	public List<Todo> findCompleteTodo(int user_id) {
		System.out.println("selectComplete");
		for (Todo todo: todoMapper.selectComplete(user_id)) {
			System.out.println(todo.getTime_limit());
		}
		return todoMapper.selectComplete(user_id);
	}

	@Override
	public void addTodo(Todo todo) {
		todoMapper.add(todo);
	}

	/*@Override
	public void updateTodo(Todo todo) {
		todoMapper.update(todo);
	}*/
	
	@Override
	public void updateAllTodo(TodoForm form) {
		if (form.getTodos() != null) {
			for (Todo todo: form.getTodos()) {
				todoMapper.update(todo);
			}
		}
		if (form.getDoneTodos() != null) {
			for (Todo todo: form.getDoneTodos()) {
				todoMapper.update(todo);
			}
		}
		
	}

	@Override
	public void deleteTodo(int user_id) {
		todoMapper.delete(user_id);
	}
}
