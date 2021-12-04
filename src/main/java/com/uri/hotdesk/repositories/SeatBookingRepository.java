package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.EmployeeTeam;
import com.uri.hotdesk.entities.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long>{

	@Query(value = "SELECT * from seat_bookings p where p.seat_seat_id= :seatId and p.start_date<= :bookingEndDate and p.end_date>= :bookingStartDate ", nativeQuery = true)
	List<SeatBooking> findSeatOverLap(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate,@Param("seatId") int seatId);

	@Query(value = "SELECT * from seat_bookings p where p.employee_team_id= :clusterId and p.start_date<= :currentDate and p.end_date>= :currentDate ", nativeQuery = true)
	List<SeatBooking> findTodatBooking(@Param("currentDate") Date currentDate,@Param("clusterId") Long clusterId);
}
