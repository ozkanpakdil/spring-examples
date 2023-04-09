package com.ozkanpakdil.hsqltransactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String lastName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders;
}