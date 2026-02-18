package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import domain.Account;
import domain.AccountId;
import domain.Money;
import policy.OverdraftPolicy;
import port.AccountOutputPort;

class TransferDomainServiceTest {

	@Test
	void shouldTransferFromToSuccessfully() {
		
		AccountOutputPort accountOutputPort = Mockito.mock(AccountOutputPort.class);
		OverdraftPolicy overdraftPolicy = Mockito.mock(OverdraftPolicy.class);
		
		AccountId fromId = AccountId.create();
		Account fromAccount = new Account(fromId, Money.of(1000), overdraftPolicy);
		
		AccountId toId = AccountId.create();
		Account toAccount = new Account(fromId, Money.of(0), overdraftPolicy);
		
		Mockito.when(accountOutputPort.findById(fromId)).thenReturn(Optional.of(fromAccount));
		Mockito.when(accountOutputPort.findById(toId)).thenReturn(Optional.of(toAccount));
		Mockito.when(overdraftPolicy.canDebit(Mockito.any(), Mockito.any())).thenReturn(true);
		
		TransferDomainService service = new TransferDomainService(accountOutputPort);
		service.transfer(fromId, toId, Money.of(500));
		
		assertEquals(Money.of(500), toAccount.getBalance());
		assertEquals(Money.of(500), toAccount.getBalance());
		
		Mockito.verify(accountOutputPort).save(fromAccount);
		Mockito.verify(accountOutputPort).save(toAccount);
		
	}

}
