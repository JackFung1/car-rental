package com.es.challenge.controller.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author fjt
 * @date
 */
@Data
@ToString
public class CarVO {

    private Long id;

    private String carModel;

    private Integer carStock;

    private Long createTime;

    private Long updateTime;

}
