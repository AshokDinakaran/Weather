package com.develogical;


import com.oocode.ForecasterCache;
import com.oocode.IForecaster;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ForecasterCacheTest {

    @Test
    public void GetNewForecastTest() {
        IForecaster forecaster = mock(IForecaster.class);

        ForecasterCache cacheClient = new ForecasterCache(forecaster);
        String day = "Wednesday";
        String place = "Reading";

        given(forecaster.Forecast(day, place)).willReturn("moo");
        cacheClient.Forecast(day, place);
        verify(forecaster).Forecast(day, place);
    }


    @Test
    public void GetForecastFromCache() {
        IForecaster forecaster = mock(IForecaster.class);

        ForecasterCache cacheClient = new ForecasterCache(forecaster);
        String day = "Wednesday";
        String place = "Reading";

        given(forecaster.Forecast(day, place)).willReturn("moo");
        cacheClient.Forecast(day, place);
        verify(forecaster).Forecast(day, place);
        cacheClient.Forecast(day, place);
        verifyNoMoreInteractions(forecaster);
    }
}
