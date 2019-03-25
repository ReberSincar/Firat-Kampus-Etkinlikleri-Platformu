package com.rebersincar.kampusetkinlik.Models;

public class KontrolLike {
	private boolean tf;

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"KontrolLike{" +
			"tf = '" + tf + '\'' + 
			"}";
		}
}
