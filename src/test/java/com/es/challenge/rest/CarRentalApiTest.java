package com.es.challenge.rest;

import com.es.challenge.entity.CarDTO;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Before;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;


/**
 * @author fjt
 * @date 2023-02-16
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CarRentalApiTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

//    @Test
    public void testGetCars() {
        Response response = RestAssured.when().get("/carRental/public/getCars");

        assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
    }

//    @Test
    public void testGetCarsWithRange() {
        Response response = RestAssured.when().get("/carRental/public/getCars/1/3");

        assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
    }

//    @Test
    public void testGetCarsWithRangeNotFound() {
        Response response = RestAssured.when().get("/carRental/public/getCars/1000/2000");

        assertEquals("404 must be returned", HttpStatus.NOT_FOUND.value(), response.statusCode());
    }

//    @Test
    public void testAddCar() {
        Headers headers = populateAdminHeaders();
        Response response = RestAssured.given()
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(this.createMockCar())
                .when().post("/carRental/admin/addCar");

        assertEquals("200 must be returned", HttpStatus.CREATED.value(), response.statusCode());
    }

//    @Test
    public void testAddCarWithoutConsumerKey() {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(this.createMockCar())
                .when().post("/carRental/admin/addCar");

        assertEquals("200 must be returned", HttpStatus.UNAUTHORIZED.value(), response.statusCode());
    }

    /**
     * @return
     */
    private Headers populateAdminHeaders() {
        return new Headers(new Header("consumer-key", "admin"));
    }

    private CarDTO createMockCar() {
        return new CarDTO(Long.valueOf(1000), "Porsche911", Long.valueOf(1000));
    }
}
