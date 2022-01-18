package hellojpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// @Entity(name = "MEMBER_PRODUCT")
public class MemberProduct {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member members;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product products;

	@Column(name = "ORDER_COUNT")
	private Long orderCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMembers() {
		return members;
	}

	public void setMembers(Member members) {
		this.members = members;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}
}
