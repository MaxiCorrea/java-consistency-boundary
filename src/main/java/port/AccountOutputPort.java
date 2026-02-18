package port;

import java.util.Optional;

import domain.Account;
import domain.AccountId;

public interface AccountOutputPort {

	Optional<Account> findById(AccountId id);

	void save(Account fromAccount);
	
}
