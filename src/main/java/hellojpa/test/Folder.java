package hellojpa.test;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;

@Entity
@Getter
public class Folder {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "IS_SHARED")
	private Boolean isShared;

	@BatchSize(size = 900)
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<File> children = new ArrayList<>();

	@OneToOne(mappedBy = "sub", fetch = FetchType.LAZY)
	private File superr;

	public void setShared(Boolean shared) {
		isShared = shared;
	}

	public void addChildren(File... files) {
		this.children.addAll(Arrays.asList(files));
		Arrays.stream(files).forEach(file -> file.setParent(this));
	}
}