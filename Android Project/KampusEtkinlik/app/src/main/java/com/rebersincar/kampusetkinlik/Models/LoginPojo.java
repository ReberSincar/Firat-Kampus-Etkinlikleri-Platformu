package com.rebersincar.kampusetkinlik.Models;

public class LoginPojo{
	private Object admin;
	private int id;
	private Object email;


	public Object getAdmin(){
		return admin;
	}

	public int getId(){
		return id;
	}

	public void setEmail(Object email){
		this.email = email;
	}

	public Object getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"LoginPojo{" + 
			"admin = '" + admin + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
