package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import com.ecommerce.repositories.specifications.UserRepository;
import com.ecommerce.services.specifications.UserCreditCardService;
import com.ecommerce.utils.UserBuilder;
import com.ecommerce.utils.UserCreditCardBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class UserCreditCardServiceImplementationTest {

    @Autowired
    private UserCreditCardService userCreditCardService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);

        UserCreditCard creditCard = getCreditCard();
        userCreditCardService.save(creditCard, 2L);
    }

    @Test
    @DirtiesContext
    public void testSaveCard() {
        UserCreditCard creditCard = UserCreditCardBuilder.builder()
                .cardHolder("Bla")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4485285545962568")
                .build();
        userCreditCardService.save(creditCard, 2L); //TEAM user id
        //first card is the one inserted above in @BeforeEach
        UserCreditCard foundCard = userCreditCardService.findById(2L);
        assertThat(creditCard).isEqualTo(foundCard);
    }

    @Test
    @DirtiesContext
    public void testFindCardById() {
        UserCreditCard foundCard = userCreditCardService.findById(1L);
        assertThat(getCreditCard()).isEqualTo(foundCard);
    }

    @Test
    @DirtiesContext
    public void testFindCardException() {
        Exception e = assertThrows(NotFoundException.class, () -> userCreditCardService.findById(2L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testExistCreditCard() {
        boolean exists = userCreditCardService.existsById(1L);
        boolean notExist = userCreditCardService.existsById(2L);

        assertThat(exists).isEqualTo(true);
        assertThat(notExist).isEqualTo(false);
    }


    @Test
    @DirtiesContext
    public void testDeleteCard() {
        userCreditCardService.delete(2L);
        assertThat(userCreditCardService.findUserCreditCards(2L)).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testFindUserCreditCards() {
        UserCreditCard creditCard = UserCreditCardBuilder.builder()
                .cardHolder("Bla")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4485285545962568")
                .build();
        userCreditCardService.save(creditCard, 2L);
        UserCreditCard secondaryCard = getCreditCard();

        assertThat(userCreditCardService.findUserCreditCards(2L)).contains(creditCard, secondaryCard);
    }

    @Test
    @DirtiesContext
    public void testDeleteSpecificCard() {
        userCreditCardService.deleteSpecificCard(1L);
        assertThat(userCreditCardService.findUserCreditCards(2L)).isEmpty();
    }

    private UserCreditCard getCreditCard() {
        return UserCreditCardBuilder.builder()
                .cardHolder("TEST Admin")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4716980678542457")
                .build();
    }
}