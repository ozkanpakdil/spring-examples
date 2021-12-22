package com.mascix.mysqlsession;

import javax.transaction.Transactional;

import com.mascix.mysqlsession.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByName(String name);
    @Query(name = "native1")
    public User native1(String name);
    @Modifying
    @Query(name = "nativeSessionSet")
    @Transactional
    public void nativeSessionSet(String name);
    @Query(name = "nativeSessionGet")
    public Object nativeSessionGet();
    User findByEmail(String email);

}
