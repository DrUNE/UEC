package ru.sbrf.uec.domain;

/**
 * @author sbt-koshenkova-mv
 * 
 * 15 февр. 2014 г. 22:49:48
 */
public class RequestResult {
	
	private ResultCode resultCode;
	private String resultMessage;
	
	public ResultCode getResultCode() {
		return resultCode;
	}
	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
