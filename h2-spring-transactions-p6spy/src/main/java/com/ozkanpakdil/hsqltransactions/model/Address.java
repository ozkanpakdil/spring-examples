package com.ozkanpakdil.hsqltransactions.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String line1;
    String postCode;
    String city;
}
