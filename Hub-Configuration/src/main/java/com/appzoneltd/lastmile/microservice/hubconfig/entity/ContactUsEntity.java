package com.appzoneltd.lastmile.microservice.hubconfig.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_us", schema = "lastmile_core")

public class ContactUsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "language", nullable = false, length = 10)
	private String language;

	@Column(name = "hotlinenumber", nullable = false, length = 100)
	private String hotlinenumber;

	@Column(name = "hotlinetitle", length = 100)
	private String hotlinetitle;

	@Column(name = "daily_working_hours_from", length = 20)
	private String dailyWorkingHoursFrom;

	@Column(name = "daily_working_hours_to", length = 20)
	private String dailyWorkingHoursTo;

	@Column(name = "vacation_working_hours_from", length = 20)
	private String vacationWorkingHoursFrom;

	@Column(name = "vacation_working_hours_to", length = 20)
	private String vacationWorkingHoursTo;

	public ContactUsEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setHotlinenumber(String hotlinenumber) {
		this.hotlinenumber = hotlinenumber;
	}

	public String getHotlinenumber() {
		return this.hotlinenumber;
	}

	public void setHotlinetitle(String hotlinetitle) {
		this.hotlinetitle = hotlinetitle;
	}

	public String getHotlinetitle() {
		return this.hotlinetitle;
	}

	public void setDailyWorkingHoursFrom(String dailyWorkingHoursFrom) {
		this.dailyWorkingHoursFrom = dailyWorkingHoursFrom;
	}

	public String getDailyWorkingHoursFrom() {
		return this.dailyWorkingHoursFrom;
	}

	public void setDailyWorkingHoursTo(String dailyWorkingHoursTo) {
		this.dailyWorkingHoursTo = dailyWorkingHoursTo;
	}

	public String getDailyWorkingHoursTo() {
		return this.dailyWorkingHoursTo;
	}

	public void setVacationWorkingHoursFrom(String vacationWorkingHoursFrom) {
		this.vacationWorkingHoursFrom = vacationWorkingHoursFrom;
	}

	public String getVacationWorkingHoursFrom() {
		return this.vacationWorkingHoursFrom;
	}

	public void setVacationWorkingHoursTo(String vacationWorkingHoursTo) {
		this.vacationWorkingHoursTo = vacationWorkingHoursTo;
	}

	public String getVacationWorkingHoursTo() {
		return this.vacationWorkingHoursTo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(language);
		sb.append("|");
		sb.append(hotlinenumber);
		sb.append("|");
		sb.append(hotlinetitle);
		sb.append("|");
		sb.append(dailyWorkingHoursFrom);
		sb.append("|");
		sb.append(dailyWorkingHoursTo);
		sb.append("|");
		sb.append(vacationWorkingHoursFrom);
		sb.append("|");
		sb.append(vacationWorkingHoursTo);
		return sb.toString();
	}

}
