package com.stickyrecycler;

public class SelectCityBean {

    private Long id;
    private String cityName;
    private String letter;

    public SelectCityBean() {
    }

    public SelectCityBean(Long id, String cityName, String letter) {
        this.id = id;
        this.cityName = cityName;
        this.letter = letter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
