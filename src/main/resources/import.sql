insert into vending_machine_user_role (id, role_name) values (1, 'SELLER');
insert into vending_machine_user_role (id, role_name) values (2, 'BUYER');

-- Admin pass : mtp
insert into vending_machine_user (deposit, password, user_name) values ( 1000, '$2a$10$bYJO4E0Ff/XzALOQfUeaAeJ3Dhw6rJqRm7VKWRbFugiyhgKSxsi2G', 'admin');
insert into vending_machine_users_roles (vending_machine_user_id, vending_machine_user_role_id) values (1, 1);

insert into vending_machine_user (deposit, password, user_name) values ( 1000, '$2a$10$bYJO4E0Ff/XzALOQfUeaAeJ3Dhw6rJqRm7VKWRbFugiyhgKSxsi2G', 'buyer_user');
insert into vending_machine_users_roles (vending_machine_user_id, vending_machine_user_role_id) values (2, 2);