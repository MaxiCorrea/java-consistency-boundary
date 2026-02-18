package service;

import domain.Account;
import domain.AccountId;
import domain.Money;
import port.AccountOutputPort;

public class TransferDomainService {

	private final AccountOutputPort accountOutputPort;
	
	public TransferDomainService(
			final AccountOutputPort accountOutputPort) {
		this.accountOutputPort = accountOutputPort;
	}
	
	public void transfer(
			AccountId fromId,
			AccountId toId,
			Money amount) {
		
		Account fromAccount = accountOutputPort.findById(fromId).orElseThrow(
				() -> new IllegalArgumentException("Account not found " + fromId));
		
		Account toAccount = accountOutputPort.findById(toId).orElseThrow(
				() -> new IllegalArgumentException("Account not found" + toId));
		
		fromAccount.debit(amount);
		toAccount.credit(amount);
		
		accountOutputPort.save(fromAccount);
		accountOutputPort.save(toAccount);
	}
	
}
