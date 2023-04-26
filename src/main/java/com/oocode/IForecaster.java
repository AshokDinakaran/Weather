package com.oocode;

import java.io.IOException;

public interface IForecaster {
    public String Forecast(String day,
                           String place) throws IOException;
}
