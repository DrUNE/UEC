/**
 * 
 */
package ru.sbrf.qrcode.json;

import java.util.Date;

import ru.sbrf.qrcode.events.application.ApplicationInfoDetails;

/**
 * @author sbt-koshenkova-mv
 * 
 */
public class ApplicationInfo {

	private String sin;
	private String status;
	private Date changeDate;
	private String packageName;

	public String getSin() {
		return sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public static ApplicationInfo fromApplicationInfoDetails(ApplicationInfoDetails appInfoDetails) {
		ApplicationInfo applicationInfo = new ApplicationInfo();
		applicationInfo.setSin(appInfoDetails.getSin());
		applicationInfo.setStatus(appInfoDetails.getStatus());
		applicationInfo.setChangeDate(appInfoDetails.getChangeDate());
		applicationInfo.setPackageName(appInfoDetails.getPackageName());
		return applicationInfo;
	}

}
