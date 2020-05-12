package model;

public class Respond {
	private boolean success = true;
	private String msg;
	public Respond() {
		success = true;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setError(String error) {
		success = false;
		msg = error;
	}
	public String getError() {
		return msg;
	}
	
}
