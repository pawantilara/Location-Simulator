package com.location.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationSimulatorRequestModel {
  LatLngsModel origin;
  LatLngsModel destination;
}
