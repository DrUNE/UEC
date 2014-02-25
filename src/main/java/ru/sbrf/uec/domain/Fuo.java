package ru.sbrf.uec.domain;

import ru.sbrf.qrcode.events.dictionary.FuoDetails;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class Fuo {

	private String name;
	private String regionName;
	private String inn;
	private String kpp;
	private String address;
	private String category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getInn() {
		return inn;
	}
	public void setInn(String inn) {
		this.inn = inn;
	}
	public String getKpp() {
		return kpp;
	}
	public void setKpp(String kpp) {
		this.kpp = kpp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public static Fuo fromFuoDetails(FuoDetails fuoDetails) {
		Fuo fuo = new Fuo();
		fuo.setName(fuoDetails.getName());
		fuo.setRegionName(fuoDetails.getRegionName());
		fuo.setCategory(fuoDetails.getCategory());
		fuo.setAddress(fuoDetails.getAddress());
		fuo.setInn(fuoDetails.getInn());
		fuo.setKpp(fuoDetails.getKpp());
		return fuo;
	}
	
}
