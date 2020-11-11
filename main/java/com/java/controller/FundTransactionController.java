package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.FundTransactionDto;
import com.java.entity.FundTransaction;
import com.java.exception.InsufficentBalanceException;
import com.java.exception.TransactionDetailsNotFoundException;
import com.java.service.FundTransactionService;

@RestController
public class FundTransactionController {

	@Autowired
	FundTransactionService fundTransactionService;
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/bank/fundtransfer")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransactionDto fundTransactionDto) throws InsufficentBalanceException{
		return new ResponseEntity<>(fundTransactionService.fundTransfer(fundTransactionDto),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/transactionHistory")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<FundTransaction>> getTransactions(@RequestParam("accountNumber")Long accountNumber,@RequestParam(defaultValue="0") Integer pageNo,@RequestParam(defaultValue="10") Integer pageSize, @RequestParam(defaultValue="transactionId") String sortByDesc) throws TransactionDetailsNotFoundException{
		List<FundTransaction> list=fundTransactionService.getTransactions(accountNumber,pageNo,pageSize,sortByDesc);
		return new ResponseEntity<List<FundTransaction>>(list,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String getPortNo(){
		String port = environment.getProperty("local.server.port");
		return "Banking app : "+port;
	}
}

