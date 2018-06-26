package org.shyam.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.shyam.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value = "SELECT p FROM Project p WHERE p.id = :id AND p.createdUserId = :userId AND p.archived = false")
	Project getProject(@Param("id") Long id, @Param("userId") Long userId);

	@Query(value = "SELECT p FROM Project p WHERE p.createdUserId = :userId AND p.archived = false")
	List<Project> findAll(@Param("userId") Long userId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Project p SET  p.archived = true WHERE p.id = :id AND p.createdUserId = :userId")
	void archiveProject(@Param("id") Long id, @Param("userId") Long userId);

	@Transactional
	@Query(value = "DELETE Project p WHERE p.id = :id AND p.createdUserId = :userId AND p.archived = false")
	void deleteProject(@Param("id") Long id, @Param("userId") Long userId);
}
