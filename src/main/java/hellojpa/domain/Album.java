package hellojpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@DiscriminatorValue("A")
public class Album extends Item {

	private String artist;

	public Album() {
	}


}
