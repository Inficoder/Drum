package com.bryce.constant;

/**
 * @ClassName Gender
 * @Description gender
 * @Author Bryce
 * @Date 2020-07-20 13:17
 */
public enum Gender {
    FEMALE("female", "女"),
    MALE("male", "男");

    private String status;
    private String name;

    Gender(String status, String name) {
        this.status = status;
        this.name = name;
    }
}
