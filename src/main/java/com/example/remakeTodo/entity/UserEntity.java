package com.example.remakeTodo.entity;

import lombok.Data;

@Data
public class UserEntity {
	private int id;
    private String username;
    private String passwordHash;
    private String email;
    private java.sql.Timestamp createdAt;
}
