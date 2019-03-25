package com.rebersincar.kampusetkinlik.Models;

public class PostPojo{
	private boolean tf;
	private String postId;
	private int userId;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"PostPojo{" + 
			"tf = '" + tf + '\'' + 
			",post_id = '" + postId + '\'' + 
			",user_id = '" + userId + '\'' + 
			"}";
		}
}
