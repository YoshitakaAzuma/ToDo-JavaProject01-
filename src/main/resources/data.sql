insert into users (username, password_hash, email)
values ('user1','$2a$10$CSDoH4U6iJmc10FywcHQjezbJtgvHGnJr7qUFK8tHQLEWREWka1Qm', 'user1@gmail.com');
insert into users (username, password_hash, email)
values ('user2','$2a$10$DhT1FQMPCa5/D4JFXFY5A.Vrzjxjrw.QEcfh8PtJN3Dcc5YsaXlSW', 'user2@docomo.ne.jp');

insert into todo_items (title, time_limit, user_id)
values ('タスク1','2024-08-30',1);
insert into todo_items (title, time_limit, user_id)
values ('タスク2','2024-08-30',1);
insert into todo_items (title, time_limit, user_id)
values ('タスク3','2024-08-30',1);

insert into todo_items (title, time_limit, user_id)
values ('タスク1','2024-08-30',2);
insert into todo_items (title, time_limit, user_id)
values ('タスク2','2024-08-30',2);
insert into todo_items (title, time_limit, user_id)
values ('タスク3','2024-08-30',2);
