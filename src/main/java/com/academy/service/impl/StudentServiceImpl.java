package com.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.model.core.Student;
import com.academy.repository.IGenericRepository;
import com.academy.repository.IStudentRepository;
import com.academy.service.IStudentService;

@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, Integer> implements IStudentService {

	@Autowired
	private IStudentRepository repository;

	@Override
	protected IGenericRepository<Student, Integer> getRepository() {
		return this.repository;
	}

}
