package hellojpa.test;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
abstract public class File {

	@Id @GeneratedValue
	private Long id;

	private String name;

	@JoinColumn(name = "PARENT_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private File parent;

	@JoinColumn(name = "SUB_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private File sub;

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(File parent) {
		this.parent = parent;
	}
}
