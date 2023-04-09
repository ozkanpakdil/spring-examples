package com.ozkanpakdil.hsqltransactions.controller;

import com.ozkanpakdil.hsqltransactions.model.Customer;
import com.ozkanpakdil.hsqltransactions.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/byname/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name) {
        return customerService.findByName(name);
    }

    @GetMapping("/addorders/{name}")
    public List<Customer> getDoSome(@PathVariable String name) {
        return customerService.getDoSome(name);
    }

    @GetMapping("/rollBackExample/{name}")
    public List<Customer> rollBackExample(@PathVariable String name) {
        return customerService.transactionRollBackExample(name);
    }

    @GetMapping("/norollBackExample/{name}")
    public List<Customer> noRollBackExample(@PathVariable String name) {
        return customerService.noTransactionRollBackExample(name);
    }
}
