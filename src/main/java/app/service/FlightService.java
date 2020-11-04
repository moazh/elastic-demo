package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.model.Flight;
import app.repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findByCaseA() {
        return flightRepository.findByCarrier();
    }

    public List<Flight> findByCaseB() {
        return null; // TODO return repo.findBy
    }

    public List<Flight> findByCaseC() {
        return null; // TODO return repo.findBy
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
