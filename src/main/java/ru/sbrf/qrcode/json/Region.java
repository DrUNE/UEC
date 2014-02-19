package ru.sbrf.qrcode.json;

import ru.sbrf.qrcode.events.dictionary.RegionDetails;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class Region {
	
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static Region fromRegionDetails(RegionDetails regionDetails) {
		Region region = new Region();
		region.setCode(regionDetails.getCode());
		region.setName(regionDetails.getName());
		return region;
	}
	
}
