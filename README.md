# Location-Simulator
Steps to run Location Simulator
first open it in intellija
build the application 
so the next step is to hit the url type localhost:8080/location in the postman and pass this 
{
    "origin": {
        "lats":"39.862808",
        "langs":"-4.0273727"
    },
    "destination": {
        "lats":"40.4165207",
        "langs":"-3.705076"
    }
}
as body

the response would be list of LatLngs at every 50ms.

Thank you
