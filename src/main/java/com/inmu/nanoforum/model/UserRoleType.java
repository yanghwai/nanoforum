package com.inmu.nanoforum.model;

public enum UserRoleType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    final String userProfileType;

    UserRoleType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }


}
