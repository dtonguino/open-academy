package com.academy.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.exceptions.ModelNotFoundException;
import com.academy.model.core.Course;
import com.academy.model.dto.CourseDTO;
import com.academy.service.ICourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private ICourseService service;

	@Autowired
	@Qualifier("courseMapper")
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<CourseDTO>> readAll() throws Exception {
		List<CourseDTO> list = this.service.readAll().stream().map(obj -> mapper.map(obj, CourseDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Course entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		CourseDTO dto = this.mapper.map(entity, CourseDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) throws Exception {
		Course obj = this.service.save(this.mapper.map(dto, Course.class));
		return new ResponseEntity<>(this.mapper.map(obj, CourseDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto) throws Exception {
		Course obj = this.service.update(this.mapper.map(dto, Course.class));
		return new ResponseEntity<>(this.mapper.map(obj, CourseDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Course entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
