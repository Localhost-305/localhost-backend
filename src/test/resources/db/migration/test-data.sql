-- Inserções de roles e permissões
INSERT INTO dim_roles (role_name)
VALUES
('ADMIN'),
('SUPERVISOR'),
('USER');

INSERT INTO dim_permissions (permission_name)
VALUES
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

-- Inserções para dim_users
INSERT INTO dim_users (name, email, password, created_on, updated_on, role_id)
VALUES
('Admin User', 'admin@example.com', 'hashedpassword1', '2024-01-01', '2024-11-01', 1), -- ADMIN role
('Supervisor User', 'supervisor@example.com', 'hashedpassword2', '2024-02-01', '2024-11-01', 2), -- SUPERVISOR role
('Regular User 1', 'user1@example.com', 'hashedpassword3', '2024-03-01', '2024-11-01', 3), -- USER role
('Regular User 2', 'user2@example.com', 'hashedpassword4', '2024-04-01', '2024-11-01', 3), -- USER role
('Regular User 3', 'user3@example.com', 'hashedpassword5', '2024-05-01', '2024-11-01', 3); -- USER role

-- Inserções para dim_candidates
INSERT INTO dim_candidates (candidate_name, email, phone, birth_date)
VALUES
('John Doe', 'johndoe@example.com', '12345678901', '1990-01-01'),
('Jane Smith', 'janesmith@example.com', '12345678902', '1985-05-15'),
('Alice Johnson', 'alicej@example.com', '12345678903', '1992-07-20'),
('Bob Brown', 'bobbrown@example.com', '12345678904', '1988-12-10'),
('Eve White', 'evewhite@example.com', '12345678905', '1995-03-25');

-- Inserções para dim_jobs
INSERT INTO dim_jobs (job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date)
VALUES
('Java Developer', 3, 'Experience in Java and Spring', 'OPEN', 'Remote', 'Manager A', '2024-11-01 08:00:00', '2024-12-15 18:00:00'),
('Data Analyst', 2, 'Proficiency in SQL and Python', 'CLOSED', 'New York', 'Manager B', '2024-10-01 09:00:00', '2024-11-01 17:00:00'),
('UI/UX Designer', 1, 'Expertise in Figma and Adobe XD', 'OPEN', 'San Francisco', 'Manager C', '2024-09-15 08:00:00', '2024-12-01 18:00:00'),
('DevOps Engineer', 4, 'Experience in CI/CD pipelines', 'OPEN', 'Seattle', 'Manager D', '2024-11-10 09:00:00', '2025-01-10 17:00:00'),
('Project Manager', 2, 'Strong communication skills', 'CLOSED', 'Chicago', 'Manager E', '2024-08-01 09:00:00', '2024-09-30 17:00:00');

-- Inserções para dim_recruitment_processes
INSERT INTO dim_recruitment_processes (process_name, start_date, end_date, process_status, process_description)
VALUES
('Java Hiring Process', '2024-11-01 08:00:00', '2024-12-15 18:00:00', 'ACTIVE', 'Recruitment for Java developers'),
('Data Analysis Recruitment', '2024-10-01 09:00:00', '2024-11-01 17:00:00', 'COMPLETED', 'Process to hire data analysts'),
('UI/UX Designer Selection', '2024-09-15 08:00:00', '2024-12-01 18:00:00', 'ACTIVE', 'Hiring UI/UX designers'),
('DevOps Hiring Process', '2024-11-10 09:00:00', '2025-01-10 17:00:00', 'PLANNED', 'Recruitment for DevOps engineers'),
('Project Manager Onboarding', '2024-08-01 09:00:00', '2024-09-30 17:00:00', 'COMPLETED', 'Selection of project managers');

-- Inserções para dim_recruiters
INSERT INTO dim_recruiters (recruiter_name, role, feedbacks_given)
VALUES
('Michael Green', 'Senior Recruiter', 50),
('Samantha Blue', 'HR Specialist', 40),
('James Brown', 'Recruitment Manager', 60),
('Emily White', 'Recruiter', 30),
('Robert Black', 'HR Consultant', 20);

-- Inserções para dim_date
INSERT INTO dim_date (date_day, date_month, date_year)
VALUES
(1, 11, 2024),
(2, 11, 2024),
(3, 11, 2024),
(4, 11, 2024),
(5, 11, 2024);

-- Inserções para dim_hour
INSERT INTO dim_hour (hour_hour)
VALUES
(9),
(10),
(11),
(14),
(15);

-- Inserções para fact_applications
INSERT INTO fact_applications (recruiter_id, candidate_id, job_id, process_id, date_id, hour_id, number_of_applications)
VALUES
(1, 1, 1, 1, 1, 1, 1),
(2, 2, 2, 2, 2, 2, 2),
(3, 3, 3, 3, 3, 3, 1),
(4, 4, 4, 4, 4, 4, 1),
(5, 5, 1, 1, 5, 5, 1);

-- Inserções para fact_hirings
INSERT INTO fact_hirings (hiring_id, candidate_id, job_id, date_id, hour_id, hiring_date, initial_salary, contract_type, acceptance_date, qty_hirings, contract_end_date)
VALUES
(1, 1, 1, 1, 1, '2024-11-01', 6000.00, 'CLT', '2024-11-01 10:00:00', 1, '2025-11-01'),
(2, 2, 2, 2, 2, '2024-10-10', 7000.00, 'PJ', '2024-10-11 11:00:00', 1, NULL),
(3, 3, 3, 3, 3, '2024-09-15', 5500.00, 'CLT', '2024-09-16 14:00:00', 1, '2026-09-15'),
(4, 4, 4, 4, 4, '2024-11-20', 7500.00, 'CLT', '2024-11-21 16:00:00', 1, '2025-11-20'),
(5, 5, 5, 5, 5, '2024-08-01', 5000.00, 'CLT', '2024-08-02 09:00:00', 1, '2025-08-01');

