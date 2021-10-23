package com.location.simulator.utils;

import com.location.simulator.constants.ApiConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@Component
@Getter
public class PropertiesUtil {
  @Value("${geo.location.api}")
  private String geoLocationApi;

  public UriComponentsBuilder getGeoLocationBaseApi() {
    return UriComponentsBuilder.fromHttpUrl(geoLocationApi);
  }
}
