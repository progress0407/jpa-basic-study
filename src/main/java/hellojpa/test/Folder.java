package hellojpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

import hellojpa.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
public class Folder extends File {

	@BatchSize(size = 900)
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<File> children = new ArrayList<>();

	public Folder addChildren(File... files) {
		this.children.addAll(Arrays.asList(files));
		Arrays.stream(files).forEach(file -> file.setParent(this));
		return this;
	}

	@Override
	public String toString() {
		return "Folder{}";
	}
}