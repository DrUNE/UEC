package ru.sbrf.uec.domain;

import java.util.Date;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class ApplicationFilter {

	private String sin;
	private Date date;
	private int uosId;
	private int regionId;
	private int statusId;
	
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUosId() {
		return uosId;
	}
	public void setUosId(int uosId) {
		this.uosId = uosId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
}
