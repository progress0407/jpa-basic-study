package hellojpa.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent {

	@Id @GeneratedValue
	private Long id;

	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Child> children = new ArrayList<>();

	@JoinColumn(name = "GRAND_PARENT_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private GrandParent grandParent;

	public void addChildren(Child... children) {
		this.children.addAll(Arrays.asList(children));
		Arrays.stream(children).forEach(child -> child.mapParent(this));
	}

	public void mapGrandParent(GrandParent grandParent){
		this.grandParent = grandParent;
	}
}
