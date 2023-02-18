package com.es.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message = "Success";
}
