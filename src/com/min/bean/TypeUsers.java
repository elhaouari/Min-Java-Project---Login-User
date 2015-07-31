package com.min.bean;

public enum TypeUsers {

    SUPERADMIN("super admin"),
    ADMIN("admin"),
    USER("user");

    private final String value;
     
    public static TypeUsers getType(String typeUser) {
        for (TypeUsers t : values()) {
            if (t.getValue().equalsIgnoreCase(typeUser)) {
                return t;
            }
        }
        return USER;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
    
    TypeUsers(String value) {
        this.value = value;
    }
}
