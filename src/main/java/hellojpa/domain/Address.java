package hellojpa.domain;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String city;
	private String street;
	private String zipcode;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address)o;
		return Objects.equals(city, address.city) && Objects.equals(street, address.street)
			&& Objects.equals(zipcode, address.zipcode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, zipcode);
	}

	@Override
	public String toString() {
		return "Address{" +
			"city='" + city + '\'' +
			", street='" + street + '\'' +
			", zipcode='" + zipcode + '\'' +
			'}';
	}
}
