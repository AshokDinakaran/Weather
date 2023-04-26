package com.oocode;

import com.teamoptimization.LocatorClient;
import com.teamoptimization.MetOfficeForecasterClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    static MetOfficeForecasterClientAdapter adapter = new MetOfficeForecasterClientAdapter();
    static ForecasterCache cache = new ForecasterCache(adapter);

    public static void main(String[] args) throws IOException {
        if (args.length % 2 != 0) {
            throw new RuntimeException("Must specify Day and Place");
        }

        for (int i = 0; i < args.length / 2; i++) {
            forecast(args[i * 2], args[i * 2 + 1]);
        }

    }

    private static void forecast(String day, String place) throws IOException {
        System.out.printf(cache.Forecast(day, place));
    }
}
