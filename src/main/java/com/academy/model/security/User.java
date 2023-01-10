package com.academy.model.security;

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
@Document(collection = "seg_user")
public class User {

	@Id
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String username;

	@NotNull
	@NotEmpty
	@Size(max = 60)
	private String password;

	@NotNull
	private Role role;

	@NotNull
	private Boolean enabled;

}
