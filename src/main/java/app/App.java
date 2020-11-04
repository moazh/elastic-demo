package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import app.model.Flight;
import app.service.FlightService;

import java.io.FileReader;
import java.util.Iterator;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private FlightService flightService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        addFlights();
    }

    private void addFlights() {
        try {
            elasticsearchTemplate.deleteIndex(Flight.class);
            elasticsearchTemplate.createIndex(Flight.class);
            elasticsearchTemplate.putMapping(Flight.class);
            elasticsearchTemplate.refresh(Flight.class);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("flights.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray flights = (JSONArray) jsonObject.get("flights");
            Iterator<JSONObject> iterator = flights.iterator();
            ObjectMapper objectMapper = new ObjectMapper();
            Long i = 1L;
            while (iterator.hasNext()) {
                Flight flight = objectMapper.readValue(iterator.next().toJSONString(), Flight.class);
                flight.setId(i);
                flightService.addFlight(flight);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
