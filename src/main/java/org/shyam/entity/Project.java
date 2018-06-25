package org.shyam.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	@Column
	private String description;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
	private Set<Employee> employees;

	public Project() {
		super();
	}

	public Project(Long id, String name, String description, Set<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", description=" + description + ", employees=" + employees + ", id=" + id
				+ "]";
	}

}
