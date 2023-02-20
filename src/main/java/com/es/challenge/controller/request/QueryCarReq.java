package com.es.challenge.controller.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author fjt
 * @date
 */
@Data
@ToString
public class QueryCarReq {

    private Long id;

    private String carModel;

    private Long minId;

    private Long maxId;

}
