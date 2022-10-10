package com.lukodev.evorapaint.core.exceptionHandling;

import com.lukodev.evorapaint.core.exceptionHandling.models.ErrorModel;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorModel errorModel) {
        return new ResponseEntity<>(errorModel, errorModel.getHttpStatus());
    }

    private ErrorModel buildSqlExceptionModel(ConstraintViolationException ex){
        switch (ex.getSQLState()){
            case "23503":
                return new ErrorModel(HttpStatus.BAD_REQUEST, "Bağımlılık hatası",
                    new String[]{"Silmeye çalıştığınız nesneye bağlı nesneler bulunmakta, Lütfen öncelikle bağlı nesneleri siliniz."});
            case "23505":
                return new ErrorModel(HttpStatus.BAD_REQUEST, "Tekrar eden kayıt hatası",
                        new String[]{ex.getSQLException().getMessage()});
            default:
                return new ErrorModel(HttpStatus.BAD_REQUEST, "Hata", new String[]{"İşlem yaparken bi hata oluştu lütfen sistem yetkilisiyle iletişime geçiniz."});
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String[] validationErrorMessages = ex.getAllErrors().stream().map(e -> e.getDefaultMessage()).toArray(String[]::new);
        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST, "Doğrulama hatası", validationErrorMessages);
        return this.buildResponseEntity(errorModel);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    protected ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        return this.buildResponseEntity(buildSqlExceptionModel(ex));
    }




}
