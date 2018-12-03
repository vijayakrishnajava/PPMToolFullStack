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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
}
