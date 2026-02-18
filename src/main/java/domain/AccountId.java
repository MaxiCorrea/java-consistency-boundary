package domain;

import java.util.Objects;
import java.util.UUID;

public class AccountId {

	public static AccountId create() {
		return new AccountId(UUID.randomUUID());
	}
	
	private final UUID id;
	
	private AccountId(
			final UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountId other = (AccountId) obj;
		return Objects.equals(id, other.id);
	}	
}
