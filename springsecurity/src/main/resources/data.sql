INSERT IGNORE INTO tb_roles (id, role)
VALUES ('da9529e3-874d-4f84-ae3a-c5b3408e4cd9', 'ROLE_USER'),
        ('7e0e6762-25c2-46ad-8735-96af2e3c00fb', 'ROLE_ADMIN');

INSERT IGNORE INTO tb_users (id, username, password)
VALUES ('76cc8d66-25a6-4da3-9489-86fdfcd92a76', 'admin', '$2a$10$Fews8VHzAK6p69TpsjpuUOlyNx2WMgWgX.bjmydO8HvJ2iS25cHle'),
        ('bf033286-d17a-4e81-b5bb-c62e2f494fcc', 'user', '$2a$10$Fews8VHzAK6p69TpsjpuUOlyNx2WMgWgX.bjmydO8HvJ2iS25cHle');

INSERT IGNORE INTO tb_users_roles (user_id, role_id)
VALUES ('76cc8d66-25a6-4da3-9489-86fdfcd92a76', 'da9529e3-874d-4f84-ae3a-c5b3408e4cd9'),
        ('76cc8d66-25a6-4da3-9489-86fdfcd92a76', '7e0e6762-25c2-46ad-8735-96af2e3c00fb'),
        ('bf033286-d17a-4e81-b5bb-c62e2f494fcc', 'da9529e3-874d-4f84-ae3a-c5b3408e4cd9');

