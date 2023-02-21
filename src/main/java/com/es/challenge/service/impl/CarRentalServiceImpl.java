package com.es.challenge.service.impl;

import com.es.challenge.dao.CarDao;
import com.es.challenge.entity.CarDTO;
import com.es.challenge.entity.CarEntity;
import com.es.challenge.service.CarRentalService;
import com.es.challenge.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Component
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public Boolean addCar(CarDTO carDTO) {
        return this.carDao.addCar(this.commonUtils.map(carDTO, CarEntity.class));
    }

    @Override
    public Boolean removeCar(Long carId) {
        return this.carDao.removeCar(carId);
    }

    @Override
    public List<CarDTO> getCars() {
        List<CarEntity> cars = this.carDao.getCars();

        return commonUtils.mapList(cars, CarDTO.class);
    }

    @Override
    public List<CarDTO> getCars(Long carMinId, Long carMaxId) {
        List<CarEntity> cars = this.carDao.getCars(carMinId, carMaxId);

        return commonUtils.mapList(cars, CarDTO.class);
    }

    @Override
    public CarDTO getCarById(Long carId) {
        CarEntity car = this.carDao.getCarById(carId);

        return commonUtils.map(car, CarDTO.class);
    }

}
