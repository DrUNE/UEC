package ru.sbrf.qrcode.json;

import ru.sbrf.qrcode.events.dictionary.StatusDetails;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class Status {
	
	private String code;
	private String name;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static Status fromStatusDetails(StatusDetails statusDetails) {
		Status status = new Status();
		status.setCode(statusDetails.getCode());
		status.setName(statusDetails.getName());
		status.setDescription(statusDetails.getDescription());
		return status;
	}

}
