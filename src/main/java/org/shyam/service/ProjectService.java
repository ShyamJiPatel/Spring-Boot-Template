package org.shyam.service;

import org.shyam.entity.Project;

public interface ProjectService {

	public Project getProject(Long id);

	public Iterable<Project> findAll();

	public Long saveOrUpdateProject(Project project);

	public Long deleteProject(Long id);

	public Long archiveProject(Long id);

	public boolean isProjectExist(Long id);

}
