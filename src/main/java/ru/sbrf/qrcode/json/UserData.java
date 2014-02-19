package ru.sbrf.qrcode.json;

import ru.sbrf.qrcode.events.user.UserDetails;

/**
 * @author sbt-koshenkova-mv
 * 
 */
public class UserData {

	private long id;
	private String login;
	private String password;
	private String roleName;
	private boolean active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static UserData fromUserDetails(UserDetails userDetails){
		UserData userData = new UserData();
		userData.setId(userDetails.getUserId());
		userData.setLogin(userDetails.getLogin());
		userData.setActive(userDetails.isActive());
		userData.setRoleName(userDetails.getRoleName());
		return userData;
	}

	public static UserDetails toUserDetails(UserData userData) {
		UserDetails userDetails = new UserDetails();
		userDetails.setLogin(userData.getLogin());
		userDetails.setRoleName(userData.getRoleName());
		userDetails.setActive(userData.isActive());
		return userDetails;
	}
}
