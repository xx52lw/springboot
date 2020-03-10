package com.dock.lw.common.enums;

public enum SystemContextType {

    CURRENT_USER("1", "当前用户"),
    CURRENT_ORG("2", "当前组织"),
    ;

    private String value;
    private String text;

    SystemContextType(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String value() {
        return value;
    }
}
