package com.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.model.core.Course;
import com.academy.repository.ICourseRepository;
import com.academy.repository.IGenericRepository;
import com.academy.service.ICourseService;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, Integer> implements ICourseService {

	@Autowired
	private ICourseRepository repository;

	@Override
	protected IGenericRepository<Course, Integer> getRepository() {
		return this.repository;
	}

}
