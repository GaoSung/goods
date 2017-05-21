package com.my.repository;

import com.my.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findOneByUserName(String userName);
	List<Account> findByUserNameContaining(String name);
}