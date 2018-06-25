package org.shyam.repository;

import java.util.List;

import org.shyam.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value = "SELECT p FROM Project p WHERE p.id = :id AND p.createdBy = :createdBy AND p.archived = false")
	Project getProject(@Param("id") Long id, @Param("createdBy") String createdBy);

	@Query(value = "SELECT p FROM Project p WHERE p.createdBy = :createdBy AND p.archived = false")
	List<Project> findAll(@Param("createdBy") String createdBy);

	@Query(value = "UPDATE Project p SET  p.archived = true WHERE p.id = :id AND p.createdBy = :createdBy")
	Boolean archiveProject(@Param("id") Long id, @Param("createdBy") String createdBy);

	@Query(value = "DELETE Project p WHERE p.id = :id AND p.createdBy = :createdBy AND p.archived = false")
	Boolean deleteProject(@Param("id") Long id, @Param("createdBy") String createdBy);
}
