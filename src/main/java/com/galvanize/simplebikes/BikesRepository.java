package com.galvanize.simplebikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikesRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByModelContains(String model);

}
