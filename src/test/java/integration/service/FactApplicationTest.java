package integration.service;

import com.api.ApiApplication;
import com.api.domain.entity.FactApplication;
import com.api.domain.entity.FactApplicationId;
import com.api.domain.repository.FactApplicationRepository;
import com.api.domain.service.FactApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class FactApplicationServiceIntegrationTest {

    @Autowired
    private FactApplicationService service;

    @Autowired
    private FactApplicationRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Limpa os dados existentes nas tabelas antes do teste,
    @BeforeEach
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM fact_hirings");
        jdbcTemplate.execute("DELETE FROM fact_applications");
        jdbcTemplate.execute("DELETE FROM dim_recruiters");
        jdbcTemplate.execute("DELETE FROM dim_candidates");
        jdbcTemplate.execute("DELETE FROM dim_jobs");
    }

    @BeforeEach
    public void setUp() {
        // Insere os dados dos recrutadores
        jdbcTemplate.execute("INSERT INTO dim_recruiters (recruiter_id, recruiter_name, role, feedbacks_given) VALUES (1, 'Recruiter 1', 1, 0)");
        jdbcTemplate.execute("INSERT INTO dim_recruiters (recruiter_id, recruiter_name, role, feedbacks_given) VALUES (2, 'Recruiter 2', 2, 0)");
        jdbcTemplate.execute("INSERT INTO dim_recruiters (recruiter_id, recruiter_name, role, feedbacks_given) VALUES (3, 'Recruiter 3', 3, 0)");

        // Insere os dados dos candidatos
        jdbcTemplate.execute("INSERT INTO dim_candidates (candidate_id, candidate_name, email, phone, birth_date) VALUES (1, 'Ana Silva', 'ana.silva@email.com', '11987654321', '1990-05-12')");
        jdbcTemplate.execute("INSERT INTO dim_candidates (candidate_id, candidate_name, email, phone, birth_date) VALUES (2, 'Carlos Pereira', 'carlos.pereira@email.com', '11987654322', '1985-08-23')");
        jdbcTemplate.execute("INSERT INTO dim_candidates (candidate_id, candidate_name, email, phone, birth_date) VALUES (3, 'Maria Oliveira', 'maria.oliveira@email.com', '11987654323', '1992-11-30')");

        // Insere os dados de jobs na tabela dim_jobs
        jdbcTemplate.execute("INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date) " +
                "VALUES (1, 'Software Engineer', 3, 'Java, Spring Boot, SQL', 'Open', 'São Paulo', 'Alice', '2023-11-01 08:00:00', '2023-12-01 18:00:00')");
        jdbcTemplate.execute("INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date) " +
                "VALUES (2, 'Data Scientist', 2, 'Python, Machine Learning, SQL', 'Closed', 'Rio de Janeiro', 'Bob', '2023-10-15 08:00:00', '2023-11-15 18:00:00')");
        jdbcTemplate.execute("INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date) " +
                "VALUES (3, 'DevOps Engineer', 4, 'Docker, Kubernetes, AWS', 'Open', 'Belo Horizonte', 'Charlie', '2023-11-10 08:00:00', '2023-12-10 18:00:00')");
        jdbcTemplate.execute("INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date) " +
                "VALUES (4, 'Product Manager', 1, 'Agile, Scrum, Communication', 'Open', 'Brasília', 'Diana', '2023-11-05 08:00:00', '2023-12-05 18:00:00')");
        jdbcTemplate.execute("INSERT INTO dim_jobs (job_id, job_title, number_of_positions, job_requirements, job_status, location, responsible_person, opening_date, closing_date) " +
                "VALUES (5, 'Quality Assurance', 2, 'Test Automation, Selenium, API Testing', 'Closed', 'Curitiba', 'Eve', '2023-10-01 08:00:00', '2023-11-01 18:00:00')");

        // Insere os dados das aplicações
        FactApplication app1 = new FactApplication();
        app1.setId(new FactApplicationId(1, 1, 1, 1, 3, 1));
        app1.setNumberOfApplications(5);
        repository.save(app1);

        FactApplication app2 = new FactApplication();
        app2.setId(new FactApplicationId(2, 2, 2, 2, 2, 2));
        app2.setNumberOfApplications(3);
        repository.save(app2);

        FactApplication app3 = new FactApplication();
        app3.setId(new FactApplicationId(3, 3, 3, 3, 3, 4));
        app3.setNumberOfApplications(7);
        repository.save(app3);
    }

    //Verifica se o serviço consegue buscar todas as aplicações existentes no banco.
    @Test
    void shouldFindAllApplications() {
        List<FactApplication> applications = service.findAll();

        assertEquals(3, applications.size(), "O número de aplicações retornadas deve ser 3.");
        assertTrue(applications.stream().anyMatch(app -> app.getId().getJobId().equals(1)), "Deve conter a aplicação com Job ID 1.");
        assertTrue(applications.stream().anyMatch(app -> app.getId().getJobId().equals(2)), "Deve conter a aplicação com Job ID 2.");
        assertTrue(applications.stream().anyMatch(app -> app.getId().getJobId().equals(3)), "Deve conter a aplicação com Job ID 3.");
    }
}
