

INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date)
VALUES
    (1, 'Desenvolvedor', 3, 'Conhecimento em Java, SQL', 'Aberta', 'São Paulo - SP', 'Ricardo Lima', '2024-01-15 09:00:00', '2024-02-15 18:00:00'),
    (2, 'Analista de Marketing', 2, 'Experiência com SEO, Google Ads', 'Aberta', 'Rio de Janeiro - RJ', 'Juliana Costa', '2024-02-01 09:00:00', '2024-03-01 18:00:00'),
    (3, 'Designer Gráfico', 1, 'Portfólio com projetos criativos', 'Fechada', 'Curitiba - PR', 'Pedro Almeida', '2023-11-01 09:00:00', '2023-12-01 18:00:00');

INSERT INTO dim_candidates (candidate_id, candidate_name, email, phone, birth_date)
VALUES
    (1, 'Ana Silva', 'ana.silva@email.com', '11987654321', '1990-05-12'),
    (2, 'Carlos Pereira', 'carlos.pereira@email.com', '11987654322', '1985-08-23'),
    (3, 'Maria Oliveira', 'maria.oliveira@email.com', '11987654323', '1992-11-30'),
    (4, 'José Santos', 'jose.santos@email.com', '11987654324', '1988-02-15');

INSERT INTO dim_recruiters (recruiter_id, recruiter_name, role, feedbacks_given)
VALUES
    (1, 'Ricardo Lima', 'Gerente de RH', 15),
    (2, 'Juliana Costa', 'Coordenadora de RH', 8),
    (3, 'Pedro Almeida', 'Especialista em Recrutamento', 10);

INSERT INTO dim_date (date_id, day, month, year)
VALUES
    (1, 1, 1, 2024),
    (2, 2, 1, 2024),
    (3, 3, 1, 2024);

INSERT INTO dim_hour (hour_id, hour)
VALUES
    (1, 0),
    (2, 1),
    (3, 2);

INSERT INTO dim_recruitment_processes (process_id, process_name, start_date, end_date, process_status, process_description)
VALUES
    (1, 'Processo Seletivo 2024 - Desenvolvimento', '2024-01-16 09:00:00', '2024-02-15 18:00:00', 'Em Andamento', 'Seleção de candidatos para vagas de desenvolvedor.'),
    (2, 'Processo Seletivo 2024 - Marketing', '2024-02-02 09:00:00', '2024-03-01 18:00:00', 'Em Andamento', 'Seleção para vagas na área de marketing.'),
    (3, 'Processo Seletivo 2023 - Design', '2023-11-02 09:00:00', '2023-12-01 18:00:00', 'Finalizado', 'Seleção para designer gráfico.'),
    (4, 'Processo Seletivo 2024 - TI', '2024-03-01 09:00:00', '2024-04-01 18:00:00', 'Agendado', 'Seleção para novas vagas na área de TI.');

INSERT INTO fact_hirings (hiring_id, candidate_id, job_id, date_id, hour_id, hiring_date, initial_salary, contract_type, acceptance_date, qty_hirings)
VALUES
    (1, 1, 1, 1, 1, '2024-02-16', 15000.00, 'CLT', '2024-02-10 12:00:00', 1),
    (2, 2, 1, 1, 2, '2024-02-20', 14000.00, 'CLT', '2024-02-18 15:00:00', 1),
    (3, 3, 2, 2, 1, '2024-03-02', 12000.00, 'PJ', '2024-02-25 10:00:00', 1);

INSERT INTO fact_applications (recruiter_id, candidate_id, job_id, date_id, hour_id, process_id, number_of_applications)
VALUES
    (1, 1, 1, 1, 1, 1, 1),
    (1, 2, 1, 1, 2, 1, 1),
    (2, 3, 2, 2, 1, 2, 1),
    (3, 4, 3, 3, 1, 3, 1);

INSERT INTO users (email, name, password, created_on, updated_on)
VALUES
    ('api@email.com', 'api', '$2a$12$qQHqq5cdoWedWUi25cDtjupMlxRWwKO74HkhbCzZctouolZpeyTV.', '2024-09-13', '2024-09-13');


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

