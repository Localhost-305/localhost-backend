package com.api.domain.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
class QuantityApplicationsRepositoryTest {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindApplicationsByMonths_withValidParams() {

    }
}
