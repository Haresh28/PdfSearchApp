package com.register;
import com.opensymphony.xwork2.ActionSupport;
public class RegisterUser extends ActionSupport {
	private String user;
	private String password;
	private String message;
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String execute() {
		int i=RegisterAction.register(this);
		if(i>0)
			return SUCCESS;
		else {
			setMessage("Registration Failed!");
			return ERROR;
		}
	}

}
