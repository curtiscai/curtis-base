package com.curtis.guava.model;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference
 */
public class City {
    private Integer cityCode;

    private String cityName;

    public City() {
    }

    public City(Integer cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
