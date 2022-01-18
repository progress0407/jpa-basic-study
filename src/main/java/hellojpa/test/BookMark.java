package hellojpa.test;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class BookMark extends File {

	private String url;

	public BookMark setUrl(String url) {
		this.url = url;
		return this;
	}

	@Override
	public String toString() {
		return "BookMark{}";
	}
}
