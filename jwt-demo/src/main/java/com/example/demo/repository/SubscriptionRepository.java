package com.example.demo.repository;

import com.example.demo.model.AccountId;
import com.example.demo.model.Subscription;
import com.example.demo.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByNextPaymentDate(LocalDate nextPaymentDate);

    Optional<Subscription> findByTypeAndAccountId(SubscriptionType type, AccountId accountId);

    List<Subscription> findAllByAccountId(AccountId accountId);
}
