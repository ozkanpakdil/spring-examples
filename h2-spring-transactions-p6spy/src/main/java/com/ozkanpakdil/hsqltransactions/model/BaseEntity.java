package com.ozkanpakdil.hsqltransactions.model;

import jakarta.persistence.*;

import java.time.Instant;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    Instant createTime;
    Instant updateTime;
    @Version
    private Integer version;
}
