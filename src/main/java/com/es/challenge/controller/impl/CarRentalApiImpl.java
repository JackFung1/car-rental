package com.es.challenge.controller.impl;

import com.es.challenge.domain.CarDTO;
import com.es.challenge.controller.CarRentalApi;
import com.es.challenge.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Component
public class CarRentalApiImpl implements CarRentalApi {

    @Autowired
    private CarRentalService carRentalService;

    @Override
    public ResponseEntity getCars() {
        List<CarDTO> cars = this.carRentalService.getCars();
        return new ResponseEntity(cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getCars(@PathVariable("carMinId") Long carMinId, @PathVariable("carMaxId") Long carMaxId) {
        List<CarDTO> cars = this.carRentalService.getCars(carMinId, carMaxId);
        return new ResponseEntity(cars, !cars.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getCarById(@PathVariable("carId") Long carId) {
        CarDTO car = this.carRentalService.getCarById(carId);
        return new ResponseEntity(car, !StringUtils.isEmpty(car) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity addCar(@RequestBody CarDTO carDTO) {
        Boolean result = this.carRentalService.addCar(carDTO);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity removeCar(@PathVariable("id") Long carId) {
        Boolean result = this.carRentalService.removeCar(carId);
        return new ResponseEntity(result, (result) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
