package com.example.demo.model.UserDTO;

public class UserDTO {

	private String userid;
	private String userpw;
	private String username;
	private String userphone;
	private String useraddr;
	private int userReward;
	private String userbirth;
	private String usergender;
	private String userhomenum;
	private String userjoin;
	private int userpoint;

	// Getters and Setters
	public String getUserid() {
		return userid;
	}	

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getUserReward() {
		return userReward;
	}
	
	public void setUserReward(int userReward) {
		this.userReward = userReward;
	}
	
	public int getUserpoint() {
		return userpoint;
	}
	
	public void setUserpoint(int userpoint) {
		this.userpoint = userpoint;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserbirth() {
		return userbirth;
	}

	public void setUserbirth(String userbirth) {
		this.userbirth = userbirth;
	}

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public String getUserhomenum() {
		return userhomenum;
	}

	public void setUserhomenum(String userhomenum) {
		this.userhomenum = userhomenum;
	}

	public String getUserjoin() {
		return userjoin;
	}

	public void setUserjoin(String userjoin) {
		this.userjoin = userjoin;
	}
}