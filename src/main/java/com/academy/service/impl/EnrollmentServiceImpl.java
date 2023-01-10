package com.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.model.core.Enrollment;
import com.academy.repository.IEnrollmentRepository;
import com.academy.repository.IGenericRepository;
import com.academy.service.IEnrollmentService;

@Service
public class EnrollmentServiceImpl extends GenericServiceImpl<Enrollment, Integer> implements IEnrollmentService {

	@Autowired
	private IEnrollmentRepository repository;

	@Override
	protected IGenericRepository<Enrollment, Integer> getRepository() {
		return this.repository;
	}

}
