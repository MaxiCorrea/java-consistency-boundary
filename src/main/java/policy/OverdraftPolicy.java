package policy;

import domain.Money;

public interface OverdraftPolicy {

	boolean canDebit(Money balance, Money amount);
	
}
