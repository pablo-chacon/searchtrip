package com.example.searchtrip.repository;


import com.example.searchtrip.model.FindTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTripRepository extends JpaRepository<FindTrip, Long> {
    // SELECT * FROM course WHERE name=?
    FindTrip findTripByName(String name);

    //SELECT * FROM course LIKE name=%name%;
    List<FindTrip> findAllByCity(String city);

    //SELECT * FROM course LIKE name=%query1% OR description=%query2%;
    List<FindTrip> findCoursesByOrigin(String originLat, String originLon);

    List<FindTrip> findCoursesByDest(String destLat, String destLon);


}