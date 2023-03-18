package com.example.project3;

/**
 Defines the majors provided by the University,
 including the school name and major code.
 @author David Harianto, Joban Singh
 **/
public enum Major {

    CS("SAS", "01:198"),
    MATH("SAS", "01:640"),
    EE("SOE", "14:332"),
    ITI("SC&I", "04:547"),
    BAIT("RBS", "33:136"),
    UNKNOWN(null, null);
    private final String school;
    private final String code;

    /**
     This constructor takes in the school name and major code.
     @author David Harianto, Joban Singh
     **/
    Major(String school, String code)
    {
        this.school = school;
        this.code = code;
    }

    /**
     This method returns the school name.
     @author David Harianto, Joban Singh
     **/
    public String getSchool() {
        return school;
    }

    /**
     This method returns the major code.
     @author David Harianto, Joban Singh
     **/
    public String getCode() {
        return code;
    }

}
