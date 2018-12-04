package io.agileintelligence.ppmtool.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.service.MapValidationService;
import io.agileintelligence.ppmtool.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	MapValidationService mapValidationService;
	
	@PostMapping
	ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		
		ResponseEntity<?> errors = this.mapValidationService.MapvalidatedResults(result);
		
		if (errors != null) {
			return errors;
		}
		
		Project response = this.projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(response, HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/{identifier}")
	public ResponseEntity<?> findProject(@PathVariable("identifier") String identiier) {
		
		Project project = this.projectService.findProject(identiier);
		
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		
		return new ResponseEntity<>(this.projectService.findAll(), HttpStatus.OK); 
		
	}
	
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteProject(@PathVariable String identifier) { 
		
		this.projectService.deleteProject(identifier);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
