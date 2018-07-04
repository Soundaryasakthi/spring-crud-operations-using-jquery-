package com.db.tis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT_DETAILS")
public class Student {
	@Id
	Long id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;
	
	@Column(name="createdDate")
	private String createdDate;
	
	@Column(name="createdUser")
	private String createdUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" "+address+" "+createdDate+" "+createdUser;
	}
}
