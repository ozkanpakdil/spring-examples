package com.mascix.postgresqltest.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "meteo")
public interface MeteoRepository extends JpaRepository<Meteo, Integer> {
}