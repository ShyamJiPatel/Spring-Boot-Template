package org.shyam.service.impl;

import javax.transaction.Transactional;

import org.shyam.entity.BaseEntity;
import org.shyam.entity.Employee;
import org.shyam.repository.EmployeeRepository;
import org.shyam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.getEmployee(id, BaseEntity.getCurrentUser());
	}

	@Override
	@Transactional
	public Long saveOrUpdateEmployee(Employee employee) {
		if (employee.getId() == null) {
			employee.setCreatedBy(BaseEntity.getCurrentUser());
			employeeRepository.saveAndFlush(employee);
		} else {
			employee.setUpdatedBy(BaseEntity.getCurrentUser());
			employeeRepository.saveAndFlush(employee);
		}
		return employee.getId();
	}

	@Override
	@Transactional
	public Long deleteEmployee(Long id) {
		employeeRepository.deleteEmployee(id, BaseEntity.getCurrentUser());
		return id;
	}

	@Override
	public Iterable<Employee> findAll() {
		return employeeRepository.findAllEmployee(BaseEntity.getCurrentUser());
	}

	@Override
	@Transactional
	public Long archiveEmployee(Long id) {
		employeeRepository.archiveEmployee(id, BaseEntity.getCurrentUser());
		return id;
	}

	@Override
	public boolean isEmployeeExist(Long id) {
		return getEmployee(id) == null ? false : true;
	}

}
