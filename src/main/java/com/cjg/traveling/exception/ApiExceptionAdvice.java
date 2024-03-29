package com.cjg.traveling.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice("com.cjg.traveling")
public class ApiExceptionAdvice {
	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e){
		e.printStackTrace();
		return ResponseEntity.status(e.getError().getStatus())
				.body(ApiExceptionEntity.builder()
						.code(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
						.message(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
				.body(ApiExceptionEntity.builder()
						.code(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
						.message(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
				.body(ApiExceptionEntity.builder()
						.code(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
						.message(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
				.body(ApiExceptionEntity.builder()
						.code(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
						.message(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ApiExceptionEntity> methodArgumentTypeMismatchException(HttpServletRequest request, final Exception e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.PARAM_ERROR.getStatus())
				.body(ApiExceptionEntity.builder()
						.code(ExceptionEnum.PARAM_ERROR.getCode())
						.message(e.getMessage())
						.build());
	}	
}
