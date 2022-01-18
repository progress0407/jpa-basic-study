package hellojpa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Builder
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	@Embedded
	private Period period;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(
			name = "city",
			column = @Column(name = "HOME_CITY")
		),
		@AttributeOverride(name = "street",
			column = @Column(name = "HOME_STREET")
		),
		@AttributeOverride(name = "zipcode",
			column = @Column(name = "HOME_ZIPCODE")
		)
	})
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(
			name = "city",
			column = @Column(name = "WORK_CITY")
		),
		@AttributeOverride(name = "street",
			column = @Column(name = "WORK_STREET")
		),
		@AttributeOverride(name = "zipcode",
			column = @Column(name = "WORK_ZIPCODE")
		)
	})
	private Address workAddress;

	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD" ,
		joinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "ADDRESS" ,
		joinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private List<Address> addressHistory = new ArrayList<>();

	// @OneToOne(mappedBy = "member", fetch = FetchType.EAGER)
	// private Locker locker;

	// public void setLocker(Locker locker) {
	// 	this.locker = locker;
	// }

	// @ManyToMany
	// @JoinColumn(name = "PRODUCT_ID")
	// @JoinTable(name = "MEMBER_PRODUCT")
	// private List<Product> products = new ArrayList<>();

	// @OneToMany(mappedBy = "members")
	// private List<MemberProduct> memberProducts = new ArrayList<>();

	// private Integer age;

	// @Enumerated(EnumType.STRING)
	// private RoleType roleType;

	// @Temporal(TemporalType.DATE)
	// private Date createDate;

	// @Temporal(TemporalType.DATE)
	// private Date lastModifiedDate;

	// @Lob
	// private String description;

	// @Transient
	// private String temp;

	public void addTeam(Team team) {
		this.team = team;
		team.addMembers(this);
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", name='" + name + '\'' +
			", homeAddress=" + homeAddress +
			", workAddress=" + workAddress +
			'}';
	}
}
