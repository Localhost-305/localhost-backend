package com.api;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class H2DatabaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        // Testa se a conexão foi estabelecida
        String sql = "SELECT 1";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        assertTrue(result != null && result == 1, "Database connection should be successful");
    }

    @Test
    public void testDataInsertedIntoDimCandidates() {
        // Testa se dados foram inseridos corretamente na tabela 'dim_candidates'
        String sql = "SELECT COUNT(*) FROM dim_candidates";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertTrue(count != null && count > 0, "Data should be inserted into dim_candidates");
    }

    @Test
    public void testDataInsertedIntoDimJobs() {
        // Testa se dados foram inseridos corretamente na tabela 'dim_jobs'
        String sql = "SELECT COUNT(*) FROM dim_jobs";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertTrue(count != null && count > 0, "Data should be inserted into dim_jobs");
    }

    @Test
    public void testDataInsertedIntoDimRecruiters() {
        // Testa se dados foram inseridos corretamente na tabela 'dim_recruiters'
        String sql = "SELECT COUNT(*) FROM dim_recruiters";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertTrue(count != null && count > 0, "Data should be inserted into dim_recruiters");
    }

    @Test
    @Transactional
    public void testInsertFactApplications() {
        jdbcTemplate.update("INSERT INTO fact_applications (recruiter_id, candidate_id, job_id, date_id, hour_id, process_id, number_of_applications) VALUES (1, 1, 1, 1, 1, 1, 1)");

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM fact_applications", Integer.class);
        assertTrue(count != null && count > 0);
    }

}
