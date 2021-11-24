package com.curtis.apache.lang3.enums;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-24
 * @email curtis.cai@outlook.com
 * @reference
 */
public enum CoordinateSystemEnum {
    WGS84("wgs84", 1),
    GCJ02("gcj02", 2),
    BD09("bd09", 3);

    private String coordinateSystemLabel;
    private Integer coordinateSystemValue;

    private CoordinateSystemEnum(String coordinateSystemLabel, Integer coordinateSystemValue) {
        this.coordinateSystemLabel = coordinateSystemLabel;
        this.coordinateSystemValue = coordinateSystemValue;
    }

    public String getCoordinateSystemLabel() {
        return coordinateSystemLabel;
    }

    public Integer getCoordinateSystemValue() {
        return coordinateSystemValue;
    }
}
