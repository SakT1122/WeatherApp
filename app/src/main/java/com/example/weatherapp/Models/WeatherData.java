package com.example.weatherapp.Models;

import java.util.List;

public class WeatherData {

    private List<weather> weathers;
    private main main;
    private String name;

    public WeatherData(List<weather> weathers, com.example.weatherapp.Models.main main, String name) {
        this.weathers = weathers;
        this.main = main;
        this.name = name;
    }

    public List<weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<weather> weathers) {
        this.weathers = weathers;
    }

    public com.example.weatherapp.Models.main getMain() {
        return main;
    }

    public void setMain(com.example.weatherapp.Models.main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
