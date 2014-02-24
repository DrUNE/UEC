package ru.sbrf.qrcode.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.sbrf.qrcode.events.application.ApplicationDetails;
import ru.sbrf.qrcode.events.application.ApplicationInfoDetails;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class Application {
	
	private String sin;
	private Date date;
	private String uosName;
	private long number;
	private String regionName;
	private String clientFIO;
	private String currentStatus;
	private List<ApplicationInfo> infos = new ArrayList<ApplicationInfo>();
	
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
	public String getUosName() {
		return uosName;
	}
	public void setUosName(String uosName) {
		this.uosName = uosName;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getClientFIO() {
		return clientFIO;
	}
	public void setClientFIO(String clientFIO) {
		this.clientFIO = clientFIO;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public List<ApplicationInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<ApplicationInfo> infos) {
		this.infos = infos;
	}
	public void addInfo(ApplicationInfo info){
		this.infos.add(info);
	}
	public static Application fromApplicationDetails(ApplicationDetails applicationDetails) {
		Application application = new Application();
		application.setSin(applicationDetails.getSin());
		application.setDate(applicationDetails.getDate());
		application.setUosName(applicationDetails.getUosName());
		application.setNumber(applicationDetails.getNumber());
		application.setRegionName(applicationDetails.getRegionName());
		application.setClientFIO(applicationDetails.getClientFIO());
		application.setCurrentStatus(applicationDetails.getCurrentStatus());
		if(applicationDetails.getInfos()!=null && !applicationDetails.getInfos().isEmpty()){
			for(ApplicationInfoDetails applicationInfoDetails:applicationDetails.getInfos())
				application.addInfo(ApplicationInfo.fromApplicationInfoDetails(applicationInfoDetails));
		}
		return application;
	}

}
