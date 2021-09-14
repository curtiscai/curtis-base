package com.curtis.guava.model;

import java.util.List;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-10
 * @email curtis.cai@outlook.com
 * @reference
 */
public class Province {

    private Integer provinceCode;

    private String provinceName;

    List<City> cityList;

    public Province() {
    }

    public Province(Integer provinceCode, String provinceName, List<City> cityList) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityList = cityList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
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

    @Override
    public String toString() {
        return "Province{" +
                "provinceCode=" + provinceCode +
                ", provinceName='" + provinceName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
