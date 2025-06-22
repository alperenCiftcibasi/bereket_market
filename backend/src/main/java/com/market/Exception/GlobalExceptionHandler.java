package com.market.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
	    Map<String, List<String>> errorsMap = new HashMap<>();

	    for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
	        String fieldName = ((FieldError) objError).getField();
	        String message = objError.getDefaultMessage();

	        errorsMap.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(message);
	    }

	    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
	}

}
