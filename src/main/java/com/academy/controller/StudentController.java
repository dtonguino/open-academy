package com.academy.controller;

import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.exceptions.ModelNotFoundException;
import com.academy.model.core.Student;
import com.academy.model.dto.StudentDTO;
import com.academy.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private IStudentService service;

	@Autowired
	@Qualifier("studentMapper")
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<StudentDTO>> readAll() throws Exception {
		List<StudentDTO> list = this.service.readAll().stream().map(obj -> mapper.map(obj, StudentDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception {
		Student entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		StudentDTO dto = this.mapper.map(entity, StudentDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/sortedByAge")
	public ResponseEntity<List<StudentDTO>> readByAge(
			@RequestParam(name = "order", defaultValue = "asc", required = false) String order) throws Exception {
		Comparator<Student> orderCompare = "desc".equals(order) ? (s1, s2) -> (s2.getAge() - s1.getAge())
				: Comparator.comparing(Student::getAge);
		List<StudentDTO> list = this.service.readAll().stream().sorted(orderCompare)
				.map(obj -> mapper.map(obj, StudentDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception {
		Student obj = this.service.save(this.mapper.map(dto, Student.class));
		return new ResponseEntity<>(this.mapper.map(obj, StudentDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto) throws Exception {
		Student obj = this.service.update(this.mapper.map(dto, Student.class));
		return new ResponseEntity<>(this.mapper.map(obj, StudentDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Student entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
