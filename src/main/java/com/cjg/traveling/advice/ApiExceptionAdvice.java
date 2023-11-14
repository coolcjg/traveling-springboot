package com.cjg.traveling.advice;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cjg.traveling.entity.ApiExceptionEntity;
import com.cjg.traveling.enums.ExceptionEnum;
import com.cjg.traveling.exception.ApiException;

@RestControllerAdvice
public class ApiExceptionAdvice {
	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e){
		e.printStackTrace();
		return ResponseEntity.status(e.getError().getStatus())
				.body(ApiExceptionEntity.builder()
						.errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
				.body(ApiExceptionEntity.builder()
						.errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
				.body(ApiExceptionEntity.builder()
						.errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e){
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
				.body(ApiExceptionEntity.builder()
						.errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
	

}