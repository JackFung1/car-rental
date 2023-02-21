package com.es.challenge.rest;

import com.alibaba.fastjson.JSON;
import com.es.challenge.MainApplication;
import com.es.challenge.controller.CarRentalController;
import com.es.challenge.controller.request.CreateCarReq;
import com.es.challenge.controller.request.DeleteCarReq;
import com.es.challenge.controller.request.QueryCarReq;
import com.es.challenge.controller.request.UpdateCarReq;
import com.es.challenge.controller.vo.CarVO;
import com.es.challenge.entity.Car;
import com.es.challenge.entity.MsgResponse;
import com.es.challenge.service.CarService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * @author fjt
 * @date 2023-02-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Slf4j
public class CarApiTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Resource
    public CarRentalController carRentalController;

    @Resource
    public CarService carService;

    @Test
    public void testGetCar() {
        QueryCarReq queryCarReq = new QueryCarReq();
        queryCarReq.setId(2L);
        MsgResponse<CarVO> msgResponse = carRentalController.getCar(queryCarReq);
        Assert.assertNotNull(msgResponse);
        Assert.assertNotNull(msgResponse.getData());
        Assert.assertTrue(msgResponse.getData().getId() == 2L);
    }

    @Test
    public void testGetCarsWithRange() {
        QueryCarReq queryCarReq = new QueryCarReq();
        queryCarReq.setMinId(1L);
        queryCarReq.setMaxId(10L);
        MsgResponse<List<CarVO>> msgResponse = carRentalController.getCarsByRange(queryCarReq);
        Assert.assertNotNull(msgResponse);
        Assert.assertNotNull(msgResponse.getData());
        Assert.assertTrue(msgResponse.getData().size() > 0);
    }

    @Test
    public void testGetCarsWithRangeNotFound() {

        QueryCarReq queryCarReq = new QueryCarReq();
        queryCarReq.setMinId(1000L);
        queryCarReq.setMaxId(1010L);
        MsgResponse<List<CarVO>> msgResponse = carRentalController.getCarsByRange(queryCarReq);
        Assert.assertNotNull(msgResponse);
        Assert.assertNotNull(msgResponse.getData());
        Assert.assertTrue(msgResponse.getData().size() == 0);
    }

    @Test
    public void testAddCar() {
        long insertSequence = 0L;

        try {
            CreateCarReq createCarReq = new CreateCarReq();
            createCarReq.setCarModel("Test993");
            createCarReq.setCarStock(50);
            MsgResponse<Car> msgResponse = carRentalController.createCar(createCarReq);
            Assert.assertNotNull(msgResponse);
            Assert.assertNotNull(msgResponse.getData());
            Assert.assertNotNull(msgResponse.getData().getId());
            insertSequence = msgResponse.getData().getId();
        } finally {
            int deleteRows = carService.deleteByPrimaryKey(insertSequence);
            log.info("delete Rows:" + deleteRows + " insertSequence:" + insertSequence);
        }

    }

    @Test
    public void testUpdateCar() {
        long insertSequence = 0L;

        try {
            CreateCarReq createCarReq = new CreateCarReq();
            createCarReq.setCarModel("Test990");
            createCarReq.setCarStock(50);
            MsgResponse<Car> msgResponse = carRentalController.createCar(createCarReq);
            Assert.assertNotNull(msgResponse);
            Assert.assertNotNull(msgResponse.getData());
            Assert.assertNotNull(msgResponse.getData().getId());
            insertSequence = msgResponse.getData().getId();


            UpdateCarReq updateCarReq = new UpdateCarReq();
            updateCarReq.setCarModel("Test991");
            updateCarReq.setCarStock(msgResponse.getData().getCarStock());
            updateCarReq.setId(msgResponse.getData().getId());
            MsgResponse<Integer> updateResp = carRentalController.update(updateCarReq);
            Assert.assertNotNull(updateResp);
            Assert.assertNotNull(updateResp.getData());
            Assert.assertTrue(updateResp.getData().intValue()>0);
        } finally {
            int deleteRows = carService.deleteByPrimaryKey(insertSequence);
            log.info("delete Rows:" + deleteRows + " insertSequence:" + insertSequence);
        }

    }

    @Test
    public void testDeleteCar() {
        long insertSequence = 0L;
        try {
            CreateCarReq createCarReq = new CreateCarReq();
            createCarReq.setCarModel("Test985");
            createCarReq.setCarStock(50);
            MsgResponse<Car> msgResponse = carRentalController.createCar(createCarReq);
            Assert.assertNotNull(msgResponse);
            Assert.assertNotNull(msgResponse.getData());
            Assert.assertNotNull(msgResponse.getData().getId());
            insertSequence = msgResponse.getData().getId();
        } finally {
            DeleteCarReq deleteCarReq = new DeleteCarReq();
            deleteCarReq.setId(insertSequence);
            MsgResponse<Integer> response = carRentalController.deleteById(deleteCarReq);

            Assert.assertNotNull(response);
            Assert.assertNotNull(response.getData());
            Assert.assertTrue(response.getData().intValue() > 0);
            log.info("delete response:" + JSON.toJSONString(response) + " insertSequence:" + insertSequence);
        }
    }

    /**
     * @return
     */
    private Headers populateAdminHeaders() {
        return new Headers(new Header("consumer-key", "admin"));
    }

    private Car createMockCar() {
        return new Car(Long.valueOf(1000), "Porsche911", Integer.valueOf(1000));
    }
}
