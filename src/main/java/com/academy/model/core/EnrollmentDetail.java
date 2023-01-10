package com.academy.model.core;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;

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
public class EnrollmentDetail {

	@NotNull
	@DBRef
	private Course course;

	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String classroom;

	public String getCourseName() {
		return this.course.getName();
	}
}
