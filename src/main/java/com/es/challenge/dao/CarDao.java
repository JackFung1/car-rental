package com.es.challenge.dao;

import com.es.challenge.domain.CarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fjt
 * @date 2023-02-16
 */
@Repository
public interface CarDao {
    List<CarEntity> getCars();

    List<CarEntity> getCars(Long carMinId, Long carMaxId);

    CarEntity getCarById(Long carId);

    Boolean addCar(CarEntity car);

    Boolean removeCar(Long carId);
}
