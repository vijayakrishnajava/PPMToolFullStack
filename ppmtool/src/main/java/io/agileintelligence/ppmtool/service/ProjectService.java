package io.agileintelligence.ppmtool.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exception.SqlBadRequestException;
import io.agileintelligence.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	
	public Project saveOrUpdateProject(Project project) {
		
		Project out = null;
		
		try {
			out = this.projectRepository.save(project);
		} catch (RuntimeException e) {

			SqlBadRequestException exception = new SqlBadRequestException("projectIdentifier", "value should be unique", e, e.getMessage());
			throw exception;
		}
		
		return out;
	}
	
	
	
	public Project findProject(String identifier) {
		
		Project project = this.projectRepository.findByProjectIdentifier(identifier);
		
		if(project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		return project;
	}
	
	public Iterable<Project> findAll() {
		
		return this.projectRepository.findAll();
	}
	
	public void deleteProject(String identifier) {
		
		Project project = this.projectRepository.findByProjectIdentifier(identifier);
		
		if(project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		this.projectRepository.delete(project);
	}
}
