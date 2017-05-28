package com.pong.entity;

import java.io.Serializable;

/**
 * @Description GoldPrice entity bean associated with table GOLDPRICE
 * @author canpong
 * @date 2017-05-28 12:31:05
 */
public class GoldPrice implements Serializable{

	private static final long serialVersionUID = - 1534551699286241581L;
	private String guid;
	private Double price;
	private String adddate;
	private String adduser;
	private String day;

	public void setGuid(String guid){
		this.guid = guid;
	}
	public String getGuid(){
		return this.guid;
	}
	public void setPrice(Double price){
		this.price = price;
	}
	public Double getPrice(){
		return this.price;
	}
	public void setAdddate(String adddate){
		this.adddate = adddate;
	}
	public String getAdddate(){
		return this.adddate;
	}
	public void setAdduser(String adduser){
		this.adduser = adduser;
	}
	public String getAdduser(){
		return this.adduser;
	}
	public void setDay(String day){
		this.day = day;
	}
	public String getDay(){
		return this.day;
	}

}