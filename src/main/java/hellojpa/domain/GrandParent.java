package hellojpa.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity(name = "GRAND_PARENT")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GrandParent extends BaseEntity{

	@Id
	@GeneratedValue(generator = "uuid_gen")
	@GenericGenerator(name = "uuid_gen", strategy = "uuid")
	// @Column(columnDefinition = "BINARY(16)")
	private String id;

	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "grandParent", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@Setter
	private List<Parent> parents = new ArrayList<>();

	public void addParents(Parent... parents) {
		this.parents.addAll(Arrays.asList(parents));
		Arrays.stream(parents).forEach(parent -> parent.mapGrandParent(this));
	}
}