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
import com.academy.model.dto.UserDTO;
import com.academy.model.security.User;
import com.academy.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	@Qualifier("userMapper")
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<UserDTO>> readAll() throws Exception {
		List<UserDTO> list = this.service.readAll().stream().map(obj -> mapper.map(obj, UserDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception {
		User entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		UserDTO dto = this.mapper.map(entity, UserDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception {
		User obj = this.service.save(this.mapper.map(dto, User.class));
		return new ResponseEntity<>(this.mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) throws Exception {
		User obj = this.service.update(this.mapper.map(dto, User.class));
		return new ResponseEntity<>(this.mapper.map(obj, UserDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		User entity = this.service.readById(id);
		if (Objects.isNull(entity)) {
			throw new ModelNotFoundException("Id not found: " + id);
		}
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
