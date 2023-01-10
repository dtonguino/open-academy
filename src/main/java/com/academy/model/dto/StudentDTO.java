package com.academy.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

	@NotNull
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String lastName;

	@NotNull
	@NotEmpty
	@Size(max = 13)
	private String dni;

	@NotNull
	private Integer age;

}
