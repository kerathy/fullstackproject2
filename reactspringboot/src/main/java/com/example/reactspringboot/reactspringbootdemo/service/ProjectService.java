package com.example.reactspringboot.reactspringbootdemo.service;

import com.example.reactspringboot.reactspringbootdemo.domain.Project;
import com.example.reactspringboot.reactspringbootdemo.exceptions.ProjectIdException;
import com.example.reactspringboot.reactspringbootdemo.repositories.ProjectRepository;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
//        System.out.println(project);
//        System.out.println(project.getCreated_At());
//        System.out.println(project.getProjectName());
//        System.out.println(project.getProjectIdentifier());

        try {
            project.setProjectIdentifier(project.getProjectIdentifier());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("project identifier:  " + project.getProjectIdentifier()+ "already exists");
        }
    }

   public Project findProjectByProjectIdentifier(String projectId) {
        try {
            System.out.println(projectRepository.findProjectByProjectIdentifier(projectId).getProjectIdentifier());
            return projectRepository.findProjectByProjectIdentifier(projectId);
        } catch (Exception e) {
            throw new ProjectIdException("project identifier: " + projectId + "  does not exist");
        }
   }

   public Iterable<Project> findAllProjects() {

        Iterable<Project> all = projectRepository.findAll();
        System.out.println(all);
        System.out.println(all.getClass());

        for (Project p : all) {
            System.out.println(p.getProjectIdentifier());
            System.out.println(p.getProjectName());
            System.out.println(p.getProjectDescription());
        }
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project p = projectRepository.findProjectByProjectIdentifier(projectId);
        try {
            System.out.println(p.getProjectIdentifier());
            projectRepository.delete(p);
        } catch (Exception e) {
            throw new ProjectIdException("S... project not found, projectId is: " + projectId);
        }
    }

}
