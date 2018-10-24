/*
 * All GTAS code is Copyright 2016, The Department of Homeland Security (DHS), U.S. Customs and Border Protection (CBP).
 * 
 * Please see LICENSE.txt for details.
 */
package gov.gtas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.model.Disposition;
import gov.gtas.model.Passenger;

import static gov.gtas.querybuilder.constants.Constants.SELECT;

public interface PassengerRepository extends PagingAndSortingRepository<Passenger, Long>, PassengerRepositoryCustom {
    
	@Query("SELECT p FROM Passenger p WHERE p.id = :id")
	public Passenger getPassengerById(@Param("id") Long id);
	
    @Query("SELECT p FROM Passenger p WHERE UPPER(p.firstName) = UPPER(:firstName) AND UPPER(p.lastName) = UPPER(:lastName)")
    public List<Passenger> getPassengerByName(@Param("firstName") String firstName,@Param("lastName") String lastName);
    
    @Query("SELECT p FROM Passenger p WHERE UPPER(p.lastName) = UPPER(:lastName)")
    public List<Passenger> getPassengersByLastName(@Param("lastName") String lastName);

  /*  @Query("SELECT p FROM Flight f join f.passengers p where f.id = (:flightId)")
    public List<Passenger> getPassengersByFlightId(@Param("flightId") Long flightId);*/
    
    /*@Query(nativeQuery = true, 
    		value="SELECT p.* FROM flight_passenger fp join passenger p ON (fp.passenger_id = p.id) where fp.flight_id = (:flightId)")
    public List<Passenger> getPassengersByFlightId(@Param("flightId") Long flightId);*/
    
/*    @Query("SELECT p FROM Flight f join f.passengers p where f.id = (:flightId) AND UPPER(p.firstName) = UPPER(:firstName) AND UPPER(p.lastName) = UPPER(:lastName)")
    public List<Passenger> getPassengersByFlightIdAndName(@Param("flightId") Long flightId, @Param("firstName") String firstName,@Param("lastName") String lastName);*/

   /* @Query(nativeQuery = true, 
    		value="SELECT p.*, ptag.* FROM flight_passenger fp join passenger p ON (fp.passenger_id = p.id) join pax_idtag ptag ON (ptag.pax_id = p.id) "
    				+ "where fp.flight_id = (:flightId) AND UPPER(p.first_name) = UPPER(:firstName) AND UPPER(p.last_name) = UPPER(:lastName)")
    public List<Passenger> getPassengersByFlightIdAndName(@Param("flightId") Long flightId, @Param("firstName") String firstName,@Param("lastName") String lastName);*/
    
    @Query("SELECT d FROM Disposition d where d.passenger.id = (:passengerId) AND d.flight.id = (:flightId)")
    public List<Disposition> getPassengerDispositionHistory(@Param("passengerId") Long passengerId, @Param("flightId") Long flightId);
    
	@Modifying
	@Transactional
    @Query("UPDATE Passenger set watchlistCheckTimestamp =:lastTimestamp WHERE id=:passengerId")
    public void setPassengerWatchlistTimestamp(@Param("passengerId") Long passengerId, @Param("lastTimestamp") Date lastTimestamp);

//	@Query("SELECT p FROM Passenger p WHERE UPPER(p.firstName) = UPPER(:firstName) " +
//            "AND UPPER(p.lastName) = UPPER(:lastName)" +
//            "AND UPPER(p.gender) = UPPER(:gender)" +
//            "AND UPPER(p.citizenshipCountry) = UPPER(:ctz_country) " +
//            "AND p.dob = (:dob)" +
//            "AND p.idTag IS NOT NULL")
//	public List<Passenger> getNotNullIdTaggedPassenger(@Param("firstName") String firstName, @Param("lastName") String lastName,
//                                                         @Param("gender") String gender, @Param("ctz_country") String ctz_country,
//                                                         @Param("dob") Date dob);

//	@Query("SELECT p FROM Passenger p WHERE p.idTag = (:idTag)")
//    public List<Passenger> getIdTaggedPassenger(@Param("") String idTag);
//
/*	@Query("SELECT p FROM Passenger p WHERE p.paxIdTag IS NULL")
    public List<Passenger> getNullIdTagPassengers();

    @Query("SELECT p FROM Passenger p WHERE p.paxIdTag IS NOT NULL")
    public List<Passenger> getNotNullIdTagPassengers();*/
}
