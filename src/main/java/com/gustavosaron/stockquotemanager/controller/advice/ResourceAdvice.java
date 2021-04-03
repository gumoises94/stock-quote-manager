package com.gustavosaron.stockquotemanager.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustavosaron.stockquotemanager.exception.ResourceNotFoundException;
import com.gustavosaron.stockquotemanager.model.ErrorModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ControllerAdvice
public class ResourceAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorModel> sorteioSemUsuariosHandler(ResourceNotFoundException ex) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		ErrorModel error = ErrorModel.builder()
				.status(httpStatus.value())
				.error("Not Found")
				.message(ex.getMessage())
				.build();

		return ResponseEntity.status(httpStatus)
				.body(error);
	}

}
