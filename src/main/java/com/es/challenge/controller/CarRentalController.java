package com.es.challenge.controller;


import com.alibaba.fastjson.JSON;
import com.es.challenge.controller.request.CreateCarReq;
import com.es.challenge.controller.request.DeleteCarReq;
import com.es.challenge.controller.request.QueryCarReq;
import com.es.challenge.controller.request.UpdateCarReq;
import com.es.challenge.controller.vo.CarVO;
import com.es.challenge.entity.Car;
import com.es.challenge.entity.MsgResponse;
import com.es.challenge.entity.Response;
import com.es.challenge.service.CarService;
import com.es.challenge.util.AppAssert;
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
public class CarRentalController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/index")
    public MsgResponse<String> index() {
        return new MsgResponse<>("hello world");
    }

    @GetMapping(value = "/car/test/{id}")
    public MsgResponse<String> getTest(@PathVariable("id") long id) {
        log.info("get test: " + id);
        return new MsgResponse<>("OK");
    }

    /**
     * 获取数据库中的car info
     *
     * @param req
     * @return
     */
    @PostMapping("/car/getCar")
    @ApiOperation(value = "Return the Car with detail by car id", notes = "This is a public API", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"), @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carId")})
    public MsgResponse<CarVO> getCar(@RequestBody QueryCarReq req) {
        AppAssert.notNull(req.getId(), "car id不能为空");
        AppAssert.greaterOrEqualLong(req.getId(), 0L, "car id should >=0");
        log.info("get id: " + req.getId());
        Car car = carService.selectById(req.getId());
        log.info("carInfo: " + JSON.toJSONString(car));
        return new MsgResponse<>(convert2VOItem(car));
    }

    /**
     * @param req
     * @return
     */
    @PostMapping("/car/getCars/{minCarId}/{maxCarId}")
    @ApiOperation(value = "Return all Cars with details by car id range", notes = "This is a public API", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"), @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carIds range")})
    public MsgResponse<List<CarVO>> getCarsByRange(@RequestBody QueryCarReq req) {
        AppAssert.notNull(req.getMinId(), "car minId不能为空");
        AppAssert.notNull(req.getMaxId(), "car maxId不能为空");
        AppAssert.greaterOrEqualLong(req.getMinId(), 0L, "car id should >=0");
        AppAssert.greaterOrEqualLong(req.getMaxId(), req.getMinId(), "car should maxId>=carId");
        log.info("getCarsByRange req:" + JSON.toJSONString(req));
        List<Car> cars = carService.batchSelectById(req.getMinId(), req.getMaxId());
        log.info("getCarsByRange carInfos:" + JSON.toJSONString(cars));
        return new MsgResponse<>(batchConvert2VO(cars));
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
     * @param createCarReq
     * @return
     */
    @PostMapping("/car/insert")
    @ApiOperation(value = "Add a new Car with details", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "A new car has been added successfully"),})
    public MsgResponse<Car> createCar(@RequestBody CreateCarReq createCarReq) {
        AppAssert.notNull(createCarReq.getCarModel(), "car model不能为空");
        AppAssert.notNull(createCarReq.getCarStock(), "car stock不能为空");
        log.info("createUser createCarReq:" + JSON.toJSONString(createCarReq));
        Car car = new Car();
        car.setCarModel(createCarReq.getCarModel());
        car.setCarStock(createCarReq.getCarStock());
        long insertNum = carService.insertSelective(car);
        car.setId(car.getId());
        log.info("car:" + JSON.toJSONString(car) + " insertNum:" + insertNum);
        return new MsgResponse<>(car);
    }

    /**
     * @param updateCarReq
     * @return
     */
    @PostMapping("/car/update")
    @ApiOperation(value = "update an existing Car", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "The car has been updated successfully"),})
    public MsgResponse<Car> update(@RequestBody UpdateCarReq updateCarReq) {
        AppAssert.notNull(updateCarReq.getId(), "car id不能为空");
        AppAssert.greaterOrEqualLong(updateCarReq.getId(), 0L, "car id should >=0");
        AppAssert.notNull(updateCarReq.getCarModel(), "car model不能为空");
        AppAssert.notNull(updateCarReq.getCarStock(), "car stock不能为空");
        log.info("update updateCarReq:" + JSON.toJSONString(updateCarReq));
        Car carResult = carService.selectById(updateCarReq.getId());
        if (carResult == null) {
            log.info("error: not exist");
            return new MsgResponse<>(null);
        }

        carResult.setCarModel(updateCarReq.getCarModel());
        carResult.setCarStock(updateCarReq.getCarStock());
        int updateRows = carService.updateByPrimaryKey(carResult);
        log.info("updateRows:" + updateRows + " carResult:" + JSON.toJSONString(carResult));
        return new MsgResponse<>(carResult);
    }

    /**
     * @param deleteCarReq
     * @return
     */
    @DeleteMapping("/car/delete/{id}")
    @ApiOperation(value = "Remove an existing Car", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "The car has been removed successfully"),})
    public MsgResponse<Integer> deleteById(@RequestBody DeleteCarReq deleteCarReq) {
        AppAssert.notNull(deleteCarReq.getId(), "car id不能为空");
        AppAssert.greaterOrEqualLong(deleteCarReq.getId(), 0L, "car id should >=0");
        log.info("deleteById id: " + deleteCarReq.getId());
        int deleteRows = carService.deleteByPrimaryKey(deleteCarReq.getId());
        log.info("deleteById deleteRows:" + deleteRows);
        return new MsgResponse<>(deleteRows);
    }

    /**
     * @param deleteCarReq
     * @return
     */
    @PostMapping("/car/batchDelete/{minId}/{maxId}")
    @ApiOperation(value = "Remove existing Cars", notes = "This is a public API with admin right", response = Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpServletResponse.SC_OK, message = "The cars have been removed successfully"),})
    public MsgResponse<Integer> batchDeleteById(@RequestBody DeleteCarReq deleteCarReq) {
        AppAssert.notNull(deleteCarReq.getMinId(), "car minId不能为空");
        AppAssert.notNull(deleteCarReq.getMaxId(), "car maxId不能为空");
        AppAssert.greaterOrEqualLong(deleteCarReq.getMaxId(), deleteCarReq.getMinId(), "car should maxId>=carId");
        log.info("batchDelete minId:" + deleteCarReq.getMinId() + " maxId:" + deleteCarReq.getMaxId());
        int deleteRows = carService.batchDeleteById(deleteCarReq.getMinId(), deleteCarReq.getMaxId());
        log.info("batchDelete deleteRows:" + deleteRows);
        return new MsgResponse<>(deleteRows);
    }
}
