package com.arg.user.user.constants;

public enum Status {
    ACTIVE("Active"),
    DISABLED("Disabled"),
    INACTIVE("Inactive");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
