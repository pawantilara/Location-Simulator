package com.location.simulator.controller;

import com.location.simulator.model.LatLngsModel;
import com.location.simulator.model.LocationSimulatorRequestModel;
import com.location.simulator.service.LocationSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LocationSimulatorController {
  @Autowired
  LocationSimulatorService locationSimulatorService;
  @PostMapping("/location")
  public ArrayList<LatLngsModel>  getLocationDetails(@RequestBody LocationSimulatorRequestModel locationSimulatorRequestModel){
    return locationSimulatorService.getLocationDetails(locationSimulatorRequestModel);
  }
}
