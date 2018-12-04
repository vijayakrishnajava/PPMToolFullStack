package io.agileintelligence.ppmtool.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(SqlBadRequestException.class)
	public ResponseEntity<?> handleSqlException(SqlBadRequestException sqlBadRequestException, WebRequest request) {
		
		
		sqlBadRequestException.printStackTrace();
		
		Map<String, String> map = new HashMap<>();
		
		map.put(sqlBadRequestException.getColumn(), sqlBadRequestException.getErrorMessage());
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		
	}
}
