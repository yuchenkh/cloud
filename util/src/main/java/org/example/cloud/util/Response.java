package org.example.cloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuchen
 * Aug 19, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    public Response(Integer code, String message) {
        this(code, message, null);
    }
}
