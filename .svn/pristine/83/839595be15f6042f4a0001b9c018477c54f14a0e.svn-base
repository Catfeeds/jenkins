package com.hfocean.uavportal.core.common.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;


/*
 * 通用异常处理类
 */
@ControllerAdvice
public class BaseControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseBusinessException.class)
    @ResponseBody
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        BaseBusinessException exception = (BaseBusinessException)ex;
        WebResponse webResponse = new WebResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(webResponse, status);
    }
    
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebRequest request)  {
		ex.printStackTrace();	
		Collection<FieldError> errors = ex.getBindingResult().getFieldErrors();
		//显示第一个返回对象字段的错误
		WebResponse webresponse = new WebResponse(status.value(),errors.iterator().next().getDefaultMessage());
		return  new ResponseEntity<Object>(webresponse,headers,status);
    }
	
	@Override	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
		//显示第一个返回对象字段的错误
		WebResponse webresponse = new WebResponse(status.value(),ex.getMessage());
		return  new ResponseEntity<Object>(webresponse,headers,status);
	}

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}