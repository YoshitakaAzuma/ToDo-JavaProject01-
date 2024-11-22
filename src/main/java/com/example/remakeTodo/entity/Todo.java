package com.example.remakeTodo.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Todo {
	private int id;
	
	@NotBlank(message = "Todoは必須です")
	private String title;
	
	private int done_flg;
	
	private String time_limit;
	
	private int user_id;
	
}
