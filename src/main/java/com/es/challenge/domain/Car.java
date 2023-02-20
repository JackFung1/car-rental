package com.es.challenge.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fjt
 * @date 2023-02-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("创建car响应结果")
public class Car implements Serializable {

    private static final long serialVersionUID = -4087970586697604490L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("car model")
    private String carModel;

    @ApiModelProperty("car stock")
    private Integer carStock;

    @ApiModelProperty("create time")
    private Long createTime;

    private Long updateTime;

    public Car(Long id, String carModel, Integer carStock) {
        this.id = id;
        this.carModel = carModel;
        this.carStock = carStock;
    }
}