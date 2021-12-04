package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, String>{

    @Query(value = "SELECT Count(*) FROM seats s where s.location_location_id= :locationId",nativeQuery = true)
    int getSumOfSeatsinLocation(@Param("locationId") String locationId);
}
