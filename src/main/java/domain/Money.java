package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

	public static Money of(
			final int i) {
		return new Money(BigDecimal.valueOf(i));
	}
	
	private final BigDecimal value;
	
	private Money(
			final BigDecimal value) {
		this.value = value;
	}
	
	public Money subtract(
			final Money other) {
		return new Money(this.value.subtract(other.value));
	}

	public Money add(
			final Money other) {
		return new Money(this.value.add(other.value));
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return Objects.equals(value, other.value);
	}
}
