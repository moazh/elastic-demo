package app.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import app.model.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends ElasticsearchRepository<Flight, Long> {

    List<Flight> findByCarrier(); //TODO case a

//    List<Flight> findByCaseB(); //TODO case b
//
//    List<Flight> findByCaseC(); //TODO case c
}
