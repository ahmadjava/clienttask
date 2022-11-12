package com.capitalnumber.demo.handlers;

import com.capitalnumber.demo.error.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class DemoExceptionHandler {

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleSQLException(HttpServletRequest req, Exception ex) {
        log.error("Could not perform the operation, please look stack trace for details", ex);
        return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
