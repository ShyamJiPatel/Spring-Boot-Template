package org.shyam.service;

import org.shyam.entity.Employee;

public interface EmployeeService {

	public Employee getEmployee(Long id);

	public Iterable<Employee> findAll();

	public Long saveOrUpdateEmployee(Employee employee);

	public Long deleteEmployee(Long id);

	public Long archiveEmployee(Long id);

	public boolean isEmployeeExist(Long id);

}
