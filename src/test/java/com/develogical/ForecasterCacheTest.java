package com.develogical;


import com.oocode.ForecasterCache;
import com.oocode.IForecaster;
import com.oocode.MetOfficeForecasterClientAdapter;
import org.junit.Assert;
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
    List<String> locations = Arrays.asList("London", "Oxford");
    IForecaster mockForecaster;
    ForecasterCache cacheClient;
    String day = days.get((int) Math.floor(Math.random() * days.size()));
    String place = locations.get((int) Math.floor(Math.random() * locations.size()));
    String expected = String.format("forecaster: %s day=%s ", place, day);

    @Before
    public void setup() throws IOException {
        MetOfficeForecasterClientAdapter realForecaster = new MetOfficeForecasterClientAdapter();
        expected = realForecaster.Forecast(day, place);

        mockForecaster = mock(IForecaster.class);
        cacheClient = new ForecasterCache(mockForecaster);
        given(mockForecaster.Forecast(day, place)).willReturn(expected);
    }

    @Test
    public void GetNewForecastSendsRequest() throws IOException {
        cacheClient.Forecast(day, place);
        verify(mockForecaster).Forecast(day, place);
    }

    @Test
    public void GetNewForecastReturnsResult() throws IOException {
        Assert.assertTrue(cacheClient.Forecast(day, place).startsWith(expected));
    }

    @Test
    public void GetForecastFromCacheSendsRequestOnce() throws IOException {
        cacheClient.Forecast(day, place);
        verify(mockForecaster).Forecast(day, place);
        cacheClient.Forecast(day, place);
        verifyNoMoreInteractions(mockForecaster);
    }

    @Test
    public void GetForecastFromCacheSendsRequestOnceAndReturnsResult() throws IOException {
        cacheClient.Forecast(day, place);
        cacheClient.Forecast(day, place);
        Assert.assertTrue(cacheClient.Forecast(day, place).startsWith(expected));
    }
}
