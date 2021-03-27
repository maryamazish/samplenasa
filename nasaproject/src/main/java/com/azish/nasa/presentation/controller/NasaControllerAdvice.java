package com.azish.nasa.presentation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class NasaControllerAdvice {

    private final MessageSource messageSource;

    public NasaControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception.getMessage());

        BusinessExceptionModel exceptionModel = new BusinessExceptionModel(exception.getMessage(), null, this.getStackTraceString(exception));
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            exceptionModel.setMessage(messageSource.getMessage(businessException.getMessage(), new Object[0], LocaleContextHolder.getLocale()));
            exceptionModel.setDescription(businessException.getDescription());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionModel);
    }

    /**
     * یک اکسپشن را میگیرد و string آن را برمیگرداند
     *
     * @param exception
     * @return
     */
    static String getStackTraceString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
