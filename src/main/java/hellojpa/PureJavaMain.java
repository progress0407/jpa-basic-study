package hellojpa;

import hellojpa.domain.Address;

public class PureJavaMain {
	public static void main(String[] args) {

		Address address1 = new Address("city A", "street", "zipcode");
		Address address2 = new Address("city A", "street", "zipcode");

		System.out.println("(address1 == address2) = " + (address1 == address2));
		System.out.println("address1.equals(address2) = " + address1.equals(address2));
	}
}
