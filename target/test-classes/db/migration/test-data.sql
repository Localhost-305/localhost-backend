-- Inserções de roles e permissões
INSERT INTO dim_roles (role_name) VALUES
                                      ('ADMIN'),
                                      ('SUPERVISOR'),
                                      ('USER');

INSERT INTO dim_permissions (permission_name) VALUES
                                                  ('allowed_to_see'),
                                                  ('allowed_to_change'),
                                                  ('allowed_to_import'),
                                                  ('allowed_to_add_role'),
                                                  ('allowed_to_see_money');

-- Vinculação de permissões a roles

-- Permissões para ADMIN
INSERT INTO fact_roles_permissions (role_id, permission_id)
SELECT 1, permission_id FROM dim_permissions WHERE permission_name IN ('allowed_to_see', 'allowed_to_change', 'allowed_to_import', 'allowed_to_add_role', 'allowed_to_see_money');

-- Permissões para SUPERVISOR
INSERT INTO fact_roles_permissions (role_id, permission_id)
SELECT 2, permission_id FROM dim_permissions WHERE permission_name IN ('allowed_to_see', 'allowed_to_import', 'allowed_to_see_money');

-- Permissões para USER
INSERT INTO fact_roles_permissions (role_id, permission_id)
SELECT 3, permission_id FROM dim_permissions WHERE permission_name IN ('allowed_to_see', 'allowed_to_import');

INSERT INTO dim_users (email, name, password, created_on, updated_on, role_id)
VALUES ('api@email.com', 'api', 'password-hash', '2024-09-13', '2024-09-13', 1);
