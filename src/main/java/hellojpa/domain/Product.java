package hellojpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// @Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	// @ManyToMany(mappedBy = "products")
	// private List<Member> members = new ArrayList<>();

	@OneToMany(mappedBy = "products")
	private List<MemberProduct> memberProducts = new ArrayList<>();


	public Product() {
	}

	public Product(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MemberProduct> getMemberProducts() {
		return memberProducts;
	}

	public void setMemberProducts(List<MemberProduct> memberProducts) {
		this.memberProducts = memberProducts;
	}
}
