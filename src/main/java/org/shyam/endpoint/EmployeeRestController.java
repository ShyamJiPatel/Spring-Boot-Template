package org.shyam.endpoint;

import java.util.ArrayList;

import org.shyam.entity.Employee;
import org.shyam.service.EmployeeService;
import org.shyam.util.BaseURL;
import org.shyam.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class EmployeeRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);

	private final String BASE_URL = BaseURL.EMPLOYEE;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Employee>> listAllEmployees() {
		ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.findAll();
		if (employees == null) {
			return new ResponseEntity<ArrayList<Employee>>(HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Getting the employees data : " + employees);
		return new ResponseEntity<ArrayList<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Getting the employee data : " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = BASE_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createEmployee(@RequestBody Employee employee) {
		LOGGER.info("Saving the employee data : " + employee);
		Long id = employeeService.saveOrUpdateEmployee(employee);
		LOGGER.info("Employee id  : " + id);
		return JSONUtil.toJSON("id", String.valueOf(id));
	}

	@RequestMapping(value = BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String updateEmployee(@RequestBody Employee employee) {
		LOGGER.info("Updating the employee data : " + employee);
		Long id = employeeService.saveOrUpdateEmployee(employee);
		return JSONUtil.toJSON("id", String.valueOf(id));
	}

	@RequestMapping(value = BASE_URL
			+ "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteEmployee(@PathVariable("id") long id) {
		employeeService.deleteEmployee(id);
		LOGGER.info("Employee data deleted : " + id);
		return JSONUtil.toJSON("message", "Success");
	}

	@RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String archiveEmployee(@PathVariable("id") long id) {
		employeeService.archiveEmployee(id);
		LOGGER.info("Employee data archived : " + id);
		return JSONUtil.toJSON("message", "Success");
	}

}