package com.wan.grace.mvpapplication.bean;

import java.io.Serializable;

public class User implements Serializable {
	private String loginId;//登录用户名
	private String id;//用户id"userid": "sdlu044",
	private String username;//"userName": "符云峰",
	private String companyname;
	private String pwd;
	private String pricinctname;//铁塔人员登陆时的区域管辖集合
	private boolean isRemember;
	private boolean isAutoLogin;//是否自动登录
	private boolean isNavigation;
	private String array;//权限

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getId() {
		return id;
	}

	public void setId(String userid) {
		this.id = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPricinctname() {
		return pricinctname;
	}

	public void setPricinctname(String pricinctname) {
		this.pricinctname = pricinctname;
	}

	public boolean isRemember() {
		return isRemember;
	}

	public void setRemember(boolean remember) {
		isRemember = remember;
	}

	public boolean isAutoLogin() {
		return isAutoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		isAutoLogin = autoLogin;
	}

	public boolean isNavigation() {
		return isNavigation;
	}

	public void setNavigation(boolean navigation) {
		isNavigation = navigation;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

	@Override
	public String toString() {
		return "User{" +
				"loginId='" + loginId + '\'' +
				", id='" + id + '\'' +
				", username='" + username + '\'' +
				", companyname='" + companyname + '\'' +
				", pwd='" + pwd + '\'' +
				", pricinctname='" + pricinctname + '\'' +
				", isRemember=" + isRemember +
				", isAutoLogin=" + isAutoLogin +
				", isNavigation=" + isNavigation +
				", array='" + array + '\'' +
				'}';
	}
}
