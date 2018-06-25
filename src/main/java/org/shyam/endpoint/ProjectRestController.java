package org.shyam.endpoint;

import java.util.ArrayList;
import org.shyam.entity.Project;
import org.shyam.service.ProjectService;
import org.shyam.util.BaseURL;
import org.shyam.util.JSONUtil;
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
public class ProjectRestController {

	private final String BASE_URL = BaseURL.PROJECT;

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Project>> listAllProjects() {
		ArrayList<Project> projects = (ArrayList<Project>) projectService.findAll();
		if (projects == null) {
			return new ResponseEntity<ArrayList<Project>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArrayList<Project>>(projects, HttpStatus.OK);
	}

	@RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
		Project project = projectService.getProject(id);
		if (project == null) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@RequestMapping(value = BASE_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createProject(@RequestBody Project project) {
		Long id = projectService.saveOrUpdateProject(project);
		return JSONUtil.toJSON("id", String.valueOf(id));
	}

	@RequestMapping(value = BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String updateProject(@RequestBody Project project) {
		Long id = projectService.saveOrUpdateProject(project);
		return JSONUtil.toJSON("id", String.valueOf(id));
	}

	@RequestMapping(value = BASE_URL
			+ "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteProject(@PathVariable("id") long id) {
		projectService.deleteProject(id);
		return JSONUtil.toJSON("message", "Success");
	}

	@RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String archiveProject(@PathVariable("id") long id) {
		projectService.archiveProject(id);
		return JSONUtil.toJSON("message", "Success");
	}

}