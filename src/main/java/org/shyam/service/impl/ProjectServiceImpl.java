package org.shyam.service.impl;

import javax.transaction.Transactional;

import org.shyam.entity.BaseEntity;
import org.shyam.entity.Project;
import org.shyam.repository.ProjectRepository;
import org.shyam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project getProject(Long id) {
		return projectRepository.getProject(id, BaseEntity.getCurrentUser());
	}

	@Override
	@Transactional
	public Long saveOrUpdateProject(Project project) {
		if (project.getId() == null) {
			project.setCreatedBy(BaseEntity.getCurrentUser());
			projectRepository.saveAndFlush(project);
		} else {
			project.setUpdatedBy(BaseEntity.getCurrentUser());
			projectRepository.saveAndFlush(project);
		}
		return project.getId();
	}

	@Override
	@Transactional
	public Long deleteProject(Long id) {
		projectRepository.deleteProject(id, BaseEntity.getCurrentUser());
		return id;
	}

	@Override
	public Iterable<Project> findAll() {
		return projectRepository.findAll(BaseEntity.getCurrentUser());
	}

	@Override
	@Transactional
	public Long archiveProject(Long id) {
		projectRepository.archiveProject(id, BaseEntity.getCurrentUser());
		return id;
	}

	@Override
	public boolean isProjectExist(Long id) {
		return getProject(id) == null ? false : true;
	}

}
