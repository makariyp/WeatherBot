package com.makariyp.weatherbot.service;

import com.makariyp.weatherbot.model.OpenWeatherMapAns;
import com.makariyp.weatherbot.external.WeatherApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService{
    private final WeatherApi weatherApi;
    @Value("${weather.api-key}")
    private String APPID;

    public WeatherServiceImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public String getActual(String q) {
        OpenWeatherMapAns weather = weatherApi.getWeather(q, APPID);
        float temp = weather.getMain().getFeels_like() - 273.15f;
        return getClothes(temp);
    }

    private String getClothes(float temp) {
        if (temp > 25) return "На улице жарко, надеваем футболку и шорты";
        if (temp > 20) return "На улице жарко, надеваем футболку и джинсы";
        if (temp > 17) return "На улице тепло, надеваем футболку и джинсы. Лучше взять с собой легкую кофту";
        if (temp > 16) return "На улице тепло, надеваем футболку и джинсы и легкую кофту";
        if (temp > 15) return "На улице тепло, надеваем футболку и легкую курточку";
        if (temp > 14) return "На улице тепло, надеваем футболку с легкой курткой (или кофточка)";
        if (temp > 13) return "На улице тепло, надеваем легкую курточку";
        if (temp > 12) return "На улице тепло, кофта и легкая куртка отлично подойдут";
        if (temp > 11) return "На улице тепло, надеваем кожанку";
        if (temp > 10) return "На улице тепло, надеваем футболку и кофту с джинсами. Берем с собой куртку";
        if (temp > 9) return "На улице тепло, надеваем футболку и кофту с джинсами. Лучше взять куртку";
        if (temp > 8) return "На улице тепло, надеваем футболку и кофту с курткой потеплее, вечером похолодает";
        if (temp > 7) return "На улице тепло, надеваем куртку потеплее, вечером похолодает";
        if (temp > 6) return "На улице уже не тепло, надеваем кофту и куртку потеплее";
        if (temp > 5) return "На улице прохладно, надеваем теплую кофту и курточку";
        if (temp > 4) return "На улице прохладно, теплая кофта и куртка отлично подойдут";
        if (temp > 3) return "На улице прохладно, надеваем теплую кофту и курточку";
        if (temp > 0) return "На улице все еще холодно, надеваем теплую куртку и кофту";
        if (temp > -5) return "На улице все еще холодно, надеваем куртку потеплее и кофту";
        if (temp > -10) return "На улице холодно, надеваем теплую куртку";
        if (temp > -13) return "На улице холодно, надеваем теплую куртку и теплую кофту";
        if (temp > -15) return "На улице мороз, надеваем теплую куртку и подштанники";
        if (temp > -20) return "На улице мороз, надеваем теплую куртку и подштанники";
        return "На улицу убийственный мороз, надеваем все самое теплое";
    }
}