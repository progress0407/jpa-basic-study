package hellojpa.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Movie extends Item {

	private String director;
	private String actor;

}
