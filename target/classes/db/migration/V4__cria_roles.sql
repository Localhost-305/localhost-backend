
-- Criando a tabela de roles
CREATE TABLE dim_roles (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

-- Criando a tabela de permissões
CREATE TABLE dim_permissions (
    permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    permission_name VARCHAR(255) NOT NULL UNIQUE
);

-- Criando uma tabela para conectar as permissões na role criada
CREATE TABLE fact_roles_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES dim_roles(role_id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES dim_permissions(permission_id) ON DELETE CASCADE
);

-- Realizando um insert das roles na tabela de roles
insert into dim_roles (role_name) VALUES ('ADMIN'),('SUPERVISOR'),('USER');

-- Realizando um insert das permissões na tabela de permissões
INSERT INTO dim_permissions (permission_name) VALUES
('allowed_to_see'),
('allowed_to_change'),
('allowed_to_import'),
('allowed_to_add_role'),
('allowed_to_see_money');

-- Realizando um insert na tabela de vinculação entre as tabelas roles e permissions para atribuir todas as permissões na ADMIN
INSERT INTO fact_roles_permissions (role_id, permission_id) VALUES
(1, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_see')),
(1, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_change')),
(1, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_import')),
(1, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_add_role')),
(1, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_see_money'));

-- Realizando um insert na tabela de vinculação entre as tabelas roles e permissions para atribuir todas as permissões na SUPERVISOR
INSERT INTO fact_roles_permissions (role_id, permission_id) VALUES
(2, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_see')),
(2, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_import')),
(2, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_see_money'));

-- Realizando um insert na tabela de vinculação entre as tabelas roles e permissions para atribuir todas as permissões na SUPERVISOR
INSERT INTO fact_roles_permissions (role_id, permission_id) VALUES
(3, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_see')),
(3, (SELECT permission_id FROM dim_permissions WHERE permission_name = 'allowed_to_import'));

RENAME TABLE users TO dim_users;
ALTER TABLE dim_users ADD COLUMN role_id BIGINT;
ALTER TABLE dim_users ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES dim_roles(role_id);

UPDATE dim_users SET role_id = 1 WHERE user_id = 1;