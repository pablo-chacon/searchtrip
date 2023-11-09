package com.example.searchtrip.repository;


import com.example.searchtrip.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {



    List<Location> findByOrigin(String origin);
    List<Location> findByDestination (String destination);
    List<Location> findByFavorite(boolean b);
    List<Location> findByOriginDestination(String origin, String destination);


}

