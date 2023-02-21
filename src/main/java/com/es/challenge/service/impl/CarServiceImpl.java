package com.es.challenge.service.impl;


import com.es.challenge.controller.request.UpdateCarReq;
import com.es.challenge.entity.Car;
import com.es.challenge.entity.MsgResponse;
import com.es.challenge.mapper.CarMapper;
import com.es.challenge.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiaour.github.com on 2017/11/8.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CarServiceImpl implements CarService {

    @Resource
    private CarMapper carMapper;

    @Override
    public Car selectById(long id) {
        return carMapper.selectById(id);
    }

    @Override
    public List<Car> batchSelectById(long minCarId, long maxCarId) {
        return carMapper.batchSelectById(minCarId, maxCarId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long insertSelective(Car car) {
        return carMapper.insertSelective(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateByPrimaryKey(UpdateCarReq updateCarReq) {
        Car carResult = carMapper.selectById(updateCarReq.getId());
        if (carResult == null) {
            log.info("car id" + updateCarReq.getId() + " not exist");
            return -1;
        }

        carResult.setCarModel(updateCarReq.getCarModel());
        carResult.setCarStock(updateCarReq.getCarStock());
        return carMapper.updateByPrimaryKey(carResult);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(long id) {
        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int batchDeleteById(long minId, long maxId) {
        return carMapper.batchDeleteById(minId, maxId);
    }

}
