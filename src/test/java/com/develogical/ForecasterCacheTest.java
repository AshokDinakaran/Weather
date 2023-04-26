package com.develogical;


import com.oocode.ForecasterCache;
import com.oocode.IForecaster;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ForecasterCacheTest {

    List<String> days = Arrays.asList("Monday", "Tuesday");
    List<String> locations = Arrays.asList("London", "Oxford", "abs", "asdf");
    IForecaster forecaster;
    ForecasterCache cacheClient;
    String day = days.get((int) Math.floor(Math.random() * days.size()));
    String place = locations.get((int) Math.floor(Math.random() * locations.size()));
    String expected = String.format("forecaster: %s day=%s ", day, place);

    @Before
    public void setup() throws IOException {
        forecaster = mock(IForecaster.class);
        cacheClient = new ForecasterCache(forecaster);
        given(forecaster.Forecast(day, place)).willReturn(expected);
    }

    @Test
    public void GetNewForecastSendsRequest() throws IOException {
        cacheClient.Forecast(day, place);
        verify(forecaster).Forecast(day, place);
    }

    @Test
    public void GetNewForecastReturnsResult() throws IOException {
        assertThat(cacheClient.Forecast(day, place), is(expected));
    }

    @Test
    public void GetForecastFromCacheSendsRequestOnce() throws IOException {
        cacheClient.Forecast(day, place);
        verify(forecaster).Forecast(day, place);
        cacheClient.Forecast(day, place);
        verifyNoMoreInteractions(forecaster);
    }

    @Test
    public void GetForecastFromCacheSendsRequestOnceAndReturnsResult() throws IOException {
        cacheClient.Forecast(day, place);
        cacheClient.Forecast(day, place);
        assertThat(cacheClient.Forecast(day, place), is(expected));
    }
}
