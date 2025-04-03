package com.example.demo.repository;

import com.example.demo.model.AccountIdChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountIdChangeRequestRepository extends JpaRepository<AccountIdChangeRequest, Long> {
}
