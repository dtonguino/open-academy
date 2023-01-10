package com.academy.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.academy.model.core.EnrollmentDetail;
import com.academy.model.core.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class EnrollmentDTO {
	
	@NotNull
	private Integer id;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	
	@NotNull
	private Student student;
	
	@NotNull
	private List<EnrollmentDetail> details;
	
	@NotNull
	private Boolean status;
}
