package com.ecommerce.services.implementations;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.CartProduct;
import com.ecommerce.repositories.implementations.CartRepositoryImplementation;
import com.ecommerce.repositories.specifications.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultCartService {

    private final CartRepositoryImplementation cartRepositoryImplementation;
    private final ProductRepository productRepository;

    public ConsultCartService(CartRepositoryImplementation cartRepositoryImplementation, ProductRepository productRepository) {
        this.cartRepositoryImplementation = cartRepositoryImplementation;
        this.productRepository = productRepository;
    }

    public List<CartProduct> consult(Long userId) {
        List<CartItem> cartItems = cartRepositoryImplementation.findCartItemsByUserId(userId);
        return convertCartItemsToProducts(cartItems);
    }

    private List<CartProduct> convertCartItemsToProducts(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem ->
                        new CartProduct(productRepository.findById(cartItem.getProductId()), cartItem.getQuantity()))
                .collect(Collectors.toList());
    }
}
