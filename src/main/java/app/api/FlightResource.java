package app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import app.model.Flight;
import app.service.FlightService;

import java.util.List;

@RestController
public class FlightResource {

    private FlightService flightService;

    @Autowired
    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flight/search/{searchCase}")
    public ResponseEntity<List<Flight>> flightSearch(@PathVariable("searchCase") String searchCase) {
        List<Flight> flights;
        switch (searchCase) {
            case "a":
                flights = flightService.findByCaseA();
                break;
            case "b":
                flights = flightService.findByCaseB();
                break;
            case "c":
                flights = flightService.findByCaseC();
                break;
            default:
                flights = flightService.findByCaseA();
                break;
        }
        return ResponseEntity.ok(flights);
    }
}
