package hellojpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString
public class File {

	@Id @GeneratedValue
	private Long id;

	private String name;

	@Column(name = "IS_HIDDEN")
	private boolean isHidden;

	@JoinColumn(name = "PARENT_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private File parent;

}
