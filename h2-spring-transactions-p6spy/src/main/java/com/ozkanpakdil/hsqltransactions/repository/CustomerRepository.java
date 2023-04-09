package com.ozkanpakdil.hsqltransactions.repository;

import com.ozkanpakdil.hsqltransactions.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

    List<Customer> findByName(String name);
}
