package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.CartItem;
import com.ecommerce.models.HistoryItems;
import com.ecommerce.repositories.implementations.HistoryRepositoryImplementation;
import com.ecommerce.services.specifications.HistoryService;
import com.ecommerce.utils.CartItemsBuilder;
import com.ecommerce.utils.HistoryItemsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class HistoryServiceImplementationTest {

    @Autowired
    private HistoryService historyService;

    @Test
    @DirtiesContext
    public void testFindHistoryItemById() {
        addCartItem(1L, 1L);

        HistoryItems historyItem = historyService.findById(1L, 1L);

        assertThat(historyItem.getUserId()).isEqualTo(1L);
        assertThat(historyItem.getProductId()).isEqualTo(1L);
    }

    @Test
    @DirtiesContext
    public void testFindHistoryExceptions() {
        Exception e = assertThrows(NotFoundException.class, () -> historyService.findById(1L, 1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindUserHistory() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 1L);

        List<HistoryItems> historyItems = historyService.findHistoryItemsByUserId(1L);
        HistoryItems history1 = HistoryItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .purchase()
                .build();
        HistoryItems history2 = HistoryItemsBuilder.builder()
                .userId(1L)
                .productId(2L)
                .purchase()
                .build();

        assertThat(historyItems).containsExactly(history1, history2);
    }

    @Test
    @DirtiesContext
    public void testDeleteHistoryItem() {
        addCartItem(1L, 2L);

        historyService.deleteHistoryItem(1L, 2L);

        Exception e = assertThrows(NotFoundException.class, () -> historyService.findById(1L, 2L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteForUser() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 1L);

        historyService.deleteHistoryForUser(1L);
        List<HistoryItems> items = historyService.findHistoryItemsByUserId(1L);

        assertThat(items).isEmpty();
    }

    private void addCartItem(Long userId, Long productId) {
        CartItem cartItem = CartItemsBuilder.builder()
                .userId(userId)
                .productId(productId)
                .quantity(1L)
                .build();
        historyService.addProductToHistory(cartItem);
    }
}