package com.es.challenge.service;


import com.es.challenge.entity.Car;

import java.util.List;

/**
 * Created by zhangtao on 2017/11/8.
 */

public interface CarService {

    /**
     * @param id
     * @return
     * @throws Exception
     */
    Car selectById(long id);

    /**
     * @param minCarId
     * @param maxCarId
     * @return
     * @throws Exception
     */
    List<Car> batchSelectById(long minCarId, long maxCarId);

    /**
     * @param car
     * @return
     * @throws Exception
     */
    long insertSelective(Car car);

    /**
     * @param car
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(Car car);

    /**
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(long id);

    /**
     * @param minId
     * @param maxId
     * @return
     * @throws Exception
     */
    int batchDeleteById(long minId, long maxId);

}
