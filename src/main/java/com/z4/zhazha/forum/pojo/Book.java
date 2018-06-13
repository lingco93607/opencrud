package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.z4.zhazha.forum.annotation.Autocode;

@Entity
//@Autocode
public class Book {
	
	@Id
	private int id;
	private String name;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
