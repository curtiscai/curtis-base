package com.curtis.java8.model;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference
 */
public class ProvinceCity {

    private Integer provinceCode;

    private String provinceName;

    private Integer cityCode;

    private String cityName;

    public ProvinceCity() {
    }

    public ProvinceCity(Integer provinceCode, String provinceName, Integer cityCode, String cityName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
        return "ProvinceCity{" +
                "provinceCode=" + provinceCode +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
