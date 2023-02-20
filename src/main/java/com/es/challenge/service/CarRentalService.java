package com.es.challenge.service;

import com.es.challenge.entity.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Service
public interface CarRentalService {
    List<CarDTO> getCars();

    List<CarDTO> getCars(Long carMinId, Long carMaxId);

    CarDTO getCarById(Long carId);

    Boolean addCar(CarDTO carDTO);

    Boolean removeCar(Long carId);

}
