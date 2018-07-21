package com.siemens.mindsphere.apps.exceptionHandler;

import com.siemens.mindsphere.apps.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
/*
   @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<List<ExceptionResponse>> userNotFound(ResourceNotFoundException ex) {
        List<ExceptionResponse> exceptionResponseImplList = new ArrayList<>();
        ExceptionResponseImpl exceptionResponseImpl = new ExceptionResponseImpl();
        exceptionResponseImpl.setErrorCode(ex.getErrorCode());
        exceptionResponseImpl.setErrorMessage(ex.getMessage());
        exceptionResponseImplList.add(exceptionResponseImpl);
        return new ResponseEntity<List<ExceptionResponse>>(exceptionResponseImplList, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        ValidationExceptionResponseImpl validationExceptionResponse = new ValidationExceptionResponseImpl();

        List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> new ApiFieldError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getRejectedValue()))
                .collect(toList());

        List<ApiGlobalError> apiGlobalErrors = bindingResult.getGlobalErrors().stream()
                .map(globalError -> new ApiGlobalError(globalError.getCode()))
                .collect(toList());

        ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors);
        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
