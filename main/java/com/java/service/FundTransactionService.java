package com.java.service;

import java.util.List;

import com.java.dto.FundTransactionDto;
import com.java.entity.FundTransaction;
import com.java.exception.InsufficentBalanceException;
import com.java.exception.TransactionDetailsNotFoundException;

public interface FundTransactionService {

	public String fundTransfer(FundTransactionDto fundTransactionDto) throws InsufficentBalanceException;

	public List<FundTransaction> getTransactions(Long accountNumber,Integer pageNo, Integer pageSize, String sortByDesc) throws TransactionDetailsNotFoundException;

	

}
