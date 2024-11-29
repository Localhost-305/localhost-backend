package com.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.core.env.Environment;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class DataSourceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment environment;

    @Test
    void testDatabaseIsH2() throws SQLException {
        String url = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
        String driverName = jdbcTemplate.getDataSource().getConnection().getMetaData().getDriverName();
        String databaseProductName = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();

        assertThat(url).contains("jdbc:h2:mem");
        assertThat(driverName).containsIgnoringCase("H2");
        assertThat(databaseProductName).isEqualTo("H2");
    }

    @Test
    void testActiveProfileIsTest() {
        String[] activeProfiles = environment.getActiveProfiles();
        assertThat(activeProfiles).contains("test");
    }

    @Test
    void testH2DatabaseSchema() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE UPPER(TABLE_NAME) = 'DIM_JOBS'",
                Integer.class
        );
        assertThat(count).isGreaterThan(0);
    }

    @Test
    void testNotUsingMySQL() throws SQLException {
        String url = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
        assertThat(url).doesNotContain("jdbc:mysql");
    }

}
