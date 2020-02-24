package myhealth.api.application.dto;

public abstract class OutputDto {
	
	protected String message;
	
	protected byte resultCode;
	
	public byte getResultCode() {
		return resultCode;
	}

	public void setResultCode(byte resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
