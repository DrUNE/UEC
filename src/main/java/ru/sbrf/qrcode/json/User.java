package ru.sbrf.qrcode.json;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author sbt-koshenkova-mv
 * 
 * 15 февр. 2014 г. 22:37:08
 */
public class User implements Serializable {

	private static final long serialVersionUID = -4086430618323997804L;
	
	private String login;
	private String password;
	private int roleId;
	
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
