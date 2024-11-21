package integration.service;

import com.api.ApiApplication;
import com.api.domain.dto.UserDto;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
import com.api.domain.repository.RoleRepository;
import com.api.domain.repository.UserRepository;
import com.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM dim_users");
        jdbcTemplate.execute("DELETE FROM dim_roles");
    }

    @BeforeEach
    public void setUp() {
        if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByRoleName("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setRoleName("USER");
            roleRepository.save(userRole);
        }
    }

    @Test
    void shouldCreateUserSuccessfully() {
        UserDto userDto = new UserDto(
                "Davi",
                "davi@email.com",
                "password123",
                "USER"
        );

        userService.save(userDto);

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Davi", user.getName());
        assertEquals("davi@email.com", user.getEmail());
        assertNotNull(user.getRole());
        assertEquals("USER", user.getRole().getRoleName());
        assertEquals(LocalDate.now().toString(), user.getCreatedOn().toString());
        assertEquals(LocalDate.now().toString(), user.getUpdatedOn().toString());
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        Role userRole = roleRepository.findByRoleName("USER").orElseThrow();
        User user = new User();
        user.setName("David");
        user.setEmail("david@gmail.com");
        user.setPassword("encodedPassword");
        user.setRole(userRole);
        user.setCreatedOn(LocalDate.now());
        user.setUpdatedOn(LocalDate.now());
        userRepository.save(user);

        Long userId = user.getUserId();
        UserDto updatedUserDto = new UserDto(
                "David",
                "david@gmail.com",
                null,
                null
        );

        userService.update(userId, updatedUserDto);

        User updatedUser = userRepository.findById(userId).orElseThrow();
        assertEquals("David", updatedUser.getName());
        assertEquals("david@gmail.com", updatedUser.getEmail());
        assertEquals(LocalDate.now().toString(), updatedUser.getUpdatedOn().toString());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonexistentUser() {
        Long nonexistentUserId = 999L;
        UserDto userDto = new UserDto(
                "Nonexistent User",
                "nonexistent@example.com",
                null,
                null
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.update(nonexistentUserId, userDto)
        );
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void shouldGetAllUsers() {
        Role adminRole = roleRepository.findByRoleName("ADMIN").orElseThrow();
        User user1 = new User();
        user1.setName("User 1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password");
        user1.setRole(adminRole);
        user1.setCreatedOn(LocalDate.now());
        user1.setUpdatedOn(LocalDate.now());


        User user2 = new User();
        user2.setName("User 2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password");
        user2.setRole(adminRole);
        user2.setCreatedOn(LocalDate.now());
        user2.setUpdatedOn(LocalDate.now());

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals("user1@example.com")));
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals("user2@example.com")));
    }
}
