package com.es.challenge.mapper;


import com.es.challenge.domain.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
public interface CarMapper {

    /**
     * @param id
     * @return
     */
    Car selectById(@Param("id") long id);

    /**
     * @param minCarId
     * @param maxCarId
     * @return
     */
    List<Car> batchSelectById(@Param("minCarId") long minCarId, @Param("maxCarId") long maxCarId);

    /**
     * @param car
     * @return
     */
    int insertSelective(Car car);

    /**
     * @param car
     * @return
     */
    int updateByPrimaryKey(Car car);

    /**
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") long id);

    /**
     * @param minId
     * @param maxId
     * @return
     */
    int batchDeleteById(@Param("minId") long minId, @Param("maxId") long maxId);


}