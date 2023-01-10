package com.academy.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
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
import com.academy.model.core.Enrollment;
import com.academy.model.dto.EnrollmentDTO;
import com.academy.service.IEnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	@Autowired
	private IEnrollmentService service;

	@Autowired
	@Qualifier("enrollmentMapper")
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception {
		List<EnrollmentDTO> list = this.service.readAll().stream().map(obj -> mapper.map(obj, EnrollmentDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Enrollment entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		EnrollmentDTO dto = this.mapper.map(entity, EnrollmentDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("studentsPerCourse")
	public ResponseEntity<Map<Object, List<Object>>> studentsPerCourse() throws Exception {
		List<Enrollment> list = this.service.readAll();
		Map<Object, List<Object>> map = list.stream().map(s -> {
			return s.getDetails().stream().map(m -> {
				return Pair.of(m.getCourseName(), s.getStudentName());
			}).collect(Collectors.toList());
		}).flatMap(List::stream).collect(
				Collectors.groupingBy(Pair::getFirst, Collectors.mapping(Pair::getSecond, Collectors.toList())));
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EnrollmentDTO> create(@Valid @RequestBody EnrollmentDTO dto) throws Exception {
		Enrollment obj = this.service.save(this.mapper.map(dto, Enrollment.class));
		return new ResponseEntity<>(this.mapper.map(this.service.readById(obj.getId()), EnrollmentDTO.class),
				HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<EnrollmentDTO> update(@Valid @RequestBody EnrollmentDTO dto) throws Exception {
		Enrollment obj = this.service.update(this.mapper.map(dto, Enrollment.class));
		return new ResponseEntity<>(this.mapper.map(this.service.readById(obj.getId()), EnrollmentDTO.class),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Enrollment entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
