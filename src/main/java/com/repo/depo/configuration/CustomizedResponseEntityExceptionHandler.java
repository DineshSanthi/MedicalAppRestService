package com.repo.depo.configuration;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.repo.depo.authentication.DSResponse;
import com.repo.depo.authentication.SmartGWTDSResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleExceptionInternal(
//			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		
		SmartGWTDSResponse response = new SmartGWTDSResponse();
		
		response.setResponse(new DSResponse());

		response.getResponse().setErrors(ex.getMessage().toString(), status);
		
		return new ResponseEntity<>(response, headers, status);
	}
	
}