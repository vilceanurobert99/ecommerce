package com.ecommerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "country")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @NotBlank(message = "A country should be inserted")
    private String country;

    @NotNull
    @Column(name = "city")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @NotBlank(message = "A city should be inserted")
    private String city;

    @NotNull
    @Column(name = "address")
    @NotBlank(message = "An address should be inserted")
    private String address;

    public UserAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAddress)) return false;
        UserAddress that = (UserAddress) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, address);
    }
}
