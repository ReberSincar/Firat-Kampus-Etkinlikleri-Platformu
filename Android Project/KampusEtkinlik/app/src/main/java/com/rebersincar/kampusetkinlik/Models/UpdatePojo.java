package com.rebersincar.kampusetkinlik.Models;

public class UpdatePojo{
	private boolean tf;
	private int postId;
	private int userId;

	public boolean isTf(){
		return tf;
	}

	public int getPostId(){
		return postId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"UpdatePojo{" + 
			"tf = '" + tf + '\'' + 
			",post_id = '" + postId + '\'' + 
			",user_id = '" + userId + '\'' + 
			"}";
		}
}
