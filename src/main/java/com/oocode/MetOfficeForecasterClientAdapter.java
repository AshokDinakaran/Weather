package com.oocode;

import com.teamoptimization.LocatorClient;
import com.teamoptimization.MetOfficeForecasterClient;

import java.io.IOException;
import java.time.DayOfWeek;


public class MetOfficeForecasterClientAdapter implements IForecaster {
    @Override
    public String Forecast(String day, String place) throws IOException {
        int dayNumber = DayOfWeek.valueOf(day.toUpperCase()).getValue();
        LocatorClient.Location location = new LocatorClient().locationOf(place);
        MetOfficeForecasterClient forecasterClient = new MetOfficeForecasterClient();
        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(dayNumber, location.latitude, location.longitude);
        return(String.format("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description));
    }
}
