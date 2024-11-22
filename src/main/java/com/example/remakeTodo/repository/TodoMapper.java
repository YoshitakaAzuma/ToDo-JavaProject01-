package com.example.remakeTodo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.remakeTodo.entity.Todo;

@Mapper
public interface TodoMapper {
	public List<Todo> selectAll(int user_id);
	
	public List<Todo> selectIncomplete(int user_id);
	
	public List<Todo> selectComplete(int user_id);
	
	public void add(Todo todo);
	
	public void update(Todo todo);
	
	public void delete(int user_id);
}
