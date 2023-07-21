package com.zaka.config;

import com.zaka.response.Response;
import lombok.extern.slf4j.Slf4j;
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
    /**
     * @param exception
     * @return
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public Response errorResponse(final Exception exception) {
        log.error("ERROR OCCURRED !");
        return Response.error(exception.getMessage(), exception);
    }
}
