package hellojpa.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;

// @Entity
@Getter
public class BookMark extends File {

	@Id
	@GeneratedValue
	private Long id;

	private String url;

}
