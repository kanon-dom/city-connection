package com.mc.challenge.cityconnection.model;

public class CityRoute {
  private String origin;
  private String destination;

  public CityRoute(){
    super();
  }

  public CityRoute(String origin, String destination){
    this.origin = origin;
    this.destination = destination;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }
}
