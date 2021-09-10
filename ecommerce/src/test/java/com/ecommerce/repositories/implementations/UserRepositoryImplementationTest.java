package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.repositories.specifications.UserRepository;
import com.ecommerce.util.CustomPasswordEncoder;
import com.ecommerce.utils.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
public class UserRepositoryImplementationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void addTestUser() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);
    }

    @Test
    @DirtiesContext
    public void testSaveUser() {
        User user = UserBuilder.builder()
                    .username("TW")
                    .role("ADMIN")
                    .password("proiect")
                    .build();

        User savedUser = userRepository.save(user);

        assertThat(user).isEqualTo(savedUser);
    }

    @Test
    @DirtiesContext
    public void testFindUsername() {
        User userFound = userRepository.findByUsername("TEAM");
        User foundUser = UserBuilder.builder()
                        .username("TEAM").build();
        assertThat(userFound).isEqualTo(foundUser);
    }

    @Test
    @DirtiesContext
    public void testFindUserException() {
        Exception e = assertThrows(NotFoundException.class, () -> userRepository.findByUsername("Not existent"));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindUserById() {
        User user = userRepository.findById(2L);
        User foundUser = UserBuilder.builder()
                .username("TEAM").build();

        assertThat(user).isEqualTo(foundUser);
    }

    @Test
    @DirtiesContext
    public void testUpdatePassword() {
        Password password = new Password();
        password.setPassword("New pass");
        password.setId(2L);
        CustomPasswordEncoder encoder = new CustomPasswordEncoder();

        userRepository.updateUserPassword(password);
        User changedUser = userRepository.findByUsername("TEAM");

        assertThat(changedUser.getPassword().getPassword()).isEqualTo(encoder.encode("New pass"));
    }

    @Test
    @DirtiesContext
    public void testDeleteById() {
        userRepository.deleteById(2L);
        Exception e = assertThrows(NotFoundException.class, () -> userRepository.findById(2L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteUser() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .id(2L)
                .build();

        userRepository.delete(user);
        Exception e = assertThrows(NotFoundException.class, () -> userRepository.findByUsername("Not existent"));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

}