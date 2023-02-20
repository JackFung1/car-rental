package com.es.challenge.controller.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author fjt
 * @date
 */
@Data
@ToString
public class UpdateCarReq {

    private Long id;

    private String carModel;

    private Integer carStock;
}
