package com.makariyp.weatherbot.external;

import com.makariyp.weatherbot.model.OpenWeatherMapAns;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather", url = "https://api.openweathermap.org")
public interface WeatherApi {

    @GetMapping("/data/2.5/weather?q={city}&appid={apiKey}")
    OpenWeatherMapAns getWeather(@PathVariable("city") String city, @PathVariable("apiKey") String apiKey);
}
