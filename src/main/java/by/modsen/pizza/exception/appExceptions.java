package by.modsen.pizza.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class appExceptions {
    @ExceptionHandler({ BindException.class, MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class, MissingServletRequestPartException.class,
            HttpMessageNotReadableException.class, TypeMismatchException.class})
    public ResponseEntity<Object> BadRequestException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Invalid Request", new HttpHeaders(), HttpStatus.BAD_REQUEST);//400
    }
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> AuthenticationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Wrong Users Data", new HttpHeaders(), HttpStatus.UNAUTHORIZED);//401
    }
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access Denied", new HttpHeaders(), HttpStatus.FORBIDDEN);//403
    }
    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Page Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);//404
    }
    @ExceptionHandler({ DataAccessException.class,ConversionNotSupportedException.class,
            RuntimeException.class})
    public ResponseEntity<Object> handleServerErrorException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Server Side Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
}
