package org.shyam.repository;

import java.util.List;

import org.shyam.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT p FROM Employee p WHERE p.id = :id AND p.createdBy = :createdBy AND p.archived = false")
	Employee getEmployee(@Param("id") Long id, @Param("createdBy") String createdBy);

	@Query(value = "SELECT p FROM Employee p WHERE p.createdBy = :createdBy AND p.archived = false")
	List<Employee> findAllEmployee(@Param("createdBy") String createdBy);

	@Query(value = "UPDATE Employee p SET  p.archived = true WHERE p.id = :id AND p.createdBy = :createdBy")
	Boolean archiveEmployee(@Param("id") Long id, @Param("createdBy") String createdBy);

	@Query(value = "DELETE Employee p WHERE p.id = :id AND p.createdBy = :createdBy AND p.archived = false")
	Boolean deleteEmployee(@Param("id") Long id, @Param("createdBy") String createdBy);
}
