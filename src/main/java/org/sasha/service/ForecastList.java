package org.sasha.service;


import org.sasha.Forecast;
import org.sasha.annotation.CheckCountryCapital;

import java.io.IOException;

public interface ForecastList {
    Forecast getWeather(@CheckCountryCapital String city) throws IOException;
}
