package com.java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.FundTransactionDto;
import com.java.entity.Account;
import com.java.entity.FundTransaction;
import com.java.exception.InsufficentBalanceException;
import com.java.exception.TransactionDetailsNotFoundException;
import com.java.repository.AccountRepository;
import com.java.repository.FundTransactionRepository;
import com.java.service.FundTransactionService;

@Service
public class FundTransactionServiceImpl implements FundTransactionService {

	@Autowired
	FundTransactionRepository fundTransactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public String fundTransfer(FundTransactionDto fundTransactionDto) throws InsufficentBalanceException {
		Long id = (long) (Math.random() * 9_000_000_000L + 1_000_000_000L);
		Account account = new Account();

		FundTransaction fundTransaction = new FundTransaction();
		System.out.println(".......:" + fundTransactionDto.getToAccountNumber());
		fundTransaction.setAccountNumber(fundTransactionDto.getAccountNumber());
		fundTransaction.setToAccountNumber(fundTransactionDto.getToAccountNumber());
		fundTransaction.setAmount(fundTransactionDto.getAmount());
		fundTransaction.setDate(fundTransactionDto.getDate());
		fundTransaction.setDescription(fundTransactionDto.getDescription());
		fundTransaction.setTransactionId(id);
		/* fundTransaction.setAccountNumber(fundTransactionDto.getAccountNumber()); */
		fundTransactionRepository.save(fundTransaction);

		Optional<Account> fromAccount = accountRepository.findByAccountNumber(fundTransactionDto.getAccountNumber());
		Optional<Account> toAccount = accountRepository.findByAccountNumber(fundTransactionDto.getToAccountNumber());
		if (fromAccount.isPresent() && toAccount.isPresent()
				&& fromAccount.get().getCurrentBalance() > fundTransactionDto.getAmount()) {
			fromAccount.get().setCurrentBalance(fromAccount.get().getCurrentBalance() - fundTransactionDto.getAmount());
			accountRepository.save(fromAccount.get());
			toAccount.get().setCurrentBalance(toAccount.get().getCurrentBalance() + fundTransactionDto.getAmount());
			accountRepository.save(toAccount.get());

			if (fundTransaction.getAmount() > account.getCurrentBalance()) {
				throw new InsufficentBalanceException("Your balance is insufficient");
			}
		}
		return "your transaction is successfull:" + fundTransaction.getTransactionId();
	}

	@Override
	public List<FundTransaction> getTransactions(Long accountNumber,Integer pageNo, Integer pageSize, String sortByDesc) throws TransactionDetailsNotFoundException {
		Pageable paging=PageRequest.of(pageNo, pageSize,Sort.by("transactionId").descending());
		Page<FundTransaction> pagesResult=fundTransactionRepository.findByAccountNumber(accountNumber,paging);
		List<FundTransaction> list=new ArrayList<FundTransaction>();
		if(pagesResult.hasContent()) {
			list= pagesResult.getContent();
			return list;
			}else {
					throw new TransactionDetailsNotFoundException("No Transaction Details Found!!");	
					}
	}

}
