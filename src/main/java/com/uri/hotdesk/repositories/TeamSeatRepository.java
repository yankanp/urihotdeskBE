package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Team;
import com.uri.hotdesk.entities.TeamSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TeamSeatRepository extends JpaRepository<TeamSeats, Long>{

	@Query(value = "SELECT * from team_seats p where p.seat_id= :seatId and p.start_date<= :bookingEndDate and p.end_date>= :bookingStartDate ", nativeQuery = true)
	List<TeamSeats> findSeatOverLap(@Param("bookingStartDate") Date bookingStartDate, 
			@Param("bookingEndDate") Date bookingEndDate,@Param("seatId") int seatId);
	
	@Query(value = "SELECT * from team_seats p where p.team_team_id= :team_id and p.start_date<= :currentDate and p.end_date>= :currentDate ", nativeQuery = true)
	List<TeamSeats> findTodatBooking(@Param("currentDate") Date currentDate,@Param("team_id") String team_id);

	@Query(value = "SELECT * from team_seats p where p.seat_seat_id= :seatId and p.team_team_id= :teamId and p.start_date<= :bookingStartDate and p.end_date>= :bookingEndDate ", nativeQuery = true)
	List<TeamSeats> findSeatAllocated(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate,@Param("seatId") int seatId,@Param("teamId") String teamId);

}
