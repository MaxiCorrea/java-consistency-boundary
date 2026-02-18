package domain;

import policy.OverdraftPolicy;

public class Account {

	private final AccountId id;
	private Money balance;
	private OverdraftPolicy overdraftPolicy;
	
	public Account(
			final AccountId id,
			final Money balance,
			final OverdraftPolicy overdraftPolicy) {
		this.id = id;
		this.balance = balance;
		this.overdraftPolicy = overdraftPolicy;
	}
	
	public void debit(
			final Money amount) {
		ensureDebit(amount);
		this.balance = balance.subtract(amount);
	}

	public void credit(
			final Money amount) {
		this.balance = balance.add(amount);
	}
	
	private void ensureDebit(
			final Money amount) {
		if(!overdraftPolicy.canDebit(balance, amount)) {
			String msg = "insuficient funds";
			throw new IllegalArgumentException(msg);
		}
	}	
	
	public AccountId getId() {
		return id;
	}

	public Money getBalance() {
		return balance;
	}
}
