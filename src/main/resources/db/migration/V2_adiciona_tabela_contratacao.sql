


CREATE TABLE IF NOT EXISTS fact_hirings (
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
    PRIMARY KEY (hiring_id, candidate_id, job_id, date_id, hour_id),
    FOREIGN KEY (candidate_id) REFERENCES dim_candidates(candidate_id),
    FOREIGN KEY (job_id) REFERENCES dim_jobs(job_id),
    FOREIGN KEY (date_id) REFERENCES dim_date(date_id),
    FOREIGN KEY (hour_id) REFERENCES dim_hour(hour_id)
);