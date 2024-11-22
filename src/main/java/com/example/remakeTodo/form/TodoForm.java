package com.example.remakeTodo.form;

import java.util.List;

import com.example.remakeTodo.entity.Todo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoForm {
	@Valid
	private List<Todo> todos;
	@Valid
	private List<Todo> doneTodos;
}
