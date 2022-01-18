package hellojpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// @Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Locker {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	// public void mapMember(Member member) {
	// 	this.member = member;
	// 	member.setLocker(this);
	// }

	@Override
	public String toString() {
		return "Locker{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
