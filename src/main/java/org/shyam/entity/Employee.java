package org.shyam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String address;

	public Employee() {
		super();
	}

	public Employee(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", email=" + email + ", address=" + address + ", id=" + id + "]";
	}

}
