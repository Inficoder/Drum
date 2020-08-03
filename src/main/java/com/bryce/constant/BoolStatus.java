package com.bryce.constant;

/**
 * @ClassName BoolStatus
 * @Description
 * @Author Bryce
 * @Date 2020-07-21 14:28
 */
public enum BoolStatus {
    TRUE("true", "是"),
    FALSE("false", "否");
    private String status;
    private String name;

    BoolStatus(String status, String name) {
        this.status = status;
        this.name = name;
    }
}
