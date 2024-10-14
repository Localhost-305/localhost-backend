

-- create section
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_on DATE NOT NULL,
    updated_on DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS dim_candidates (
    candidate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    candidate_name VARCHAR(200) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(12) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS dim_jobs (
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

CREATE TABLE IF NOT EXISTS dim_recruitment_processes (
    process_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    process_name VARCHAR(200) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    process_status VARCHAR(20) NOT NULL,
    process_description VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS dim_recruiters (
    recruiter_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recruiter_name VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL,
    feedbacks_given INT NOT NULL
);

CREATE TABLE IF NOT EXISTS dim_date (
    date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    day INT NOT NULL,
    month INT NOT NULL,
    year INT NOT NULL
);

CREATE TABLE IF NOT EXISTS dim_hour (
    hour_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hour INT NOT NULL
);

CREATE TABLE IF NOT EXISTS fact_applications (
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


-- inserting sample data
INSERT INTO users (email, name, password, created_on, updated_on) 
VALUES 
    ('api@email.com', 'api', '$2a$12$qQHqq5cdoWedWUi25cDtjupMlxRWwKO74HkhbCzZctouolZpeyTV.', '2024-09-13', '2024-09-13');