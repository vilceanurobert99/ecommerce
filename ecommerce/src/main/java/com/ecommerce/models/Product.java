package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products")
@JsonIgnoreProperties({"reviewList, userWishlist, histories, cartList"})
public class Product implements Serializable, Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "stars")
    @NotNull
    private double stars;

    @NotNull
    @Column(name = "price")
    private double price;

    @Value("1")
    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Value("0")
    @NotNull
    @Column(name = "sale")
    private int sale;

    @NotNull
    @Column(name = "photo")
    private String photo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sale=" + sale +
                ", photo='" + photo + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public int compareTo(Product o) {

        if (o.getPrice() < this.getPrice()) {

            return 1;

        } else if (o.getPrice() > this.getPrice()) {

            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.stars, stars) == 0 &&
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                sale == product.sale &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(photo, product.photo) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, stars, price, quantity, sale, photo, category);
    }
}
