package hellojpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import lombok.Builder;
import lombok.Setter;

@Embeddable
@MappedSuperclass
@Setter
public class BaseEntity {

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;

	@Column(name = "LAST_MODIFIED_AT")
	private LocalDateTime lastModifiedAt;

	public BaseEntity() {
		this.createdBy = "SYSTEM";
		this.createdAt = LocalDateTime.now();
	}
}
