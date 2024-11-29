-- Dropar e recriar as tabelas
DROP TABLE IF EXISTS dim_roles;
CREATE TABLE dim_roles (
                           role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           role_name VARCHAR(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS dim_permissions;
CREATE TABLE dim_permissions (
                                 permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 permission_name VARCHAR(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS dim_candidates;
CREATE TABLE dim_candidates (
                                candidate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                candidate_name VARCHAR(200) NOT NULL,
                                email VARCHAR(255) UNIQUE NOT NULL,
                                phone VARCHAR(12) NOT NULL,
                                birth_date DATE NOT NULL
);

DROP TABLE IF EXISTS dim_jobs;
CREATE TABLE dim_jobs (
                          job_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          job_title VARCHAR(200) NOT NULL,
                          number_of_positions INT NOT NULL,
                          job_requirements VARCHAR(500) NOT NULL,
                          job_status VARCHAR(20) NOT NULL,
                          location VARCHAR(300) NOT NULL,
                          responsible_person VARCHAR(200) NOT NULL,
                          opening_date DATETIME NOT NULL,
                          closing_date DATETIME NOT NULL
);

DROP TABLE IF EXISTS dim_recruitment_processes;
CREATE TABLE dim_recruitment_processes (
                                           process_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           process_name VARCHAR(200) NOT NULL,
                                           start_date DATETIME NOT NULL,
                                           end_date DATETIME NOT NULL,
                                           process_status VARCHAR(20) NOT NULL,
                                           process_description VARCHAR(500) NOT NULL
);

DROP TABLE IF EXISTS dim_recruiters;
CREATE TABLE dim_recruiters (
                                recruiter_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                recruiter_name VARCHAR(200) NOT NULL,
                                role VARCHAR(50) NOT NULL,
                                feedbacks_given INT NOT NULL
);

DROP TABLE IF EXISTS dim_date;
CREATE TABLE dim_date (
                          date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          date_day INT NOT NULL,
                          date_month INT NOT NULL,
                          date_year INT NOT NULL
);

DROP TABLE IF EXISTS dim_hour;
CREATE TABLE dim_hour (
                          hour_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          hour_hour INT NOT NULL
);

DROP TABLE IF EXISTS fact_applications;
CREATE TABLE fact_applications (
                                   recruiter_id BIGINT,
                                   candidate_id BIGINT,
                                   job_id BIGINT,
                                   process_id BIGINT,
                                   date_id BIGINT,
                                   hour_id BIGINT,
                                   number_of_applications INT NOT NULL,
                                   PRIMARY KEY (recruiter_id, candidate_id, job_id, process_id, date_id, hour_id),
                                   FOREIGN KEY (candidate_id) REFERENCES dim_candidates(candidate_id),
                                   FOREIGN KEY (job_id) REFERENCES dim_jobs(job_id),
                                   FOREIGN KEY (process_id) REFERENCES dim_recruitment_processes(process_id),
                                   FOREIGN KEY (recruiter_id) REFERENCES dim_recruiters(recruiter_id),
                                   FOREIGN KEY (date_id) REFERENCES dim_date(date_id),
                                   FOREIGN KEY (hour_id) REFERENCES dim_hour(hour_id)
);

DROP TABLE IF EXISTS fact_hirings;
CREATE TABLE fact_hirings (
                              hiring_id BIGINT AUTO_INCREMENT,
                              candidate_id BIGINT,
                              job_id BIGINT,
                              date_id BIGINT NOT NULL,
                              hour_id BIGINT NOT NULL,
                              hiring_date DATE NOT NULL,
                              initial_salary DECIMAL(8,2) NOT NULL,
                              contract_type VARCHAR(50) NOT NULL,
                              acceptance_date DATETIME NOT NULL,
                              qty_hirings INT NOT NULL,
                              contract_end_date DATE,  -- Adicionando a coluna contract_end_date diretamente
                              PRIMARY KEY (hiring_id, candidate_id, job_id, date_id, hour_id),
                              FOREIGN KEY (candidate_id) REFERENCES dim_candidates(candidate_id),
                              FOREIGN KEY (job_id) REFERENCES dim_jobs(job_id),
                              FOREIGN KEY (date_id) REFERENCES dim_date(date_id),
                              FOREIGN KEY (hour_id) REFERENCES dim_hour(hour_id)
);

DROP TABLE IF EXISTS fact_roles_permissions;
CREATE TABLE fact_roles_permissions (
                                        role_id BIGINT NOT NULL,
                                        permission_id BIGINT NOT NULL,
                                        PRIMARY KEY (role_id, permission_id),
                                        FOREIGN KEY (role_id) REFERENCES dim_roles(role_id) ON DELETE CASCADE,
                                        FOREIGN KEY (permission_id) REFERENCES dim_permissions(permission_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS dim_users;
CREATE TABLE dim_users (
                           user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(200) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           created_on DATE NOT NULL,
                           updated_on DATE NOT NULL,
                           role_id BIGINT,
                           FOREIGN KEY (role_id) REFERENCES dim_roles(role_id)
);
