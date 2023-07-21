package com.zaka.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zaka.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *
 */
@ControllerAdvice
@Component
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public Response errorLoginResponse(final BadCredentialsException exception) {
        log.error(String.format("Error occurred --> : %s", exception.getMessage()), exception);
        return Response.error(HttpStatus.UNAUTHORIZED.value(), "Login failed.");
    }

    /**
     * @param exception
     * @return
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public Response errorResponse(final Exception exception) {
        log.error(String.format("Error occurred --> : %s", exception.getMessage()), exception);
        return Response.error(exception.getMessage(), null);
    }


}
