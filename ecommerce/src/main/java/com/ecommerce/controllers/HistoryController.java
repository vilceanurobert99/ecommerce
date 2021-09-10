package com.ecommerce.controllers;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.HistoryItemsRepresentation;
import com.ecommerce.services.specifications.HistoryService;
import com.ecommerce.services.specifications.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;
    private final ProductService productService;

    public HistoryController(HistoryService historyService, ProductService productService) {
        this.historyService = historyService;
        this.productService = productService;
    }

    //ok
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveItemInHistory(@RequestBody CartItem cartItem) {
        historyService.addProductToHistory(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @GetMapping("/{userId}")
    public ResponseEntity<List<HistoryItemsRepresentation>> getAllHistoryItems(@PathVariable("userId") Long userId) {
        List<HistoryItemsRepresentation> representations = historyService.findHistoryItemsByUserId(userId).stream()
                .map(hi -> new HistoryItemsRepresentation(productService).mapFromDomain(hi))
                .collect(Collectors.toList());
        return new ResponseEntity<>(representations, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<HistoryItemsRepresentation> getProductById(@PathVariable("userId") Long userId,
                                                       @PathVariable("productId") Long productId) {
        HistoryItemsRepresentation representation = new HistoryItemsRepresentation(productService)
                .mapFromDomain(historyService.findById(userId, productId));
        return new ResponseEntity<>(representation, HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserHistory(@PathVariable("userId") Long userId) {
        historyService.deleteHistoryForUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<HttpStatus> deleteHistoryItem(@PathVariable("userId") Long userId,
                                                        @PathVariable("productId") Long productId) {
        historyService.deleteHistoryItem(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
