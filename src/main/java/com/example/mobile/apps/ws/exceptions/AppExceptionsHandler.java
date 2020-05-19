package com.example.mobile.apps.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.mobile.apps.ws.ui.model.request.ErrorMessage;

@ControllerAdvice //Listen for all the exceptions
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {Exception.class})//method that handles exception @..
			
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDescription=ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)errorMessageDescription=ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//merge 2 exceptions in 1 method
	@ExceptionHandler(value= {NullPointerException.class,UserCustomException.class})//method that handles exception @..
	
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){
		
		String errorMessageDescription=ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)errorMessageDescription=ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

}
	/*
@ExceptionHandler(value= {UserCustomException.class})//method that handles exception @..
	
	public ResponseEntity<Object> handleUserCustomException(UserCustomException ex, WebRequest request){
		
		String errorMessageDescription=ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)errorMessageDescription=ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
}  */ //this custom exeception handler method is merged to above method.
}
