package myhealth.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import myhealth.api.APILogUtils;

@ControllerAdvice
public class APIExceptionHandler {
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		e.printStackTrace();
		APILogUtils.info("JSONフォーマットエラー");
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<String>("リクエストJSONの確認をしてください。",status);
	}
	
}
