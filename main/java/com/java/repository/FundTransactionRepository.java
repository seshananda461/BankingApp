package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.FundTransaction;

@Repository
public interface FundTransactionRepository extends JpaRepository<FundTransaction, Long> {

	Page<FundTransaction> findByAccountNumber(Long accountNumber, Pageable paging);

	

}
