package com.pisoft.pisoft.advise;

import com.pisoft.pisoft.exception.ResourceNotFound;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Api Error code before global Response handler :

//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound e) {
//
//        ApiError apiError = ApiError.builder()
//
//                .localDateTime(LocalDateTime.now())
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .message(e.getMessage())
//                .build();
//
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(MethodArgumentNotValidException e) {
//
//
//        List<String> error  = e.getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(o -> o.getDefaultMessage())
//                .toList();
//
//        ApiError apiError = ApiError.builder()
//
//                .localDateTime(LocalDateTime.now())
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .message("Invalid Input Exception")
//                .errors(error)
//                .build();
//
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }


    // after global response handler code:

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFound e) {

        ApiError apiError = ApiError.builder()

                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("not found")
                .errors(List.of(e.getMessage()))
                .build();

        return BuildResponseHanlder(apiError);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> authenticationException(AuthenticationException e) {

        ApiError apiError = ApiError.builder()

                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("UnAuthorized")
                .errors(List.of(e.getMessage()))
                .build();

        return BuildResponseHanlder(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> authenticationException(JwtException e) {

        ApiError apiError = ApiError.builder()

                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Jwt Exception")
                .errors(List.of(e.getMessage()))
                .build();

        return BuildResponseHanlder(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> accessDeniedException(AccessDeniedException e) {

        ApiError apiError = ApiError.builder()

                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Jwt Exception")
                .errors(List.of(e.getMessage()))
                .build();

        return BuildResponseHanlder(apiError);
    }

    private ResponseEntity<ApiResponse<?>> BuildResponseHanlder(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(MethodArgumentNotValidException e) {


        List<String> error  = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(o -> o.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()

                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Invalid Input Exception")
                .errors(error)
                .build();

        return BuildResponseHanlder(apiError);
    }
}
