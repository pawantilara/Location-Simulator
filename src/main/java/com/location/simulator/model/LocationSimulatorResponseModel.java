package com.location.simulator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationSimulatorResponseModel {
  List<LatLngsModel> latLngsModelList;
}
