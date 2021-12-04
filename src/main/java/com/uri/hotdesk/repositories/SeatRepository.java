package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Location;
import com.uri.hotdesk.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

	@Query("SELECT DISTINCT a.location FROM Seat a")
    List<String> getAllUniqueLocationName();

    List<Seat> findAllByLocation(Location location);

    Seat findByLocationAndSeatNo(Location location,String seatNo);
}
