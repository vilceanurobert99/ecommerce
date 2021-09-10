package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.services.specifications.UserService;
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
class UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void addTestUser() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userService.save(user);
    }

    @Test
    @DirtiesContext
    public void testSaveUser() {
        User user = UserBuilder.builder()
                .username("TW")
                .role("ADMIN")
                .password("proiect")
                .build();

        User savedUser = userService.save(user);

        assertThat(user).isEqualTo(savedUser);
    }

    @Test
    @DirtiesContext
    public void testFindUsername() {
        User userFound = userService.findByUsername("TEAM");
        User foundUser = UserBuilder.builder()
                .username("TEAM").build();
        assertThat(userFound).isEqualTo(foundUser);
    }

    @Test
    @DirtiesContext
    public void testFindUserException() {
        Exception e = assertThrows(NotFoundException.class, () -> userService.findByUsername("Not existent"));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindUserById() {
        User user = userService.findById(2L);
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

        userService.updatePassword(password);
        User changedUser = userService.findByUsername("TEAM");

        assertThat(changedUser.getPassword().getPassword()).isEqualTo(encoder.encode("New pass"));
    }

    @Test
    @DirtiesContext
    public void testDeleteById() {
        userService.deleteById(2L);
        Exception e = assertThrows(NotFoundException.class, () -> userService.findById(2L));
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

        userService.delete(user);
        Exception e = assertThrows(NotFoundException.class, () -> userService.findByUsername("Not existent"));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

}