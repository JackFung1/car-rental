package com.es.challenge.repository;

import com.es.challenge.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author fjt
 * @date 2023-02-16
 */
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    /**
     * To fetch Car by Range
     *
     * @param min
     * @param max
     * @return
     */
    @Query("SELECT car FROM CarEntity car WHERE car.id BETWEEN :min AND :max")
    List<CarEntity> searchCarsByRange(@Param("min") long min, @Param("max") long max);

    /**
     * To fetch data by carId
     *
     * @param carId
     * @return
     */
    @Query(value = "SELECT car"
            + " FROM CarEntity car "
            + " WHERE car.id = :carId")
    CarEntity getCarById(@Param("carId") long carId);

}
