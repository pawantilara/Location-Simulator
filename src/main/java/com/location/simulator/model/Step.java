
package com.location.simulator.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Step {
    private Distance distance;
    
    private Duration duration;
      @JsonProperty("end_location")
    private EndLocation endLocation;
      @JsonProperty("html_instructions")
    private String htmlInstructions;
    
    private Polyline polyline;
      @JsonProperty("start_location")
    private StartLocation startLocation;
      @JsonProperty("travel_mode")
    private String travelMode;

  
}
