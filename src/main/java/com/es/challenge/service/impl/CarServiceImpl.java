package com.es.challenge.service.impl;


import com.es.challenge.domain.Car;
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
    public Car selectById(long id) throws Exception {
        return carMapper.selectById(id);
    }

    @Override
    public List<Car> batchSelectById(long minCarId, long maxCarId) throws Exception{
        return carMapper.batchSelectById(minCarId,maxCarId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertSelective(Car car) throws Exception {
        return carMapper.insertSelective(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateByPrimaryKey(Car car) throws Exception {
        return carMapper.updateByPrimaryKey(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(long id) throws Exception {
        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int batchDeleteById(long minId, long maxId) throws Exception{
        return carMapper.batchDeleteById(minId, maxId);
    }

}
