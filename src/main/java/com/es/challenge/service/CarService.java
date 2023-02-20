package com.es.challenge.service;


import com.es.challenge.domain.Car;

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
    Car selectById(long id) throws Exception;

    /**
     * @param minCarId
     * @param maxCarId
     * @return
     * @throws Exception
     */
    List<Car> batchSelectById(long minCarId, long maxCarId) throws Exception;

    /**
     * @param car
     * @return
     * @throws Exception
     */
    int insertSelective(Car car) throws Exception;

    /**
     * @param car
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(Car car) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(long id) throws Exception;

    /**
     * @param minId
     * @param maxId
     * @return
     * @throws Exception
     */
    int batchDeleteById(long minId, long maxId) throws Exception;

}
