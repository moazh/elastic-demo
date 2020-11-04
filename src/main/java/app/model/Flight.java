package app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "flight-data", type = "flight", shards = 1, replicas = 0)
public class Flight {
    @Id
    private Long id;
    private String timestamp;
    private String Carrier;
    private String OriginCityName;
    private String OriginCountry;
    private String DestCityName;
    private String DestCountry;
    private String FlightTimeMin;
    private String AvgTicketPrice;
    private Boolean Cancelled;
    private String FlightDelayType;
}
