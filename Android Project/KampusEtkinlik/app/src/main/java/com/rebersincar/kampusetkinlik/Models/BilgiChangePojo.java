package com.rebersincar.kampusetkinlik.Models;

public class BilgiChangePojo{
	private boolean tf;
	private String sonuc;

	public boolean isTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"BilgiChangePojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
