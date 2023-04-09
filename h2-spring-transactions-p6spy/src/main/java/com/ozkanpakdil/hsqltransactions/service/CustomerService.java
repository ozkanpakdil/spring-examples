package com.ozkanpakdil.hsqltransactions.service;

import com.ozkanpakdil.hsqltransactions.exceptions.InternalException;
import com.ozkanpakdil.hsqltransactions.model.Address;
import com.ozkanpakdil.hsqltransactions.model.Customer;
import com.ozkanpakdil.hsqltransactions.model.Order;
import com.ozkanpakdil.hsqltransactions.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@AllArgsConstructor
@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @PostConstruct
    public void setup() {
        customerRepository.deleteAll();
        List<Address> adresses = new ArrayList();
        IntStream.range(0, 3).forEach(i -> {
            adresses.add(Address.builder()
                    .city("Glasgow")
                    .line1("apt:" + i)
                    .postCode("G" + i)
                    .build());
        });
        IntStream.range(0, 10).forEach(i -> {
            Customer customer = Customer.builder()
                    .name("name" + i)
                    .lastName("lastname" + i)
                    .addresses(new ArrayList<>())
                    .build();
            adresses.forEach(a -> {
                customer.getAddresses().add(a.toBuilder().build());
            });
            customerRepository.save(customer);

            log.debug("created:{}", customer);
        });
    }

    public List<Customer> getDoSome(String name) {
        List<Customer> customerList = customerRepository.findByName(name);
        Random r = new Random();
        customerList.forEach(c -> {
            c.getOrders().add(Order.builder()
                    .fullPrice(r.nextDouble())
                    .build());
            c.getOrders().add(Order.builder()
                    .items(List.of("pencil", "paper"))
                    .fullPrice(r.nextDouble())
                    .build());
        });
        customerRepository.saveAll(customerList);
        return customerList;
    }

    @Transactional
    public List<Customer> transactionRollBackExample(String name) {
        List<Customer> customerList = customerRepository.findByName(name);
        Random r = new Random();
        customerList.forEach(c -> {
            c.getOrders().add(Order.builder()
                    .fullPrice(r.nextDouble())
                    .build());
            c.getOrders().add(Order.builder()
                    .items(List.of("rollback1", "rollback1"))
                    .fullPrice(r.nextDouble())
                    .build());
        });
        customerRepository.saveAll(customerList);
        // call some webservice collect some data
        customerList.forEach(c -> {
            c.getAddresses().add(Address.builder()
                    .city("Gotham")
                    .build());
        });
        if (customerList.get(0).getOrders().size() > 4) {
            throw new InternalException("bad day");
        }

        customerRepository.saveAll(customerList);
        return customerList;
    }

    public List<Customer> noTransactionRollBackExample(String name) {
        List<Customer> customerList = customerRepository.findByName(name);
        Random r = new Random();
        customerList.forEach(c -> {
            c.getOrders().add(Order.builder()
                    .fullPrice(r.nextDouble())
                    .build());
            c.getOrders().add(Order.builder()
                    .items(List.of("norollback1", "norollback1"))
                    .fullPrice(r.nextDouble())
                    .build());
        });
        customerRepository.saveAll(customerList);
        // call some webservice collect some data
        customerList.forEach(c -> {
            c.getAddresses().add(Address.builder()
                    .city("Gotham")
                    .build());
        });
        if (customerList.get(0).getOrders().size() > 4) {
            throw new InternalException("bad day");
        }

        customerRepository.saveAll(customerList);
        return customerList;
    }

    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }
}
