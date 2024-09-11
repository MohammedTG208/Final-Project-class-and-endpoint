package com.example.jumlacycle.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "product name can not be null")
    @Column(columnDefinition = "varchar(100) not null")
    private String productName;

    @Column(columnDefinition = "int not null")
    @Positive(message = "Enter positive number")
    private int quantity;

    @Column(columnDefinition = "DOUBLE not null")
    @NotEmpty(message = "Total amount can not be null")
    private double totalAmount;

    @Column(columnDefinition = "enum('Express','Priority','Standard') not null")
    @NotEmpty(message = "shipping can not be null")
    private String shippingMethod;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "review")
    private Set<Review> reviews;

    @ManyToMany
    @JsonIgnore
    private Set<Product> products;
}
