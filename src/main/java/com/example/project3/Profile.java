package com.example.project3;

/**
 Represents a Profile object which consists of first name, last name, and DOB.
 @author David Harianto, Joban Singh
 **/
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the project1.Date class described in (f)

    /**
     This constructor takes in the first name, last name, and DOB.
     @author David Harianto, Joban Singh
     **/
    public Profile(String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     This method returns first name, last name, and DOB.
     @author David Harianto, Joban Singh
     **/
    @Override
    public String toString()
    {
        return fname + " " + lname + " " + dob;
    }

    /**
     This method checks if profile has same first name, last name, and DOB.
     @author David Harianto, Joban Singh
     **/
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this){
            return true;
        }
        // Compare two profiles
        if(!(obj instanceof Profile)){
            return false;
        }
        Profile temporary = (Profile) obj;
        return this.fname.toLowerCase().equals(temporary.getFirstname().toLowerCase()) && this.lname.toLowerCase().equals(temporary.getLastname().toLowerCase()) && this.dob.equals(temporary.getDob());
    }

    /**
     This method takes in the profile and compares it with
     another profile which returns -1 if it comes before,
     0 if it is the same profile, and 1 if it comes after.
     @author David Harianto, Joban Singh
     **/
    @Override
    public int compareTo(Profile p) {
        // return int when comparing students
        if(this.lname.toLowerCase().compareTo(p.lname.toLowerCase()) < 0) {
            return -1;
        }
        else if(this.lname.toLowerCase().compareTo(p.lname.toLowerCase()) > 0) {
            return 1;
        }
        else {
            if (this.fname.toLowerCase().compareTo(p.fname.toLowerCase()) < 0) {
                return -1;
            }
            else if (this.fname.toLowerCase().compareTo(p.fname.toLowerCase()) > 0) {
                return 1;
            }
            else {
                if (this.dob.compareTo(p.dob) > 0) {
                    return -1;
                }
                else if (this.dob.compareTo(p.dob) < 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }
    /**
     This method returns first name.
     @author David Harianto, Joban Singh
     **/

    public String getFirstname(){

        return fname;
    }

    /**
     This method returns last name.
     @author David Harianto, Joban Singh
     **/
    public String getLastname(){

        return lname;
    }

    /**
     This method returns DOB.
     @author David Harianto, Joban Singh
     **/
    public Date getDob(){

        return dob;
    }
}