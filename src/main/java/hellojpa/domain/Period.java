package hellojpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {

	@Column(name = "WORK_START_DATE")
	private LocalDateTime startDate;

	@Column(name = "WORK_END_DATE")
	private LocalDateTime endDate;

	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isWork() {
		LocalDateTime now = LocalDateTime.now();
		return (startDate.equals(now) || now.isAfter(startDate)) && (endDate.equals(now) || now.isBefore(endDate));
	}
}
