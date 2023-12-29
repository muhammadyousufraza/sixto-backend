package com.example.practice.project.config;

import static com.example.practice.project.utilities.Constants.MAX_UPLOAD_SIZE_EXCEEDED;
import static com.example.practice.project.utilities.Constants.NOT_FOUND;
import static com.example.practice.project.utilities.Constants.SERVER_ERROR;
import static com.example.practice.project.utilities.Constants.SOMETHING_WENT_WRONG;
import static com.example.practice.project.utilities.Constants.UNAUTHORIZED;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.customexception.InvalidGrantException;
import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.model.response.ExceptionResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Summary :: For custom error handling.This method will catch any type of Java exceptions that get thrown.
     *
     * @param ex exception
     * @return ResponseEntity Object
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        if (ex.getCause() != null) {
            details.add(ex.getCause().getMessage());
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("Handle All Exception :" + ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(SOMETHING_WENT_WRONG).details(details).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    /**
     * Summary :: for custom error handling.
     * maxUploadSizeExceededException
     *
     * @param ex ex
     * @return ResponseEntity
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<Object> maxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        List<String> details = new ArrayList<>();
        details.add(MAX_UPLOAD_SIZE_EXCEEDED);
        log.error("Handle All Exception :" + ex.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(MAX_UPLOAD_SIZE_EXCEEDED).details(details).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    /**
     * Summary :: for custom error handling.
     * invalidGrantException
     *
     * @param ex      InvalidGrantException
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(InvalidGrantException.class)
    public final ResponseEntity<Object> invalidGrantException(InvalidGrantException ex, WebRequest request) {

        List<String> details = new ArrayList<>();
        if (ex.getCause() != null) {
            details.add(ex.getCause().getMessage());
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("Invalid Grant Exception :" + ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(UNAUTHORIZED).details(details).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    /**
     * handleEmployeeExceptions.
     *
     * @param ex BusinessException
     * @return ResponseEntity
     */
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleEmployeeExceptions(BusinessException ex) {

        List<String> details = new ArrayList<>();
        if (ex.getCause() != null) {
            details.add(ex.getCause().getMessage());
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("Business Exception :" + ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(SOMETHING_WENT_WRONG).build();
        if (ex.getCustomObject() != null) {
            exceptionResponse.setDetails(ex.getCustomObject());
        } else {
            exceptionResponse.setDetails(details);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * handleNotFoundExceptions.
     *
     * @param ex NotFoundException
     * @return ResponseEntity
     */
    @ExceptionHandler({NotFoundException.class, EmptyResultDataAccessException.class})
    public final ResponseEntity<Object> handleNotFoundExceptions(NotFoundException ex) {
        List<String> details = new ArrayList<>();
        if (ex.getCause() != null) {
            details.add(ex.getCause().getMessage());
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("Handle Not Found Exception :" + ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(NOT_FOUND).details(details).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    /**
     * handleConstraintViolationExceptions.
     *
     * @param ex ConstraintViolationException
     * @return ResponseEntity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        if (ex.getCause() != null) {
            details.add(ex.getCause().getMessage());
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("Handle Constraint Violation Exception :" + ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(SERVER_ERROR).details(details).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (ex.getCause() != null) {
                details.add(ex.getCause().getMessage());
            } else {
                details.add(error.getDefaultMessage());
            }
            log.error("Handle Method Argument Not Valid Exception :" + error.getDefaultMessage());
        }
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().status(false).message(SERVER_ERROR).details(details).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
