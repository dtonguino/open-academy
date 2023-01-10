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
public class CourseDTO {

	@NotNull
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String name;

	@NotNull
	@NotEmpty
	@Size(max = 5)
	private String initials;

	@NotNull
	private Boolean status;
}
