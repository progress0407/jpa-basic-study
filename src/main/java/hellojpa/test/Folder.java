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

// @Entity
public class Folder {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "IS_SHARED")
	private Boolean isShared;


}