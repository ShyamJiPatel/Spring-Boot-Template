package org.shyam.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.shyam.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT p FROM Employee p WHERE p.id = :id AND p.createdUserId = :userId AND p.archived = false")
	Employee getEmployee(@Param("id") Long id, @Param("userId") Long userId);

	@Query(value = "SELECT p FROM Employee p WHERE p.createdUserId = :userId AND p.archived = false")
	List<Employee> findAllEmployee(@Param("userId") Long userId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Employee p SET  p.archived = true WHERE p.id = :id AND p.createdUserId = :userId")
	void archiveEmployee(@Param("id") Long id, @Param("userId") Long userId);

	@Transactional
	@Modifying
	@Query(value = "DELETE Employee p WHERE p.id = :id AND p.createdUserId = :userId AND p.archived = false")
	void deleteEmployee(@Param("id") Long id, @Param("userId") Long userId);
}
