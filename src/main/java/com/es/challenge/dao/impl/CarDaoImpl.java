package com.es.challenge.dao.impl;

import com.es.challenge.dao.CarDao;
import com.es.challenge.entity.CarEntity;
import com.es.challenge.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Component
public class CarDaoImpl implements CarDao {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarEntity> getCars() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarEntity> getCars(Long carMinId, Long carMaxId) {
        return this.carRepository.searchCarsByRange(carMinId, carMaxId);
    }

    @Override
    public CarEntity getCarById(Long carId) {
        return this.carRepository.getCarById(carId);
    }

    @Override
    public Boolean addCar(CarEntity car) {
        this.carRepository.save(car);
        return true;
    }

    @Override
    public Boolean removeCar(Long carId) {
        if (this.carRepository.exists(carId)) {
            this.carRepository.delete(carId);
            return true;
        } else {
            return false;
        }
    }
}
