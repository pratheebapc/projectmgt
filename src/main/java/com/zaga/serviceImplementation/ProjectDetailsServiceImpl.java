package com.zaga.serviceImplementation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.service.ProjectDetailsService;

@ApplicationScoped
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Inject 
    ProjectDetailsRepository repo;
}
