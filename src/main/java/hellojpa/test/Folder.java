package hellojpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Folder extends File {

	@OneToMany
	@ToString.Exclude
	private List<File> children = new ArrayList<>();

	@Column(name = "IS_SHARED")
	private boolean isShared;

	public void addChildren(File... children) {
		this.children.addAll(Arrays.asList(children));
		Arrays.stream(children).forEach(child -> child.setParent(this));
	}
}