package com.example.demo.repository;

import com.example.demo.model.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountIdRepository extends JpaRepository<AccountId, Long> {
    Optional<AccountId> findByLogin(String login);

    List<AccountId> findAllByFrozen(boolean frozen);
}
