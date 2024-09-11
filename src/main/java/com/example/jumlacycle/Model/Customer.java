package com.example.jumlacycle.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null unique")
    @Pattern(regexp = "^05\\d{8}$",message = "Phone number most be as 05XXXXXXXX")
    private String phoneNumber;

    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,   mappedBy = "customer")
    private List<Order> orders;

    @ManyToOne
    private Review review;
}
