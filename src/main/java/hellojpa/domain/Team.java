package hellojpa.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

	@Id
	@GeneratedValue
	private Long id;

	@Builder.Default
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();

	private String name;

	/**
	 * Setter 생성을 노출하면 이전의 회원 객체들의 정보가 날라갈 수 있다
	 */
	private void setMembers(List<Member> members) {
		this.members = members;
	}

	public void addMembers(Member... members) {
		this.members.addAll(Arrays.asList(members));
	}

	@Override
	public String toString() {
		return "Team{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}

