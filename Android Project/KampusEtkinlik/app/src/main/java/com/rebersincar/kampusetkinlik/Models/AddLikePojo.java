package com.rebersincar.kampusetkinlik.Models;

public class AddLikePojo{
	private boolean tf;
	private String sonuc;

	public boolean getTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"AddLikePojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
