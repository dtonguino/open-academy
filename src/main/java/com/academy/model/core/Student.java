package com.academy.model.core;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "core_student")
public class Student {

	@Id
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
