package com.oocode;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class ForecasterCache
{
    private Dictionary<String, String> cachedResponse = new Hashtable<>();
    private IForecaster forecaster;

    public ForecasterCache(IForecaster forecaster)
    {
        this.forecaster = forecaster;
    }
    public String Forecast(String day, String place) throws IOException {
        String key = place + day;
        String response = cachedResponse.get(key);
        if (response == null) {
            response = forecaster.Forecast(day, place);
            cachedResponse.put(key, response);
            return response;
        }

        return response;
    }
}
