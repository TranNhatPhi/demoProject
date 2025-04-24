package com.r2sCompany.exampleProject.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse HandleValidationException(Exception ex, WebRequest request) {
        System.out.println("=>>>>>>>>>>>> handleValidationException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));
        String message = ex.getMessage();
        if(ex instanceof MethodArgumentNotValidException) {
            int start = message.lastIndexOf("[");
            int end = message.lastIndexOf("]");
            message = message.substring(start + 1, end - 1);
            errorResponse.setError("Payload invalid");
        }
        else if(ex instanceof ConstraintViolationException) {
            message = message.substring(message.indexOf(" ")+1 );
            errorResponse.setError("PathVariable Invalid");
        }


        errorResponse.setMessage(message);

        return errorResponse;

    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(Exception ex, WebRequest request) {
        System.out.println("=>>>>>>>>>>>> INTERNAL_SERVER_ERROR");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));
        errorResponse.setError(INTERNAL_SERVER_ERROR.getReasonPhrase());
        if(ex instanceof MethodArgumentTypeMismatchException) {
            errorResponse.setMessage("failed to convert value of type " );
        }



        return errorResponse;

    }
}
