package com.academy.config.security;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.academy.exceptions.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1052752975842768226L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String exceptionMsg = (String) request.getAttribute("exception");

		if (exceptionMsg == null) {
			exceptionMsg = "Token not found";
		}

		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exceptionMsg, request.getRequestURI());

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(convertObjectToJson(errorResponse));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		return mapper.writeValueAsString(object);
	}
}
