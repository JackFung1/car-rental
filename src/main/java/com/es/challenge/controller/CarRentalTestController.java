package com.es.challenge.controller;


import com.alibaba.fastjson.JSON;
import com.es.challenge.controller.vo.CarVO;
import com.es.challenge.domain.Car;
import com.es.challenge.domain.Response;
import com.es.challenge.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
@RestController
@Api(value = "car rental api接口", tags = {"car rental api接口"})
@Slf4j
public class CarRentalTestController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/index")
    public String index() {
        return "hello world";
    }

    @GetMapping(value = "/car/test/{id}")
    public String getTest(@PathVariable("id") long id) {
        System.out.println("###id:" + id);
        return "OK";
    }

    /**
     * 获取数据库中的car info
     *
     * @param id
     * @return
     */
    @GetMapping("/car/getCar/{id}")
    @ApiOperation(value = "Return the Car with detail by car id", notes = "This is a public API", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carId")
    })
    public CarVO get(@PathVariable("id") long id) {
        try {
            System.out.println("get id:" + id);
            Car car = carService.selectById(id);
            System.out.println("carInfo:" + JSON.toJSONString(car));
            return convert2VOItem(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param minCarId
     * @param maxCarId
     * @return
     */
    @GetMapping("/car/getCars/{minCarId}/{maxCarId}")
    @ApiOperation(value = "Return all Cars with details by car id range", notes = "This is a public API", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carIds range")
    })
    public List<CarVO> getCarsByRange(@PathVariable("minCarId") long minCarId, @PathVariable("maxCarId") long maxCarId) {
        try {
            System.out.println("get minCarId:" + minCarId + " maxCarId:" + maxCarId);
            List<Car> cars = carService.batchSelectById(minCarId, maxCarId);
            System.out.println("carInfos:" + JSON.toJSONString(cars));
            return batchConvert2VO(cars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<CarVO> batchConvert2VO(List<Car> cars) {
        List<CarVO> carVOs = new ArrayList<>();
        if (cars.isEmpty()) {
            return carVOs;
        }
        for (Car car : cars) {
            CarVO carVO = convert2VOItem(car);
            carVOs.add(carVO);
        }
        return carVOs;
    }

    private CarVO convert2VOItem(Car car) {
        if (car == null) {
            return null;
        }
        CarVO carVO = new CarVO();
        carVO.setId(car.getId());
        carVO.setCarModel(car.getCarModel());
        carVO.setCarStock(car.getCarStock());
        carVO.setCreateTime(car.getCreateTime());
        carVO.setUpdateTime(car.getUpdateTime());
        return carVO;
    }

    /**
     * @param car
     * @return
     */
    @PostMapping("/car/insert")
    @ApiOperation(value = "Add a new Car with details", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "A new car has been added successfully"),
    })
    public int createCar(@RequestBody Car car) {
        try {
            System.out.println("createUser carInfo:" + JSON.toJSONString(car));
            int insertRows = carService.insertSelective(car);
            System.out.println("insertRows:" + insertRows);
            return insertRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param car
     * @return
     */
    @PostMapping("/car/update")
    @ApiOperation(value = "update an existing Car", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "The car has been updated successfully"),
    })
    public int update(@RequestBody Car car) {
        try {
            System.out.println("update carInfo:" + JSON.toJSONString(car));
            if (car == null || car.getId() == null) {
                System.out.println("error: car or car id should not be null");
                return -1;
            }
            Car carResult = carService.selectById(car.getId());
            if (carResult == null) {
                System.out.println("error: not exist");
                return -2;
            }

            carResult.setCarModel(car.getCarModel());
            carResult.setCarStock(car.getCarStock());
            int updateRows = carService.updateByPrimaryKey(carResult);
            System.out.println("updateRows:" + updateRows);
            return updateRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/car/delete/{id}")
    @ApiOperation(value = "Remove an existing Car", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "The car has been removed successfully"),
    })
    public int deleteById(@PathVariable("id") long id) {
        try {
            System.out.println("deleteById id:" + id);
            int deleteRows = carService.deleteByPrimaryKey(id);
            System.out.println("deleteById deleteRows:" + deleteRows);
            return deleteRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param minId
     * @param maxId
     * @return
     */
    @DeleteMapping("/car/batchDelete/{minId}/{maxId}")
    @ApiOperation(value = "Remove existing Cars", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "The cars have been removed successfully"),
    })
    public int batchDeleteById(@PathVariable("minId") long minId, @PathVariable("maxId") long maxId) {
        try {
            System.out.println("batchDelete minId:" + minId + " maxId:" + maxId);
            int deleteRows = carService.batchDeleteById(minId, maxId);
            System.out.println("batchDelete deleteRows:" + deleteRows);
            return deleteRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
