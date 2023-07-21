package com.zaka.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    /**
     *
     */
    private Integer code = 200;

    /**
     *
     */
    private String message;

    /**
     *
     */
    private Object data;

    /**
     * @return
     */
    public static Response ok() {
        return Response.builder().code(HttpStatus.OK.value()).message("success").build();
    }

    /**
     * @param msg
     * @return
     */
    public static Response ok(final String msg) {
        return Response.builder().code(HttpStatus.OK.value()).message(msg).build();
    }

    /**
     * @param msg
     * @param data
     * @return
     */
    public static Response ok(final String msg, final Object data) {
        return Response.builder().code(HttpStatus.OK.value()).message(msg).data(data).build();
    }

    /**
     * @param msg
     * @param data
     * @return
     */
    public static Response error(final String msg, final Object data) {
        return Response.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(msg).data(data).build();
    }

    public static Response error(int code , final String msg) {
        return Response.builder().code(code).message(msg).build();
    }

    public static Response error(final String msg) {
        return Response.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(msg).build();
    }
}
