package com.location.simulator.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.location.simulator.constants.ApiConstants;
import com.location.simulator.model.GeoLocationResponse;
import com.location.simulator.model.LatLngsModel;
import com.location.simulator.model.LocationSimulatorRequestModel;
import com.location.simulator.model.LocationSimulatorResponseModel;
import com.location.simulator.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class LocationSimulatorService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  PropertiesUtil propertiesUtil;


  public ArrayList<LatLngsModel>  getLocationDetails(LocationSimulatorRequestModel locationSimulatorRequestModel) {
    String destination = locationSimulatorRequestModel.getDestination().getLats() + "," + locationSimulatorRequestModel.getDestination().getLangs();
    String origin = locationSimulatorRequestModel.getOrigin().getLats() + "," + locationSimulatorRequestModel.getOrigin().getLangs();
    String Key = ApiConstants.API_KEY;
    final HashMap<String, String> pathVariables = new HashMap<>();
    pathVariables.put(ApiConstants.DESTINATION, destination);
    pathVariables.put(ApiConstants.KEY, Key);
    pathVariables.put(ApiConstants.ORIGIN, origin);
    String url = propertiesUtil.getGeoLocationBaseApi().buildAndExpand(pathVariables).toUriString();
    Object object;
    LocationSimulatorResponseModel locationSimulatorResponseModel = new LocationSimulatorResponseModel();
    ArrayList<LatLngsModel> cords = new ArrayList<>();
    try {
      object = restTemplate.getForObject(url, Object.class);
      Gson gson = new Gson();
      String json = gson.toJson(object);
      JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
      JsonObject routesObject = (JsonObject) convertedObject.getAsJsonArray("routes").get(0);
      JsonObject legsObject = (JsonObject) routesObject.getAsJsonArray("legs").get(0);
      JsonObject distance = (JsonObject) legsObject.get("distance");
      JsonElement text = distance.get("text");
      String totalDistance = text.getAsString();
      Double value = Double.parseDouble(totalDistance.replaceAll("km", ""));
      double azimuth = propertiesUtil.calculateBearing(locationSimulatorRequestModel);
       cords = propertiesUtil.getLocations(50, azimuth, locationSimulatorRequestModel.getOrigin(), locationSimulatorRequestModel.getDestination(), value);
      locationSimulatorResponseModel.setLatLngsModelList(cords);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return cords;
  }
}




