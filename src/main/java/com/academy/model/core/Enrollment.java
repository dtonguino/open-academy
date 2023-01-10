package com.academy.model.core;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
@Document(collection = "core_enrollment")
public class Enrollment {

	@Id
	private Integer id;

	@NotNull
	private LocalDateTime date;

	@NotNull
	@DBRef
	private Student student;

	@NotNull
	private List<EnrollmentDetail> details;

	@NotNull
	private Boolean status;

	public String getStudentName() {
		return this.student.getFirstName() + " " + this.student.getLastName();
	}
}
