package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.CartItem;
import com.ecommerce.models.HistoryItems;
import com.ecommerce.utils.CartItemsBuilder;
import com.ecommerce.utils.HistoryItemsBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class HistoryRepositoryImplementationTest {

    @Autowired
    private HistoryRepositoryImplementation historyRepository;

    @Test
    public void testFindHistoryItemById() {
        addCartItem(1L, 1L);

        HistoryItems historyItem = historyRepository.findById(1L, 1L);

        assertThat(historyItem.getUserId()).isEqualTo(1L);
        assertThat(historyItem.getProductId()).isEqualTo(1L);
    }

    @Test
    public void testFindHistoryExceptions() {
        Exception e = assertThrows(NotFoundException.class, () -> historyRepository.findById(1L, 1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void testFindUserHistory() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 1L);

        List<HistoryItems> historyItems = historyRepository.findHistoryItemsByUserId(1L);
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
    public void testDeleteHistoryItem() {
        addCartItem(1L, 2L);

        historyRepository.deleteHistoryItem(1L, 2L);

        Exception e = assertThrows(NotFoundException.class, () -> historyRepository.findById(1L, 2L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void testDeleteForUser() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 1L);

        historyRepository.deleteHistoryForUser(1L);
        List<HistoryItems> items = historyRepository.findHistoryItemsByUserId(1L);

        assertThat(items).isEmpty();
    }

    private void addCartItem(Long userId, Long productId) {
        CartItem cartItem = CartItemsBuilder.builder()
                .userId(userId)
                .productId(productId)
                .quantity(1L)
                .build();
        historyRepository.addProductToHistory(cartItem);
    }
}
