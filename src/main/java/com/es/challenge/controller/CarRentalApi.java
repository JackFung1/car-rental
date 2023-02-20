package com.es.challenge.controller;

import com.es.challenge.entity.CarDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author fjt
 * @date 2023-02-16
 */
//@RestController
//@Api(value = "car rental接口", tags = {"car rental 接口"})
public interface CarRentalApi {
    @GetMapping(value = "/public/getCars", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Return all Cars with details", notes = "This is a public API", response = List.class)
//    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
    ResponseEntity getCars();

    @GetMapping(value = "/public/getCars/{carMinId}/{carMaxId}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Return all Cars with details by car id range", notes = "This is a public API", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
//            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carIds range")
//    })
    ResponseEntity getCars(@PathVariable("carMinId") Long carMinId, @PathVariable("carMaxId") Long carMaxId);


    @GetMapping(value = "/public/getCarById/{carId}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Return the Car with detail by car id", notes = "This is a public API", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
//            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any car by carId")
//    })
    ResponseEntity getCarById(@PathVariable("carId") Long carId);

    @PutMapping(value = "/admin/addCar",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Add a new Car with details", notes = "This is a public API with admin right", response = Response.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new car has been added successfully"),
//            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key")
//    })
    ResponseEntity addCar(@RequestBody CarDTO carDTO);

    @DeleteMapping(value = "/admin/removeCar/{id}")
//    @ApiOperation(value = "Remove an existing Car", notes = "This is a public API with admin right", response = Response.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The car has been removed successfully"),
//            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
//            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Car does not exist")
//    })
    ResponseEntity removeCar(@PathVariable("id") Long carId);
}
