drop table if exists users cascade;
drop table if exists todo_items;


-- Userテーブル
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at timestamp(6) without time zone
);

-- Todoテーブル
CREATE TABLE todo_items (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    done_flg numeric(1) default 0 ,
    time_limit date,
    user_id INT REFERENCES users(id) ON DELETE CASCADE
);